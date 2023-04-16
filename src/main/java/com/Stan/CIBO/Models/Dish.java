package com.Stan.CIBO.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(int Id, String Name, String Description, double Price, Restaurant Restaurant) {
        this.id = Id;
        this.name = Name;
        this.description = Description;
        this.price = Price;
        this.restaurant = Restaurant;
    }

    public Dish(int Id, String Name, String Description, double Price) {
        this.id = Id;
        this.name = Name;
        this.description = Description;
        this.price = Price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
