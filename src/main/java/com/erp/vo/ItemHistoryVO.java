package com.erp.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ItemHistoryVO {
    private int historyNum;
    private String itemNum;      // item_num
    private String transactionType;
    private int transactionQuantity;
    private Date transactionDate;
    private String transactionNotes;
}
