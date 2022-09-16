package com.example.springboot.entity;

import lombok.Data;

@Data
public class UserQuery {
    private int currentPage;
    private int pageSize;
    private String id;
    private String name;
    private String password;
    private String type;
    private int pageNumber;
}
