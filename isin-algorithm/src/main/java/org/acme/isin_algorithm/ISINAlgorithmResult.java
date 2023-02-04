package org.acme.isin_algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Calculates the sum of the products, optionally crossover summed, of all ISIN digits.
 */
class ISINAlgorithmResult {

    public static final Logger LOGGER_SIMPLE = LoggerFactory.getLogger("SIMPLE");
    private static final Map<Character, char[]> SUBSTITUTION_TABLE
            = new ISINAlgorithmCharMapping().getSubstitutionTable();
    int getGlobalSum(String rawIsin) {
        return calculateGlobalSum(rawIsin, rawIsin.length());
    }

    /**
     * Calculates the summation of the separated ISIN digits as one result value.
     *
     * @param rawIsin the inbound raw ISIN string.
     * @param length the raw ISIN string length.
     * @return the calculated value through the ISIN algorithm.
     */
    private int calculateGlobalSum(String rawIsin, int length) {
        int globalSum = 0;
        int isinDigitPosition = 1;
        for (int i = length -1; i > -1 ; i--) {
            char isinSingleChar = rawIsin.charAt(i);
            if (SUBSTITUTION_TABLE.containsKey(isinSingleChar)) {
                char[] chars = SUBSTITUTION_TABLE.get(isinSingleChar);
                int substitutionTableValueLength = chars.length;
                for (int j = substitutionTableValueLength -1; j > -1 ; j--) {
                    globalSum += calculateResultOfCurrentIsinChar(chars[j], isinDigitPosition);
                    isinDigitPosition++;
                }
            } else {
                globalSum += calculateResultOfCurrentIsinChar(isinSingleChar, isinDigitPosition);
                isinDigitPosition++;
            }
        }
        return globalSum;
    }

    /**
     * Calculates the product of the digit and the product and returns the end product result.
     *
     * @param isinSingleChar the current ISIN digit as character.
     * @param isinDigitPosition the position of the current ISIN digit as character.
     * @return the end result for the current ISIN digit.
     */
    private int calculateResultOfCurrentIsinChar(char isinSingleChar, int isinDigitPosition) {
        int isinDigitProduct = Integer.parseInt(String.valueOf(isinSingleChar)) * getWeight(isinDigitPosition);
        LOGGER_SIMPLE.info("weight product: " + isinDigitProduct);
        return isinDigitProduct > 9 ? calculateCrossSum(isinDigitProduct) : isinDigitProduct;
    }

    /**
     * Creates cross sum on the inbound argument which always has two or more digits.
     *
     * @param isinDigitProduct inbound argument which always has two or more digits.
     * @return cross sum on the inbound argument.
     */
    private int calculateCrossSum(int isinDigitProduct) {
        int crossSum = 0;
        for (char numberDigit : String.valueOf(isinDigitProduct).toCharArray()) {
            crossSum += Integer.parseInt(String.valueOf(numberDigit));
        }
        return crossSum;
    }

    /**
     * Calculates the weight depend on the inbound argument.
     *
     * @param isinDigitPosition position number of the digit after the letter substitution.
     * @return the weight depend on the inbound argument.
     */
    private int getWeight(int isinDigitPosition) {
        return (isinDigitPosition % 2) + 1;
    }
}
