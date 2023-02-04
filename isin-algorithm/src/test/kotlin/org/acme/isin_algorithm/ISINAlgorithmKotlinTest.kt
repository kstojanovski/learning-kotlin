package org.acme.isin_algorithm

import org.junit.Assert.assertEquals
import org.junit.Test

class ISINAlgorithmKotlinTest {

    @Test
    fun isinAlgTest() {
        assertEquals(8, ISINAlgorithmKotlin.proveISIN("DE000555750"))
        assertEquals(5, ISINAlgorithmKotlin.proveISIN("DE000LBW2CG"))
        assertEquals(0, ISINAlgorithmKotlin.proveISIN("DE000LB0CGD"))
        assertEquals(7, ISINAlgorithmKotlin.proveISIN("DE000LB0CGJ"))
        assertEquals(4, ISINAlgorithmKotlin.proveISIN("DE000589785"))
        assertEquals(0, ISINAlgorithmKotlin.proveISIN("DE000897333"))
        assertEquals(1, ISINAlgorithmKotlin.proveISIN("DE000158829"))
        assertEquals(3, ISINAlgorithmKotlin.proveISIN("DE000311608"))
        assertEquals(1, ISINAlgorithmKotlin.proveISIN("DE000589782"))
        assertEquals(9, ISINAlgorithmKotlin.proveISIN("DE000589783"))
        assertEquals(7, ISINAlgorithmKotlin.proveISIN("DE000589784"))
        assertEquals(5, ISINAlgorithmKotlin.proveISIN("DE000322432"))
        assertEquals(6, ISINAlgorithmKotlin.proveISIN("DE000812251"))
        assertEquals(8, ISINAlgorithmKotlin.proveISIN("DE000897334"))
        assertEquals(3, ISINAlgorithmKotlin.proveISIN("DE000136657"))
    }

}
