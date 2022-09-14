package com.example.springboot.controller;

import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping
    public List<User> findAll(){
        return userDao.findAll();
    }

    @GetMapping("/{id}")
    public List<User> findById(@PathVariable int id){
        return userDao.findById(id);
    }

    @GetMapping("/unique")
    public List<User> findByUser(String name, String password){
        return userDao.findByUser(name, password);
    }

    @PostMapping
    public int insertUser(@RequestBody User user){
        return userDao.insertUser(user);
    }

    @PutMapping
    public int updateUser(@RequestBody User user){
        return userDao.updateUser(user);
    }

    @DeleteMapping("/{name}")
    public int deleteUser(@PathVariable String name){
        return userDao.deleteUser(name);
    }
}
