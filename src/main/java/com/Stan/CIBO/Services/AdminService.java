package com.Stan.CIBO.Services;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Admin;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {
    public Admin saveAdmin(Admin admin) throws SaveException;
    public List<Admin> getAllAdmins();
    public Admin findById(int id);
}
