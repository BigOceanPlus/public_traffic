package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.User;
import com.example.springboot.entity.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(user.getName() == null || user.getPassword() == null) return Result.error("参数错误");
        List<User> res = userDao.findByUser(user.getName(), user.getPassword());
        if(res == null || res.size() == 0) return Result.error("用户名或密码错误");
        //if(res.size() == 0) throw new RuntimeException("用户名或密码错误");
        return Result.success(res);
    }

    @GetMapping
    public Result findAll(){
        return Result.success(userDao.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable int id){
        return Result.success(userDao.findById(id));
    }

    @GetMapping("/unique")
    public Result findByUser(String name, String password){
        return Result.success(userDao.findByUser(name, password));
    }

    @GetMapping("/page")
    public Result findPage(UserQuery userquery){
        if(userquery.getCurrentPage() <= 0 || userquery.getPageSize() <= 0)
            return Result.error("参数错误");

        int pageSize = userquery.getPageSize();
        int currentPage = userquery.getCurrentPage();
        int pageNumber = pageSize * (currentPage - 1);
        userquery.setPageNumber(pageNumber);

        int total = userDao.count(userquery);
        List<User> users = userDao.findPage(userquery);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data",users);
        hashMap.put("total",total);
        return Result.success(hashMap);
    }

    @PostMapping
    public Result insertUser(@RequestBody User user){
        return Result.success(userDao.insertUser(user));
    }

    @PutMapping
    public Result updateUser(@RequestBody User user){
        return Result.success(userDao.updateUser(user));
    }

    @DeleteMapping("/{name}")
    public Result deleteUser(@PathVariable String name){
        return Result.success(userDao.deleteUser(name));
    }
}
