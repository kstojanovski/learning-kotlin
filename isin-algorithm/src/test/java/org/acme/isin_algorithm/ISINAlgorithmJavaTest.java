package org.acme.isin_algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testing the {@link ISINAlgorithmJava} which contains entry point and the calculation through the ISIN algorithm.
 */
public class ISINAlgorithmJavaTest {

    /**
     * Testing the calculation through the ISIN algorithm.
     */
    @Test
    public void isinAlgTest() {
        Assert.assertEquals(8, ISINAlgorithmJava.proveISIN("DE000555750"));
        Assert.assertEquals(5, ISINAlgorithmJava.proveISIN("DE000LBW2CG"));
        Assert.assertEquals(0, ISINAlgorithmJava.proveISIN("DE000LB0CGD"));
        Assert.assertEquals(7, ISINAlgorithmJava.proveISIN("DE000LB0CGJ"));
        Assert.assertEquals(4, ISINAlgorithmJava.proveISIN("DE000589785"));
        Assert.assertEquals(0, ISINAlgorithmJava.proveISIN("DE000897333"));
        Assert.assertEquals(3, ISINAlgorithmJava.proveISIN("DE000311608"));
        Assert.assertEquals(9, ISINAlgorithmJava.proveISIN("DE000589783"));
        Assert.assertEquals(7, ISINAlgorithmJava.proveISIN("DE000589784"));
        Assert.assertEquals(5, ISINAlgorithmJava.proveISIN("DE000322432"));
        Assert.assertEquals(6, ISINAlgorithmJava.proveISIN("DE000812251"));
        Assert.assertEquals(8, ISINAlgorithmJava.proveISIN("DE000897334"));
        Assert.assertEquals(3, ISINAlgorithmJava.proveISIN("DE000136657"));
    }
}
