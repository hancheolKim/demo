package com.erp.service;

import com.erp.vo.ItemVO;

import java.util.List;
import java.util.Map;

public interface ItemService {
    // 아이템 조회
    ItemVO getItemById(String itemNum);

    int updateItemQuantity(String itemNum, int quantity);

    // 모든 아이템 수량 조회
    int getItemCount(Map<String,Object> map);

    // 모든 아이템 조회
    List<ItemVO> getAllItems(Map<String, Object> map);

    List<ItemVO> getStock(Map<String,Object> map);

    //해당 카테고리의 마지막 아이템 넘 구하기
    String getLastItemByCategory(String categoryName);

    // 아이템 추가
    void insertItem(ItemVO item);

    // 아이템 수정
    void updateItem(ItemVO item);

    // 아이템 삭제
    void deleteItem(String itemNum);
}
