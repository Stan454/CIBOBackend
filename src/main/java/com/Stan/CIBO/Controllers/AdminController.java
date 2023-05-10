package com.Stan.CIBO.Controllers;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Admin;
import com.Stan.CIBO.Models.Dish;
import com.Stan.CIBO.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/newAdmin")
    public ResponseEntity<String> add(@RequestBody Admin admin) {
        try {
            adminService.saveAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("New Admin added");
        } catch (IllegalArgumentException | SaveException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
        }
    }
}
