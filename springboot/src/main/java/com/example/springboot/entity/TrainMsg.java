package com.example.springboot.entity;

import lombok.Data;

@Data
public class TrainMsg {
    /* 一等座位票额数 */
    private int firstTicket;
    /* 一等座票价 */
    private int firstCost;
    /* 二等座位票额数 */
    private int secondTicket;
    /* 二等座票价 */
    private int secondCost;
}
