package com.example.dachuang.entity;

import lombok.Data;

@Data
public class RangeRequest {
    private boolean flag; // false：经纬度 true：名称
    private Vertex vertex1;
    private Vertex vertex2;
    private String datetime1;
    private String datetime2;
}
