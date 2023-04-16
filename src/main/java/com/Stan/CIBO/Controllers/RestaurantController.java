package com.Stan.CIBO.Controllers;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Restaurant;
import com.Stan.CIBO.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Restaurant Restaurant) {
        try {
            restaurantService.saveRestaurant(Restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body("New Restaurant added");
        } catch (IllegalArgumentException | SaveException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> Restaurants = restaurantService.getAllRestaurants();
        if(Restaurants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(Restaurants, HttpStatus.OK);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id) {
        Restaurant Restaurant = restaurantService.findById(id);
        if (Restaurant != null) {
            return ResponseEntity.ok(Restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}