package com.company.M2ChallengeCristePhillips.controllers;

import com.company.M2ChallengeCristePhillips.models.RandomizedMonth;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomizedMonthController {
    private RandomizedMonth[] randomMonth = {
            new RandomizedMonth(1, "January"),
            new RandomizedMonth(2, "February"),
            new RandomizedMonth(3, "March"),
            new RandomizedMonth(4, "April"),
            new RandomizedMonth(4, "May"),
            new RandomizedMonth(6, "June"),
            new RandomizedMonth(7, "July"),
            new RandomizedMonth(8, "August"),
            new RandomizedMonth(9, "September"),
            new RandomizedMonth(10, "October"),
            new RandomizedMonth(11, "November"),
            new RandomizedMonth(12, "December")
    };

    @RequestMapping(value = "/randomMonth", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RandomizedMonth getRandomMonth() {
        Random random = new Random();

        return randomMonth[random.nextInt(12)];
    }
}
