package com.Stan.CIBO.Services;

import java.util.ArrayList;

import com.Stan.CIBO.Exceptions.NotFoundException;
import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Dish;
import com.Stan.CIBO.Models.Restaurant;
import com.Stan.CIBO.Repositories.DishRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DishServiceImplTest {

    @Mock
    private DishRepo dishRepoMock;

    @InjectMocks
    private DishServiceImpl dishService;

    private List<Dish> dishList;
    Restaurant restaurant = new Restaurant(0,"Giorno's pizza");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dishList = new ArrayList<>();
        dishList.add(new Dish(1, "Pizza", "Better with cheese", 10.0, restaurant));
        dishList.add(new Dish(2, "Spaghetti Bowl", "Bigger and better", 20.0, restaurant));
    }

    @Test
    void should_saveDish() {
        //Arrange
        Dish dish = new Dish(3,"Spaghetti","European noodles", 10.99, restaurant);

        //Act
        when(dishRepoMock.save(dish)).thenReturn(dish);

        //Assert
        Dish savedDish = null;
        try {
            savedDish = dishService.saveDish(0, dish);
        } catch (SaveException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Spaghetti", savedDish.getName());
        assertEquals("European noodles", savedDish.getDescription());
        assertEquals(10.99, savedDish.getPrice());
    }

    @Test
    void shouldNot_saveDish() {
        //Arrange
        Dish dish = new Dish(3, "", "European noodles", 10.99, restaurant);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> dishService.saveDish(0,dish));
    }


    @Test
    void should_getAllDishes() {
        //Act
        when(dishRepoMock.findAll()).thenReturn(dishList);
        List<Dish> returnedList = dishService.getAllDishes();

        //Assert
        assertEquals(dishList.size(), returnedList.size());
    }

    @Test
    void should_findById() {

        //Act
        Mockito.when(dishRepoMock.findById(1)).thenReturn(Optional.of(dishList.get(0)));
        Dish returnedDish = dishService.findById(1);

        //Assert
        assertEquals(1, returnedDish.getId());
        assertEquals("Pizza", returnedDish.getName());
        assertEquals("Better with cheese", returnedDish.getDescription());
        assertEquals(10.0, returnedDish.getPrice());
    }

    @Test
    void shouldNot_findById() {

        //Act
        Mockito.when(dishRepoMock.findById(7)).thenReturn(Optional.empty());
        int returnedDish = 10;
        //Assert
        assertThrows(NotFoundException.class, () -> {dishService.findById(returnedDish);});
    }



}