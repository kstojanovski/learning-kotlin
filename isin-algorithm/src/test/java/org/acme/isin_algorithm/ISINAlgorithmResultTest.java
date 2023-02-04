package org.acme.isin_algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testing the {@link ISINAlgorithmResult} which contains the summation of all ISIN digit.
 */
public class ISINAlgorithmResultTest {

    ISINAlgorithmResult isinAlgorithmResult = new ISINAlgorithmResult();

    /**
     * Testing the summation of all ISIN digit.
     */
    @Test
    public void isinAlgTest() {
        Assert.assertEquals(32, isinAlgorithmResult.getGlobalSum("DE000555750"));
        Assert.assertEquals(45, isinAlgorithmResult.getGlobalSum("DE000LBW2CG"));
        Assert.assertEquals(40, isinAlgorithmResult.getGlobalSum("DE000LB0CGD"));
        Assert.assertEquals(43, isinAlgorithmResult.getGlobalSum("DE000LB0CGJ"));
        Assert.assertEquals(46, isinAlgorithmResult.getGlobalSum("DE000589785"));
        Assert.assertEquals(50, isinAlgorithmResult.getGlobalSum("DE000897333"));
        Assert.assertEquals(27, isinAlgorithmResult.getGlobalSum("DE000311608"));
        Assert.assertEquals(51, isinAlgorithmResult.getGlobalSum("DE000589783"));
        Assert.assertEquals(53, isinAlgorithmResult.getGlobalSum("DE000589784"));
        Assert.assertEquals(35, isinAlgorithmResult.getGlobalSum("DE000322432"));
        Assert.assertEquals(34, isinAlgorithmResult.getGlobalSum("DE000812251"));
        Assert.assertEquals(52, isinAlgorithmResult.getGlobalSum("DE000897334"));
        Assert.assertEquals(37, isinAlgorithmResult.getGlobalSum("DE000136657"));
    }
}
