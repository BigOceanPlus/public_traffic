package com.example.dachuang.controller;

import com.example.dachuang.dao.UserDao;
import com.example.dachuang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDao userDao;
    @PostMapping("/register")
    boolean Register(@RequestBody User user){
        if(user.getUsername().length() == 0 || user.getPassword().length() == 0 || userDao.FindUsername(user.getUsername()) != null)
        return false;

        userDao.AddUser(user.getUsername(), user.getPassword());
        return true;
    }

    @PostMapping("/login")
    boolean Login(@RequestBody User user){
        List<User> result = userDao.FindUser(user.getUsername(), user.getPassword());
        if(result != null && result.size() != 0) return true;
        else return false;
    }

    @GetMapping
    List<User> FindAll(){
        return userDao.FindAll();
    }
}
