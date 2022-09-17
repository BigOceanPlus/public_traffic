package com.example.springboot.dao;

import com.example.springboot.entity.Train;
import com.example.springboot.entity.TrainMsg;
import com.example.springboot.entity.TrainQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrainDao {

    int count(TrainQuery trainQuery);
    List<Train> findPage(TrainQuery trainQuery);
    List<Train> findAll();
    TrainMsg getMessage(@Param("trainId") String trainId,@Param("departure") String departure,@Param("terminal") String terminal);
}
