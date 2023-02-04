package org.acme.isin_algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main and entry point class of the ISIN algorithm program.
 * The final part of the ISIN algorithm is also places here.
 */
public class ISINAlgorithmJava {

    public static final Logger LOGGER = LoggerFactory.getLogger(ISINAlgorithmJava.class);
    public static final Logger LOGGER_SIMPLE = LoggerFactory.getLogger("SIMPLE");
    private final static ISINAlgorithmResult ISIN_ALGORITHM_PRODUCT = new ISINAlgorithmResult();

    public static void main(String[] args) {
        LOGGER.info("Start");
        proveISIN("DE000555750");
        LOGGER.info("End");
    }

    /**
     * Calculates the result which needs to be calculated from the input through the ISIN algorithm.
     *
     * @param rawIsin inbound data which needs to be used the ISIN algorithm on.
     * @return the result which needs to be calculated from the input through the ISIN algorithm.
     */
    public static int proveISIN(String rawIsin) {
        LOGGER_SIMPLE.info(rawIsin);
        int globalSum = ISIN_ALGORITHM_PRODUCT.getGlobalSum(rawIsin);
        LOGGER_SIMPLE.info("global sum " + globalSum);
        int globalModulo = globalSum % 10;
        return globalModulo == 0 ? 0 : 10 - globalModulo;
    }

}
