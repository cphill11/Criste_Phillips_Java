package com.company;

public class PointOfSaleIceCream {
    private String flavor = "";
    private String containerSize = "";
    private String retailCost = "";
    private String expirationDate = "";
    private String ingredients = "";

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(String containerSize) {
        this.containerSize = containerSize;
    }

    public String getRetailCost() {
        return retailCost;
    }

    public void setRetailCost(String retailCost) {
        this.retailCost = retailCost;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void purchaseDetails() {
        System.out.println("The flavor of icecream being purchased is " + flavor + ". "  + "The container size is " + containerSize + " at a cost of " + retailCost + ". " + "This icecream expires on " + expirationDate + ". Ingredients include: " + ingredients + ".");
    }
    public void addedNuts(boolean yesPlz) {
        System.out.println("Nuts included with order? " + yesPlz);

    }
    public void addedSprinkles(boolean yesPlz) {
        System.out.println("Sprinkles included with order? " + yesPlz);

    }

}


