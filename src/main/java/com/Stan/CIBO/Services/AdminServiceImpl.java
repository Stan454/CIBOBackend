package com.Stan.CIBO.Services;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Admin;
import com.Stan.CIBO.Models.Restaurant;
import com.Stan.CIBO.Repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private RestaurantServiceImpl restaurantService;
    @Override
    public Admin saveAdmin(Admin admin) throws SaveException {
        if(admin == null || admin.getName().isEmpty() || admin.getPassword().isEmpty() || Objects.isNull(admin.getRestaurant())){
            throw new IllegalArgumentException("All fields need to be filled");
        }
        try{
            Restaurant restaurant = restaurantService.findById(admin.getRestaurant().getId());
            admin.setRestaurant(restaurant);
            return adminRepo.save(admin);
        } catch (Exception e){
            throw new SaveException("Error saving admin: " + e.getMessage());
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public Admin findById(int id) {
        return null;
    }
}
