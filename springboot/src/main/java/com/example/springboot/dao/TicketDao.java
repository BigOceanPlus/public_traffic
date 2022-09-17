package com.example.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketDao {
    int deleteAllTicket();
    int GetSumOfCost();
}
