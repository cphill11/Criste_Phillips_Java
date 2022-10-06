package com.company;

public class App {
    public static void main(String[] args) {
        String month = ConverterApplication.ConverterIf.convertMonth(1);
        System.out.println(month);

        String day = ConverterApplication.ConverterSwitch.convertDay(4);
        System.out.print(day);
    }
}
