package com.example.dachuang.controller;

import com.example.dachuang.common.Result;
import com.example.dachuang.dao.ModelDao;
import com.example.dachuang.entity.ModelInput;
import com.example.dachuang.entity.ModelOutput;
import com.example.dachuang.entity.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/model")
@Slf4j
public class ModelController {
    @Autowired
    ModelDao modelDao;

    @PostMapping
    Result modelHandler(@RequestBody ModelInput model){
        ModelOutput modelOutput = modelDao.getTargetRankByCurrent_loc(model.getUid(), model.getCurrent_loc(), model.getCurrent_time());
        String[] strs = modelOutput.getRanking().split("-");
        log.info("{}", modelOutput);

        List<Point> list = new ArrayList<>();
        for(String s : strs){
            String raw = getPoint(getPoi(Integer.parseInt(s)));
            String[] raws = raw.split("-");
            list.add(new Point(Double.parseDouble(raws[0]),Double.parseDouble(raws[1])));
        }

        modelOutput.setPoints(list);
        return Result.success(modelOutput);
    }

    private Integer getPoi(Integer index){
        return modelDao.getPoiByIndex(index);
    }
    private String getPoint(Integer poi){
        return modelDao.getPointByPoi(poi);
    }
}
