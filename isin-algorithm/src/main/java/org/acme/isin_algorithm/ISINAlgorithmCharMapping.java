package org.acme.isin_algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a class where the mapping for the ISIN algorithm is generated.
 * The mapping is between the letters as key and value which starts from 10.
 * I.e. A:10, B:11, ... Z:35.
 */
class ISINAlgorithmCharMapping {

    //ASCII presentation of the letter 'A'
    private static final int SIGN_A_IN_ASCII = 65;
    //ASCII presentation of the letter 'Z'
    private static final int SIGN_Z_IN_ASCII = 90;
    //Init value of the ISIN algorithm which is related to the letter 'A'.
    private static final int INIT_SING_VALUE_FOR_ISIN = 10;

    //The map which will be filled here and used by the ISIN algorithm.
    Map<Character, char[]> substitutionTable;

    ISINAlgorithmCharMapping() {
        substitutionTableInitialisation();
    }

    Map<Character, char[]> getSubstitutionTable() {
        return substitutionTable;
    }

    /**
     * Filling the main structure (map) with initial data beginning from
     * A:10, B:11 to Z:35.
     */
    private void substitutionTableInitialisation() {
        substitutionTable = new HashMap<>();
        int value = INIT_SING_VALUE_FOR_ISIN;
        int charNr = SIGN_A_IN_ASCII;
        int endOfSings = SIGN_Z_IN_ASCII + 1;
        while(charNr < endOfSings) {
            substitutionTable.put(((char)charNr), createTableValues(value));
            charNr++; value++;
        }
    }

    /**
     * Converting the inbound integer via string into char array needed for the ISIN algorithm.
     *
     * @param valueInput inbound code as numeric value.
     * @return code for the ISIN algorithm which is a number value presented as char array.
     */
    private char[] createTableValues(int valueInput) {
        String s = String.valueOf(valueInput);
        return s.toCharArray();
    }
}
