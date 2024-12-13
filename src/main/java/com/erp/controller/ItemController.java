package com.erp.controller;

import com.erp.service.ItemService;
import com.erp.util.PagingUtil;
import com.erp.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getItemList(@RequestParam(defaultValue = "1") int pageNum,
                                                           @RequestParam(defaultValue = "1") int order,
                                                           @RequestParam(defaultValue = "") String keyfield,
                                                           @RequestParam(defaultValue = "") String keyword) {
        // URL 디코딩을 위한 처리
        try {
            if (!keyword.isEmpty()) {
                keyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8.name());
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error decoding keyword: " + e.getMessage());
        }

        // 검색에 필요한 파라미터를 맵에 추가
        Map<String, Object> map = new HashMap<>();
        map.put("keyfield", keyfield);
        map.put("keyword", keyword);
        System.out.println("Search map created: " + map);

        // 전체, 검색 레코드 수
        int count = itemService.getItemCount(map);
        System.out.println("Total record count: " + count);

        // 페이지 처리
        PagingUtil page = new PagingUtil(keyfield, keyword, pageNum, count, 15, 10, "list");
        System.out.println("PagingUtil created: startRow=" + page.getStartRow() + ", endRow=" + page.getEndRow() + ", page=" + page.getPage());

        List<ItemVO> items = null;
        if (count > 0) {
            System.out.println("Records found, preparing to fetch items.");
            map.put("order", order);
            map.put("start", page.getStartRow());
            map.put("end", page.getEndRow());
            System.out.println("Map for fetching items: " + map);

            items = itemService.getAllItems(map);
            System.out.println("Items fetched: " + items);
        } else {
            System.out.println("No records found.");
        }

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("page", page.getPage());
        response.put("count", count);
        System.out.println("Response data prepared: " + response);

        return ResponseEntity.ok(response);
    }


}
