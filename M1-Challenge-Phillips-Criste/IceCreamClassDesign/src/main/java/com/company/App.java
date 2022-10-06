package com.company;

public class App {
    public static void main(String[] args) {
        com.company.FactoryIceCream factory = new com.company.FactoryIceCream();
        com.company.PointOfSaleIceCream pos = new  com.company.PointOfSaleIceCream();

        factory.setFlavor("Strawberry");
        factory.setContainerSize("4oz");
        factory.setProductionCost("0.75 cents");
        factory.setProductionTime("15 minutes");
        factory.setIngredients("Strawberry, cream, ice, red dye, coconut, almond milk");

        pos.setFlavor("Strawberry");
        pos.setContainerSize("4oz");
        pos.setRetailCost("$4.75");
        pos.setExpirationDate("12/12/22");
        pos.setIngredients("Strawberry, cream, ice, red dye, coconut, almond milk");

        factory.productionDetails();
        factory.addedNuts(true);
        factory.addedChocolate(false);



        pos.purchaseDetails();
        pos.addedNuts(false);
        pos.addedSprinkles(true);

    }
}

