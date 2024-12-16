package com.erp.service;

import com.erp.dao.ItemDAO;
import com.erp.dao.PaymentDAO;
import com.erp.vo.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentDAO paymentDAO;

    @Override
    public void insertSales(PaymentVO paymentVO) {
        paymentDAO.insertSales(paymentVO);
    }
}
