package com.example.dachuang.controller;

import com.example.dachuang.dao.RoadDao;
import com.example.dachuang.dao.VertexDao;
import com.example.dachuang.entity.RangeRequest;
import com.example.dachuang.entity.Road;
import com.example.dachuang.entity.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/road")
public class RoadController {
    @Autowired
    RoadDao roadDao;
    @Autowired
    VertexDao vertexDao;

    @GetMapping
    public List<Road> findAll(){
        return roadDao.findAll();
    }
    @PostMapping("range")
    public List<Road> findRange(@RequestBody RangeRequest rangeRequest){

        if(rangeRequest.getVertex1().isFlag())
            rangeRequest.setVertex1(vertexDao.SearchVertexByName(rangeRequest.getVertex1().getName()));

        if(rangeRequest.getVertex2().isFlag())
            rangeRequest.setVertex2(vertexDao.SearchVertexByName(rangeRequest.getVertex2().getName()));

        return roadDao.searchByRange(rangeRequest.getVertex1(),rangeRequest.getVertex2(),
                                    rangeRequest.getDatetime1(), rangeRequest.getDatetime2());
    }
}
