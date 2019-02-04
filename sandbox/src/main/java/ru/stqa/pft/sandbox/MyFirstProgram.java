package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void myFirstProgram(){

		System.out.println("Hello, World!");

		Square square = new Square(5);
		System.out.println("Square side is " + square.side + ", square area is " + square.area());

		Rectangle rectangle = new Rectangle(6,4);
		System.out.println("Rectangle sides are " + rectangle.width + " and " + rectangle.height
				+ ", rectangle area is " + rectangle.area());
	}

}