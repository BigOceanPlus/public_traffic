package com.example.springboot.entity;

import lombok.Data;

@Data
public class TrainQuery {
    private int currentPage;
    private int pageSize;
    private String trainId;
    private String departure;
    private String terminal;
    private String leaveTime;
    private String arriveTime;
    private int pageNumber;
}