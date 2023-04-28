package com.example.dachuang.controller;

import com.example.dachuang.common.Result;
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
    Result Register(@RequestBody User user){
        if(user.getUsername().length() == 0 || user.getPassword().length() == 0)
            return Result.error("请输入用户名或密码");

        if(userDao.FindUsername(user.getUsername()) != null)
            return Result.error("用户已存在");

        userDao.AddUser(user.getUsername(), user.getPassword());
        return Result.success("注册成功");
    }

    @PostMapping("/login")
    Result Login(@RequestBody User user){
        List<User> result = userDao.FindUser(user.getUsername(), user.getPassword());
        if(result != null && result.size() != 0) return Result.success("登陆成功");
        else return Result.error("用户名或密码错误");
    }

    @GetMapping
    List<User> FindAll(){
        return userDao.FindAll();
    }
}
