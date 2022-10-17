package com.company.M2ChallengeCristePhillips.controllers;

import com.company.M2ChallengeCristePhillips.models.Month;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.*;

@RestController

public class MonthController {
    // data to be used in lieu of accessing a DB goes here; from class notes on Spring Boot II day
    private List<Month> monthList = new ArrayList<>(Arrays.asList(
            new Month(1, "January"),
            new Month(2, "February"),
            new Month(3, "March"),
            new Month(4, "April"),
            new Month(4, "May"),
            new Month(6, "June"),
            new Month(7, "July"),
            new Month(8, "August"),
            new Month(9, "September"),
            new Month(10, "October"),
            new Month(11, "November"),
            new Month(12, "December")
    ));

    @RequestMapping(value = "/month/{monthNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Month getMonthByMonthNumber(@PathVariable int monthNumber) {
        Month foundMonth = null;

        for(Month month : monthList){

            if(month.getNumber() == monthNumber) {
                foundMonth = month;
                break;
            }
        }
            if (foundMonth == null) {
                throw new NotFoundException("Month not found in selection");
            }

            return foundMonth;
    }
}
