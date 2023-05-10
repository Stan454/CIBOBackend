package com.Stan.CIBO.Services;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Restaurant;

import java.util.List;

public interface RestaurantService {
    public Restaurant saveRestaurant(Restaurant Restaurant) throws SaveException;
    public List<Restaurant> getAllRestaurants();
    public Restaurant findById(int id);
}
