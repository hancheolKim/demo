package com.erp.service;

import com.erp.dao.ItemDAO;
import com.erp.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemDAO itemDAO;

    @Override
    public ItemVO getItemById(String itemNum) {
        return itemDAO.getItemById(itemNum);
    }

    @Override
    public List<ItemVO> getAllItems() {
        return itemDAO.getAllItems();
    }

    @Override
    public void insertItem(ItemVO item) {
        itemDAO.insertItem(item);
    }

    @Override
    public void updateItem(ItemVO item) {
        itemDAO.updateItem(item);
    }

    @Override
    public void deleteItem(String itemNum) {
        itemDAO.deleteItem(itemNum);
    }
}
