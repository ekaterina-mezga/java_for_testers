package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testArea(){
        Square square = new Square(5);
        square.area();
        Assert.assertEquals(square.area(), 20.0);
    }
}
