package com.example.springboot.dao;

import com.example.springboot.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketDao {
    int deleteAllTicket();
    int GetSumOfCost();
    List<Ticket> findAdd();
    int deleteById(int id);
    int edit(Ticket ticket);
    //int getPrice(Ticket ticket);
}
