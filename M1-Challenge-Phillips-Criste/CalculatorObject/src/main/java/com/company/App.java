package com.company;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
         Calculator calculator = new Calculator();

        calculator.calculations(calculator.add(1,1));
        calculator.calculations(calculator.subtract(23,52));
        calculator.calculations(calculator.multiply(34, 2));
        calculator.calculations(calculator.divide(12,3));
        calculator.calculations(calculator.divide(12,7));

        calculator.calculations(calculator.add(3.4,2.3));
        calculator.calculations(calculator.subtract(5.5, 0.5));
        calculator.calculations(calculator.multiply(6.7, 4.4));
        calculator.calculations(calculator.divide(10.8,2.2));
    }
}
