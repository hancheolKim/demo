package com.erp.controller;

import com.erp.service.ItemService;
import com.erp.util.PagingUtil;
import com.erp.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                                                           @RequestParam(defaultValue = "") String category,
                                                           String keyfield, String keyword) {
        log.debug("Received request: pageNum={}, order={}, category={}, keyfield={}, keyword={}",
                pageNum, order, category, keyfield, keyword);

        Map<String, Object> map = new HashMap<>();
        map.put("category", category);
        map.put("keyfield", keyfield);
        map.put("keyword", keyword);
        log.debug("Search map created: {}", map);

        // 전체, 검색 레코드 수
        int count = itemService.getItemCount(map);
        log.debug("Total record count: {}", count);

        // 페이지 처리
        PagingUtil page = new PagingUtil(keyfield, keyword, pageNum, count, 20, 10, "list",
                "&category=" + category + "&order=" + order);
        log.debug("PagingUtil created: startRow={}, endRow={}, page={}", page.getStartRow(), page.getEndRow(), page.getPage());

        List<ItemVO> items = null;
        if (count > 0) {
            log.debug("Records found, preparing to fetch items.");
            map.put("order", order);
            map.put("start", page.getStartRow());
            map.put("end", page.getEndRow());
            log.debug("Map for fetching items: {}", map);

            items = itemService.getAllItems(map);
            log.debug("Items fetched: {}", items);
        } else {
            log.debug("No records found.");
        }

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("page", page.getPage());
        response.put("count", count);
        log.debug("Response data prepared: {}", response);

        return ResponseEntity.ok(response);
    }

}
