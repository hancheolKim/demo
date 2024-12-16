package com.erp.dao;

import com.erp.vo.PaymentVO;

public interface PaymentDAO {
    void insertSales(PaymentVO paymentVO); // 결제 정보 삽입
}
