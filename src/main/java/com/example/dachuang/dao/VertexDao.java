package com.example.dachuang.dao;

import com.example.dachuang.entity.Vertex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VertexDao {
    List<Vertex> findAll();
    Vertex SearchVertexByName(@Param("name") String name);
}
