package com.example.dachuang.dao;

import com.example.dachuang.entity.ModelOutput;
import com.example.dachuang.entity.Point;
import javafx.util.Pair;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ModelDao {
    ModelOutput getTargetRankByCurrent_loc(@Param("uid") Integer uid,
                                           @Param("current_loc") String current_loc,
                                           @Param("current_time") int current_time);

    int getPoiByIndex(@Param("index") Integer index);

    String getPointByPoi(@Param("poi") Integer poi);
}
