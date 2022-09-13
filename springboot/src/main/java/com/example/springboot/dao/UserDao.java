package com.example.springboot.dao;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> findAll();
    List<User> findById(int id);
    List<User> findByUser(@Param("name") String name, @Param("password") String password);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(String name);
}
