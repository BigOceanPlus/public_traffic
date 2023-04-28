package com.example.dachuang.entity;

import lombok.Data;

@Data
public class Road{
    private int id;

    // 两个端点
    private String longitude1;
    private String latitude1;
    private String longitude2;
    private String latitude2;

    // 日期时间
    private String datetime;
    private double flowSpeed;

}


