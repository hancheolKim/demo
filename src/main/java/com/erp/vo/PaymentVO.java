package com.erp.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class PaymentVO {

    private String paymentId;
    private String itemNum;       // 상품 번호
    private int salesQuantity;    // 판매 수량
    private BigDecimal totalPrice; // 총 가격
    private BigDecimal total_cost_price;
    private Date salesRegDate;    // 판매 등록 날짜
    private String payType;       // 결제 유형
    private int userNum;          // 사용자 번호
}
