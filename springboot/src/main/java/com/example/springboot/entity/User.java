package com.example.springboot.entity;

// import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String password;
    private int type;
}
