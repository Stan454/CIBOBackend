package com.Stan.CIBO.Services;

import com.Stan.CIBO.Exceptions.NotFoundException;
import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Dish;
import com.Stan.CIBO.Models.DishCategory;
import com.Stan.CIBO.Models.Restaurant;
import com.Stan.CIBO.Repositories.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepo dishrepo;

    @Autowired
    private RestaurantServiceImpl restaurantService;
    @Override
    public Dish saveDish(int restaurandId, Dish dish) throws SaveException{
        if(dish == null || dish.getName().isEmpty() || dish.getDescription().isEmpty() || Objects.isNull(dish.getDishCategory())|| Objects.isNull(restaurandId) ||Objects.isNull(dish.getPrice())){
            throw new IllegalArgumentException("All fields need to be filled");
        }
        try{
            Restaurant restaurant = restaurantService.findById(restaurandId);
            dish.setRestaurant(restaurant);
            return dishrepo.save(dish);
        } catch (Exception e){
            throw new SaveException("Error saving dish: " + e.getMessage());
        }
    }

    @Override
    public List<Dish> getAllDishesByRestaurantId(int restaurantId) {
        List<Dish> dishes = dishrepo.findAll();
        List<Dish> restaurantDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getRestaurant() != null && dish.getRestaurant().getId() == restaurantId) {
                restaurantDishes.add(dish);
            }
        }
        return restaurantDishes;
    }

    @Override
    public Dish findById(int id) {
        Dish dish = dishrepo.findById(id).orElse(null);
        if (dish == null) {
            throw new NotFoundException("Dish with ID " + id + " not found.");
        }
        return dish;
    }
}
