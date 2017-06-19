package edu.mum.coffee.domain;

public enum ProductType {
    BREAKFAST("Breakfast"), LUNCH("Lunch"), DINNER("Dinner");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
