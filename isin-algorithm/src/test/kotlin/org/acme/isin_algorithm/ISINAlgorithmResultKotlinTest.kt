package org.acme.isin_algorithm

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Testing the {@link ISINAlgorithmResult} which contains the summation of all ISIN digit.
 */
class ISINAlgorithmResultKotlinTest {

    private val isinAlgorithmResult = ISINAlgorithmResult()

    /**
     * Testing the summation of all ISIN digit.
     */
    @Test
    fun isinAlgTest() {
        assertEquals(32, isinAlgorithmResult.getGlobalSum("DE000555750"))
        assertEquals(45, isinAlgorithmResult.getGlobalSum("DE000LBW2CG"))
        assertEquals(40, isinAlgorithmResult.getGlobalSum("DE000LB0CGD"))
        assertEquals(43, isinAlgorithmResult.getGlobalSum("DE000LB0CGJ"))
        assertEquals(46, isinAlgorithmResult.getGlobalSum("DE000589785"))
        assertEquals(50, isinAlgorithmResult.getGlobalSum("DE000897333"))
        assertEquals(27, isinAlgorithmResult.getGlobalSum("DE000311608"))
        assertEquals(51, isinAlgorithmResult.getGlobalSum("DE000589783"))
        assertEquals(53, isinAlgorithmResult.getGlobalSum("DE000589784"))
        assertEquals(35, isinAlgorithmResult.getGlobalSum("DE000322432"))
        assertEquals(34, isinAlgorithmResult.getGlobalSum("DE000812251"))
        assertEquals(52, isinAlgorithmResult.getGlobalSum("DE000897334"))
        assertEquals(37, isinAlgorithmResult.getGlobalSum("DE000136657"))
    }
}
