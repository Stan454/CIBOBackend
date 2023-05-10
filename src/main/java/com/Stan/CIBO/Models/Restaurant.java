package com.Stan.CIBO.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Dish> dishes;
    @OneToMany(mappedBy = "restaurant")
    private Set<Admin> admins;
    public Restaurant(){

    }
    public Restaurant(int Id, String Name) {
        this.id = Id;
        this.name = Name;
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
