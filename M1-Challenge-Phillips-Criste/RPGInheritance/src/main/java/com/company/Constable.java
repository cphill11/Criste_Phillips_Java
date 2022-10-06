package com.company;
import java.util.ArrayList;

public class Constable extends Players {

    private String jurisdiction = "";

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Constable() {
        // ability to attack another player
        super();
        // set specific params
        this.setName("Bob");
        this.setStrength(60);
        this.setHealth(100);
        this.setStamina(60);
        this.setSpeed(20);
        this.setAttackPower(5);

        ArrayList<String> arrest = this.getAbilities();
        arrest.add("Arrested another character");

        this.setAbilities(arrest);
    }



}
