package ru.stqa.pft.sandbox;

public class Task2 {

    public static void main(String[] args){

        Point pointA = new Point(2, 4);
        Point pointB = new Point(5, 8);

        System.out.println("Distance between points is " + Point.distance(pointA, pointB));

        System.out.println("Distance from pointA point to pointB is " + pointA.distanceToPoint(pointB));

    }

}
