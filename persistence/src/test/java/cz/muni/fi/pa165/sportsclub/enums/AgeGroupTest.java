package cz.muni.fi.pa165.sportsclub.enums;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AgeGroupTest {

    @Test
    public void testOneAbove() throws Exception {
        Assert.assertEquals(AgeGroup.M16.oneAbove(), AgeGroup.M20);
        Assert.assertEquals(AgeGroup.M20.oneAbove(), AgeGroup.M24);
        Assert.assertEquals(AgeGroup.M24.oneAbove(), AgeGroup.MS);
        Assert.assertEquals(AgeGroup.MS.oneAbove(), null);
    }

    @Test
    public void testOneBelow() throws Exception {
        Assert.assertEquals(AgeGroup.MS.oneBelow(), AgeGroup.M24);
        Assert.assertEquals(AgeGroup.M24.oneBelow(), AgeGroup.M20);
        Assert.assertEquals(AgeGroup.M20.oneBelow(), AgeGroup.M16);
        Assert.assertEquals(AgeGroup.M16.oneBelow(), null);
    }
}
