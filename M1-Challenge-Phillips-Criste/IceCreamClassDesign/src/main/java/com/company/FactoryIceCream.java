package com.company;

public class FactoryIceCream {
    private String flavor = "";
    private String containerSize = "";
    private String productionCost = "";
    private String productionTime = "";
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

    public String getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(String productionCost) {
        this.productionCost = productionCost;
    }

    public String getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(String productionTime) {
        this.productionTime = productionTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void productionDetails() {
        System.out.println("The flavor of icecream being sold is " + flavor + ". " + "The container size is " + containerSize + " produced at a cost of " + productionCost + ". " + "This icecream takes appoximately " + productionTime + " to produce. Ingredients include: " + ingredients + ".");
    }
    public void addedNuts(boolean yesPlz) {
        System.out.println("Nuts added to product? " + yesPlz);

    }
    public void addedChocolate(boolean yesPlz) {
        System.out.println("Chocolate added to product? " + yesPlz);
    }
}

