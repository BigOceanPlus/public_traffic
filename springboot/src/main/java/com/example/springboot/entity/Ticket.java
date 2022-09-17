package com.example.springboot.entity;

import lombok.Data;

@Data
public class Ticket {
    private int id;
    /* 车次名 */
    private String trainId;
    /* 始发站 */
    private String departure;
    /* 终点站 */
    private String terminal;
    /* 离站时间 */
    private String leaveTime;
    /* 发站时间 */
    private String arriveTime;
    private int cost;
    private int seat;
    private int type;
}
