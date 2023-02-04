package org.acme.isin_algorithm

/**
 * This is a class where the mapping for the ISIN algorithm is generated.
 * The mapping is between the letters as key and value which starts from 10.
 * I.e. A:10, B:11, ... Z:35.
 */
class ISINAlgorithmCharMappingKotlin {
    //ASCII presentation of the letter 'A'
    private val signAInAscii: Int = 65

    //ASCII presentation of the letter 'Z'
    private val signZInAscii: Int = 90

    //Init value of the ISIN algorithm which is related to the letter 'A'.
    private val initSignValueForIsin: Int = 10

    //The map which will be filled here and used by the ISIN algorithm.
    var substitutionTable: MutableMap<Char, CharArray> = mutableMapOf()
        private set

    init {
        substitutionTableInitialisation()
    }

    /**
     * Filling the main structure (map) with initial data beginning from
     * A:10, B:11 to Z:35.
     */
    private fun substitutionTableInitialisation() {
        var value = initSignValueForIsin
        var charNr = signAInAscii
        val endOfSings = signZInAscii + 1
        while (charNr < endOfSings) {
            substitutionTable[charNr.toChar()] = value.toString().toCharArray()
            charNr++
            value++
        }
    }

}