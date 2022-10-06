package com.company;


public class Calculator {


    public int add(int a, int b) { return a + b; }

    public double add(double a, double b) { return a + b; }
    public int subtract(int a, int b) {
        return a - b;
    }

    // program prints out the calculation being performed and the result of the calculation; 5.5 - 0.5
    public double subtract(double a, double b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    // program prints out the calculation being performed and the result of the calculation; 6.7 * 4.4
    public double multiply(double a, double b) {
        return a * b;
    }

     public int divide(int a, int b) {
        return a / b;
    }

    // program prints out the calculation being performed and the result of the calculation; 10.8/2.2
    public double divide(double a, double b) {
        return a / b;
    }


    public void calculations(int answer) {

        System.out.println("The answer is: " + answer);
    }
    public void calculations(double answer) {

        System.out.println("The answer is: " + answer);
    }
}
