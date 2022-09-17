package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.dao.TrainDao;
import com.example.springboot.entity.Ticket;
import com.example.springboot.entity.Train;
import com.example.springboot.entity.TrainQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private TrainDao trainDao;

    @GetMapping("/page")
    public Result findPage(TrainQuery trainQuery){
        int pageSize = trainQuery.getPageSize();
        int currentPage = trainQuery.getCurrentPage();
        if(pageSize <= 0 || currentPage <= 0)
            return Result.error("参数错误");

        int pageNumber = pageSize * (currentPage - 1);
        trainQuery.setPageNumber(pageNumber);

        int total = trainDao.count(trainQuery);
        List<Train> trains = trainDao.findPage(trainQuery);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data",trains);
        hashMap.put("total",total);
        return Result.success(hashMap);
    }

    @GetMapping
    public Result findAll(){
        return Result.success(trainDao.findAll());
    }

    @GetMapping("/msg")
    public Result getMessage(String trainId,String departure,String terminal){
        if(trainId == null || trainId == "" || departure == null || departure == "" || terminal == null || terminal == "")
            return Result.error("参数错误");
        return Result.success(trainDao.getMessage(trainId,departure,terminal));
    }

    @PostMapping
    public Result insert(@RequestBody Ticket ticket,@RequestParam int counts){
        int res = 0;
        for(int i = 0; i < counts; i++)
            res += trainDao.insert(ticket);
        return Result.success(res);
    }
}
