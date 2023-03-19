package com.example.dachuang.dao;

import com.example.dachuang.entity.Road;
import com.example.dachuang.entity.Vertex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoadDao {
    List<Road> findAll();
    List<Road> searchByRange(@Param("vertex1") Vertex vertex1, @Param("vertex2") Vertex vertex2,
                             @Param("datetime1") String datetime1, @Param("datetime2") String datetime2);
}
