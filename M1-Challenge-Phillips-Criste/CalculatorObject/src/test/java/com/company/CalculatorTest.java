package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

    public class CalculatorTest {

        @Test
        public void add() {
            Calculator calc = new Calculator();
            int actualResult = calc.add(1,2);
            int expectedResult = 3;
            assertEquals(expectedResult, actualResult);
        }

        @Test
        public void subtract() {
            Calculator calc = new Calculator();
            int actualResult = calc.subtract(23,52);
            int expectedResult = -29;
            assertEquals(expectedResult, actualResult);
        }

        @Test
        public void multiply() {
            Calculator calc = new Calculator();
            int actualResult = calc.multiply(34,2);
            int expectedResult = 68;
            assertEquals(expectedResult, actualResult);
        }
        @Test
        public void divideEvenly() {
            Calculator calc = new Calculator();
            int actualResult = calc.divide(12,3);
            int expectedResult = 4;
            assertEquals(expectedResult, actualResult);
        }

        @Test
        public void divideOddly() {
            Calculator calc = new Calculator();
            int actualResult = calc.divide(12,7);
            int expectedResult = 1;
            assertEquals(expectedResult, actualResult);
        }

        @Test
        public void addDouble() {
            Calculator calc = new Calculator();
            double actualResult = calc.add(3.4, 2.3);
            double expectedResult = 5.7;
            assertEquals(expectedResult, actualResult, 1E-8);
        }

        @Test
        public void subtractDouble() {
            Calculator calc = new Calculator();
            double actualResult = calc.subtract(5.5,0.5);
            double expectedResult = 5;
            assertEquals(expectedResult, actualResult, 1E-8);
        }

        @Test
        public void multiplyDouble() {
            Calculator calc = new Calculator();
            double actualResult = calc.multiply(6.7,4.4);
            double expectedResult = 29.48;
            assertEquals(expectedResult, actualResult, 1E-8);
        }
        @Test
        public void divideDouble() {
            Calculator calc = new Calculator();
            float actualResult = calc.divide(12,3);
            float expectedResult = 4;
            assertEquals(expectedResult, actualResult, 1E-8);
        }


}
