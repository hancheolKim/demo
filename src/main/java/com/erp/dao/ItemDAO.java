package com.erp.dao;

import com.erp.vo.ItemVO;
import java.util.List;

public interface ItemDAO {
    // 아이템 조회
    ItemVO getItemById(String itemNum);

    // 모든 아이템 조회
    List<ItemVO> getAllItems();

    // 아이템 추가
    void insertItem(ItemVO item);

    // 아이템 수정
    void updateItem(ItemVO item);

    // 아이템 삭제
    void deleteItem(String itemNum);
}
