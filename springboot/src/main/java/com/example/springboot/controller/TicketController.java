package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.dao.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketDao ticketDao;

    @DeleteMapping
    public Result deleteAllTicket(){
        return Result.success(ticketDao.deleteAllTicket());
    }
    @GetMapping
    public Result GetSumOfCost(){
        return Result.success(ticketDao.GetSumOfCost());
    }
}
