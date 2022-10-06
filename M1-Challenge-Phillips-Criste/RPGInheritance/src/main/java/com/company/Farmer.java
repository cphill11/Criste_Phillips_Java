package com.company;

public class Farmer extends Players {

    private boolean plowing = false;
    private boolean harvesting = false;

    public boolean isPlowing() {
        return plowing;
    }

    public void setPlowing(boolean plowing) {
        this.plowing = plowing;
    }

    public boolean isHarvesting() {
        return harvesting;
    }

    public void setHarvesting(boolean harvesting) {
        this.harvesting = harvesting;
    }

    public Farmer() {
        // ability to attack another player
        super();
        // set specific params
        this.setName("Charlie");
        this.setStrength(75);
        this.setHealth(100);
        this.setStamina(75);
        this.setSpeed(10);
        this.setAttackPower(1);
    }
}
