package com.Stan.CIBO.Models;

public enum DishCategory {
    Drinks("Drinks"),
    Appetizers("Appetizers"),
    Main_Courses("Main Courses"),
    Desserts("Desserts");

    private final String categoryName;

    private DishCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
