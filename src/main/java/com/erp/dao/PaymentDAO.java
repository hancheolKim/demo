package com.erp.dao;

import com.erp.vo.ItemVO;
import com.erp.vo.PaymentVO;

import java.util.List;
import java.util.Map;

public interface PaymentDAO {
    void insertSales(PaymentVO paymentVO); // 결제 정보 삽입
    //결제내역
    List<PaymentVO> getPayList();
    //결제내역 카운트
    int getPayCount();
    //결제 대상 아이템num 구하기
    List<ItemVO> getItemNumList();
    //결제내역 직접추가 (날짜 넣을때 직접 명시해야함.)
    void selfInsertSales(PaymentVO paymentVO);
}
