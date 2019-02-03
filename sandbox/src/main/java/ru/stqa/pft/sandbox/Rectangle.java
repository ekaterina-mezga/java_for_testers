package ru.stqa.pft.sandbox;

public class Rectangle {

    public double height;
    public double width;

    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }

    public double area(){
        return this.height * this.width;
    }

}
