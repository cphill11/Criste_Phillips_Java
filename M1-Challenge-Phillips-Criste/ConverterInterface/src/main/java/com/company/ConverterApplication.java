package com.company;

public class ConverterApplication {
    interface ConverterIf {
        static String convertMonth(int monthNumber) {
            if (monthNumber == 1) {
                return "January";
            } else if (monthNumber == 2) {
                return "February";
            } else if (monthNumber == 3) {
                return "March";
            } else if (monthNumber == 4) {
                return "April";
            } else if (monthNumber == 5) {
                return "May";
            } else if (monthNumber == 6) {
                return "June";
            } else if (monthNumber == 7) {
                return "July";
            } else if (monthNumber == 8) {
                return "August";
            } else if (monthNumber == 9) {
                return "September";
            } else if (monthNumber == 10) {
                return "October";
            } else if (monthNumber == 11) {
                return "November";
            } else if (monthNumber == 12) {
                return "December";
            } else {
                return "Error";
            }
        }
    }

    interface ConverterSwitch {
        static String convertDay(int dayNumber) {
            int day = dayNumber;
            String dayString;
            switch (day) {
                case 1:
                    dayString = "Sunday";
                    break;
                case 2:
                    dayString = "Monday";
                    break;
                case 3:
                    dayString = "Tuesday";
                    break;
                case 4:
                    dayString = "Wednesday";
                    break;
                case 5:
                    dayString = "Thursday";
                    break;
                case 6:
                    dayString = "Friday";
                    break;
                case 7:
                    dayString = "Saturday";
                    break;
                default:
                    dayString = "Error";
                    break;

            }
            return dayString;

        }
    }
 }



