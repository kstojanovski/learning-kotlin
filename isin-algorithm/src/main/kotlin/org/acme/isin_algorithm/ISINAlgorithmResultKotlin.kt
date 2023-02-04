package org.acme.isin_algorithm

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Calculates the sum of the products, optionally crossover summed, of all ISIN digits.
 */
class ISINAlgorithmResultKotlin {

    private val loggerSimple: Logger = LoggerFactory.getLogger("SIMPLE")

    private val substitutionTable = ISINAlgorithmCharMappingKotlin().substitutionTable

    fun getGlobalSum(rawIsin: String): Int {
        return calculateGlobalSum(rawIsin)
    }

    /**
     * Calculates the summation of the separated ISIN digits as one result value.
     *
     * @param rawIsin the inbound raw ISIN string.
     * @return the calculated value through the ISIN algorithm.
     */
    private fun calculateGlobalSum(rawIsin: String): Int {
        var globalSum = 0
        var isinDigitPosition = 1
        for (isinSingleChar in rawIsin.toCharArray().reversed()) {
            if (substitutionTable.containsKey(isinSingleChar)) {
                substitutionTable[isinSingleChar]?.reversed()?.forEach { codePart ->
                    globalSum += calculateResultOfCurrentIsinChar(codePart, isinDigitPosition)
                    isinDigitPosition++
                }
            } else {
                globalSum += calculateResultOfCurrentIsinChar(isinSingleChar, isinDigitPosition)
                isinDigitPosition++
            }
        }
        return globalSum
    }

    /**
     * Calculates the product of the digit and the product and returns the end product result.
     *
     * @param isinSingleChar the current ISIN digit as character.
     * @param isinDigitPosition the position of the current ISIN digit as character.
     * @return the end result for the current ISIN digit.
     */
    private fun calculateResultOfCurrentIsinChar(isinSingleChar: Char, isinDigitPosition: Int): Int {
        val isinDigitProduct = isinSingleChar.toString().toInt() * getWeight(isinDigitPosition)
        loggerSimple.info("weight product: $isinDigitProduct")
        return if (isinDigitProduct > 9) calculateCrossSum(isinDigitProduct) else isinDigitProduct
    }

    /**
     * Creates cross sum on the inbound argument which always has two or more digits.
     *
     * @param isinDigitProduct inbound argument which always has two or more digits.
     * @return cross sum on the inbound argument.
     */
    private fun calculateCrossSum(isinDigitProduct: Int): Int {
        var crossSum = 0
        for (numberDigit in isinDigitProduct.toString().toCharArray()) {
            crossSum += numberDigit.toString().toInt()
        }
        return crossSum
    }

    /**
     * Calculates the weight depend on the inbound argument.
     *
     * @param isinDigitPosition position number of the digit after the letter substitution.
     * @return the weight depend on the inbound argument.
     */
    private fun getWeight(isinDigitPosition: Int): Int {
        return (isinDigitPosition % 2) + 1
    }
}
