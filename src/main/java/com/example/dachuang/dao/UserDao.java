package com.example.dachuang.dao;

import com.example.dachuang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    int AddUser(@Param("username") String username, @Param("password") String password);
    List<User> FindUser(@Param("username") String username, @Param("password") String password);
    List<User> FindAll();
    Integer FindUsername(@Param("username") String username);
}
