package com.company;

public class App {
    public static void main(String[] args) {
        Farmer farmer = new Farmer();
        Constable constable = new Constable();
        Warrior warrior = new Warrior();

        System.out.println(warrior.getAbilities().toString());
        System.out.println(farmer.getAbilities().toString());
        System.out.println(constable.getAbilities().toString());
    }
}
