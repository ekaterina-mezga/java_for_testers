package ru.stqa.pft.sandbox;

public class Square {

    public double side;

    public Square(double side){

        this.side = side;
    }

    public double area(){
        return this.side * this.side;
    }

}
