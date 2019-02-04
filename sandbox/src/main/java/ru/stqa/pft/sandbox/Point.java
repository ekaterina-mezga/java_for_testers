package ru.stqa.pft.sandbox;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y){

        this.x = x;
        this.y = y;
    }

    public static double distance(Point a, Point b){
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    }

    public double distanceToPoint(Point point){
        return Math.sqrt((this.x - point.x)*(this.x - point.x) + (this.y - point.y)*(this.y - point.y));
    }
}
