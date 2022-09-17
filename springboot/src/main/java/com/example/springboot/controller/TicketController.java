package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.dao.TicketDao;
import com.example.springboot.entity.Ticket;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketDao ticketDao;

    @DeleteMapping
    public Result deleteAllTicket(){
        return Result.success(ticketDao.deleteAllTicket());
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable int id){
        int flag = ticketDao.deleteById(id);
        if(flag > 0)
            return Result.success(flag);
        else
            return Result.error("删除失败");
    }
    @GetMapping("/sum")
    public Result GetSumOfCost(){
        return Result.success(ticketDao.GetSumOfCost());
    }
    @GetMapping
    public Result findAll(){
        return Result.success(ticketDao.findAdd());
    }
    @PutMapping
    public Result edit(@RequestBody Ticket ticket){
        return Result.success(ticketDao.edit(ticket));
    }
    /*@GetMapping("/price")
    public Result getPrice(Ticket ticket){
        return Result.success(ticketDao.getPrice(ticket));
    }*/
}
