package com.Stan.CIBO.Services;

import com.Stan.CIBO.Exceptions.NotFoundException;
import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Dish;
import com.Stan.CIBO.Models.Restaurant;
import com.Stan.CIBO.Repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepo restaurantRepo;
    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) throws SaveException {
        if(restaurant == null || restaurant.getName().isEmpty()){
            throw new IllegalArgumentException("All fields need to be filled");
        }
        try{
            return restaurantRepo.save(restaurant);
        } catch (Exception e){
            throw new SaveException("Error saving restaurant: " + e.getMessage());
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    @Override
    public Restaurant findById(int id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        if (restaurant == null) {
            throw new NotFoundException("Restaurant with ID " + id + " not found.");
        }
        return restaurant;
    }
}
