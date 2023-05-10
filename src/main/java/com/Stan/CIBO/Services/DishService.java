package com.Stan.CIBO.Services;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Dish;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DishService {
    public Dish saveDish(int restaurantId,Dish dish) throws SaveException;
    public List<Dish> getAllDishesByRestaurantId(int restaurantId);
    public Dish findById(int id);
    public void deleteDish(int dishId);
}
