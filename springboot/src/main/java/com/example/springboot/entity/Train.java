package com.example.springboot.entity;

import lombok.Data;

@Data
public class Train {

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
    /* 一等座位票额数 */
    private int firstTicket;
    /* 一等座票价 */
    private int firstCost;
    /* 二等座位票额数 */
    private int secondTicket;
    /* 二等座票价 */
    private int secondCost;

}