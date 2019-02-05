package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    Point pointA = new Point(2,4);
    Point pointB = new Point(5,8);

    @Test
    public void distanceEqualsFive(){

        Assert.assertEquals(pointA.distanceToPoint(pointB), 5.0);
    }

    @Test
    public void distanceDecimal(){

        Point pointC = new Point(25.8, 38.9);
        Point pointD = new Point(10.2, 31.5);

        Assert.assertEquals(pointC.distanceToPoint(pointD), 17.266151858477325);
    }

    @Test
    public void distanceEqualsForBothPoints(){

        Assert.assertEquals(pointA.distanceToPoint(pointB), pointB.distanceToPoint(pointA));
    }

    @Test
    public void distanceSamePoint(){

        Assert.assertEquals(pointA.distanceToPoint(pointA), 0.0);
    }

    @Test
    public void distanceEqualsZero(){

        Point pointC = new Point(2,6);
        Point pointD = new Point(2,6);

        Assert.assertEquals(pointC.distanceToPoint(pointD), 0.0);
    }
}
