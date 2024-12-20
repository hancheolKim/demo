package com.erp.dao;

import com.erp.vo.PaymentVO;

import java.util.List;
import java.util.Map;

public interface PaymentDAO {
    void insertSales(PaymentVO paymentVO); // 결제 정보 삽입
    //결제내역
    List<PaymentVO> getPayList();
    //결제내역 카운트
    int getPayCount();

}
