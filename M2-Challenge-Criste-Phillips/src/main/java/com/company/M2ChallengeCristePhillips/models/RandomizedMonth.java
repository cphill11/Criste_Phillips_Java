package com.company.M2ChallengeCristePhillips.models;

import java.util.Objects;

public class RandomizedMonth {

    private int number;
    private String name;

    public RandomizedMonth () {
    }

    public RandomizedMonth(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RandomizedMonth)) return false;
        RandomizedMonth that = (RandomizedMonth) o;
        return getNumber() == that.getNumber() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getName());
    }

    @Override
    public String toString() {
        return "RandomizedMonth{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
