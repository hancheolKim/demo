package com.erp.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ItemVO {
    private String itemNum;      // item_num
    private String itemName;     // item_name
    private int price;           // price
    private int costPrice;
    private int itemQuantity;    // item_quantity
    private int defectiveQuantity;
    private Date itemUptDate;    // item_upt_date
    private Date stockDate;
    private String itemNotes;    // item_notes
    private String categoryName; // category_name
}
