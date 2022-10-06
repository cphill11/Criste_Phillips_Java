package com.company;

public class Warrior extends Players {

    private int shieldStrength = 100;

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    public Warrior() {
        // ability to attack another player
        super();
        // set specific params
        this.setName("Bret");
        this.setStrength(75);
        this.setHealth(100);
        this.setStamina(100);
        this.setSpeed(50);
        this.setAttackPower(10);

    }
}
