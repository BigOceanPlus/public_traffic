package com.example.dachuang.entity;

import lombok.Data;

@Data
public class Road{
    private int id;

    // 两个端点
    private String longitude1;
    private String latitude1;
    private String longitude2;
    private String latitude2;

    // 日期时间
    private String datetime;

    private double flowSpeed;

    /*
    @Override
    public int compareTo(Road o) {
        String[] strs1 = this.Time.split("-");
        String[] strs2 = this.Date.split("-");

        String[] strs3 = o.Time.split("-");
        String[] strs4 = o.Date.split("-");

        int ans1 = (Integer.parseInt(strs1[0]) - 8) * 3600 + Integer.parseInt(strs1[1]) * 60 + Integer.parseInt(strs1[2]) +
                (Integer.parseInt(strs2[0]) - 1970) * 31536000 + (Integer.parseInt(strs2[1]) - 1) * 2678400 + (Integer.parseInt(strs2[2]) - 1) * 86400;

        int ans2 = (Integer.parseInt(strs3[0]) - 8) * 3600 + Integer.parseInt(strs3[1]) * 60 + Integer.parseInt(strs3[2]) +
                (Integer.parseInt(strs4[0]) - 1970) * 31536000 + (Integer.parseInt(strs4[1]) - 1) * 2678400 + (Integer.parseInt(strs4[2]) - 1) * 86400;

        if (ans1 > ans2) return 1;
        else if (ans1 < ans2) return -1;
        else return 0;
    }
    */

}


