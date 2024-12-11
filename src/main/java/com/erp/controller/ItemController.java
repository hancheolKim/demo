package com.erp.controller;

import com.erp.service.ItemService;
import com.erp.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<List<ItemVO>> getItemList(@RequestParam Map<String, String> filters) {
        // 필터 데이터를 서비스로 전달하여 리스트 조회
        List<ItemVO> items = itemService.getAllItems();

        // 응답 데이터 반환
        return ResponseEntity.ok(items);
    }
}
