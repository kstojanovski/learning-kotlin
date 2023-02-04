package org.acme.isin_algorithm

import org.acme.isin_algorithm.ISINAlgorithmKotlin.logger
import org.acme.isin_algorithm.ISINAlgorithmKotlin.proveISIN
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ISINAlgorithmKotlin {

    val logger: Logger = LoggerFactory.getLogger(ISINAlgorithmJava::class.java)

    private val loggerSimple: Logger = LoggerFactory.getLogger("SIMPLE")

    private val isinAlgorithmProduct = ISINAlgorithmResultKotlin()

    /**
     * Calculates the result which needs to be calculated from the input through the ISIN algorithm.
     *
     * @param rawIsin inbound data which needs to be used the ISIN algorithm on.
     * @return the result which needs to be calculated from the input through the ISIN algorithm.
     */
    fun proveISIN(rawIsin: String): Int {
        loggerSimple.info(rawIsin)
        val globalSum = isinAlgorithmProduct.getGlobalSum(rawIsin)
        loggerSimple.info("global sum $globalSum")
        val globalModulo = globalSum % 10
        return if (globalModulo == 0) 0 else 10 - globalModulo
    }
}

fun main() {
    logger.info("Start")
    proveISIN("DE000555750")
    logger.info("End")
}

