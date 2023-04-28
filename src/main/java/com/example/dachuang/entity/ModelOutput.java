package com.example.dachuang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelOutput {
    private Integer target;
    private String ranking;
    private List<Point> points;
}
