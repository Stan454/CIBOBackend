package com.Stan.CIBO.Controllers;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Dish;
import com.Stan.CIBO.Services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<String> add(@PathVariable int restaurantId,@RequestBody Dish dish) {
        try {
            dishService.saveDish(restaurantId, dish);
            return ResponseEntity.status(HttpStatus.CREATED).body("New Dish added");
        } catch (IllegalArgumentException | SaveException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
        }
    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<String> deleteDish(@PathVariable int dishId) {
        try {
            dishService.deleteDish(dishId);
            return ResponseEntity.status(HttpStatus.OK).body("Dish with ID " + dishId + " deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
        }
    }

    @GetMapping("/{restaurantId}/dishes")
    public ResponseEntity<List<Dish>> getAllDishesByRestaurant(@PathVariable int restaurantId){
        List<Dish> dishes = dishService.getAllDishesByRestaurantId(restaurantId);
        if(dishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(dishes, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable int id) {
        Dish dish = dishService.findById(id);
        if (dish != null) {
            return ResponseEntity.ok(dish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
