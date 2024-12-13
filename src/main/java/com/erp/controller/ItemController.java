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
            log.error("Error decoding keyword: {}", e.getMessage());
        }

        // 검색에 필요한 파라미터를 맵에 추가
        Map<String, Object> map = new HashMap<>();
        map.put("keyfield", keyfield);
        map.put("keyword", keyword);

        // 전체, 검색 레코드 수
        int count = itemService.getItemCount(map);

        // 페이지 처리
        PagingUtil page = new PagingUtil(pageNum, count, 15, 10); // 한 페이지에 15개 아이템, 10개 페이지 버튼

        List<ItemVO> items = null;
        if (count > 0) {
            map.put("order", order);
            map.put("start", page.getStartRow()); // 0-based index로 startRow를 설정
            map.put("end", page.getEndRow());     // 0-based index로 endRow를 설정

            items = itemService.getAllItems(map);
        }

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("items", items);           // 아이템 목록
        response.put("pageHtml", page.getPageHtml()); // 생성된 페이지 버튼 HTML
        response.put("count", count);           // 전체 아이템 수
        response.put("pageNum", pageNum);      // 현재 페이지 번호
        response.put("totalCount", (int) Math.ceil((double) count / 15)); // 전체 페이지 수

        return ResponseEntity.ok(response);
    }

}
