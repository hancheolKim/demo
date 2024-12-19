package com.erp.service;

import com.erp.vo.PaymentVO;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    void insertSales(PaymentVO paymentVO); // 결제 정보 삽입
    //결제내역
    List<PaymentVO> getPayList(Map<String,Object> map);
    //결제내역 카운트
    int getPayCount();
}
