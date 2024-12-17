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


    /*********************
     * 제품 리스트 
     *********************/
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
            items = itemService.getAllItems(map);
            System.out.println(items);
        }

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("items", items);           // 아이템 목록
        response.put("count", count);           // 전체 아이템 수
        response.put("pageNum", pageNum);      // 현재 페이지 번호

        return ResponseEntity.ok(response);
    }

    /*********************
     * 재고 리스트
     *********************/

    @GetMapping("/stocklist")
    public ResponseEntity<Map<String, Object>> getStockList(@RequestParam(defaultValue = "1") int pageNum,
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
            map.put("start", page.getStartRow()); // 0-based index로 startRow를 설정
            items = itemService.getStock(map);
            System.out.println(items);
        }

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("items", items);           // 아이템 목록
        response.put("count", count);           // 전체 아이템 수
        response.put("pageNum", pageNum);      // 현재 페이지 번호

        return ResponseEntity.ok(response);
    }
    /*********************
     * 아이템 번호 생성
     *********************/
    @PostMapping("/generate-item-num")
    public ResponseEntity<Map<String, Object>> getItemNum(@RequestParam String categoryName) {

        try {
            // 카테고리 이름에 해당하는 마지막 아이템 번호 가져오기
            String lastItemNum = itemService.getLastItemByCategory(categoryName);

            // 아이템 번호의 문자 부분(앞 2자) 추출
            String prefix = lastItemNum.substring(0, 2); // 'EA' 같은 앞 2글자 추출

            // 아이템 번호에서 숫자 부분 추출 (예: EA010에서 010 추출)
            String numPart = lastItemNum.substring(2); // '010'
            int newNum = Integer.parseInt(numPart) + 1; // 숫자 부분 1 증가

            // 새로운 아이템 번호 생성 (추출된 문자 + 새로운 번호)
            String newItemNum = prefix + String.format("%03d", newNum); // 예: 'EA011'

            // 응답 데이터
            Map<String, Object> response = new HashMap<>();
            response.put("newItemNum", newItemNum);  // 생성된 아이템 번호

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error generating item number: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error generating item number");
            return ResponseEntity.status(500).body(response);
        }
    }



    /*********************
     * 아이템 등록 페이지
     *********************/
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addItem(@RequestBody ItemVO itemVO) {
        try {
            System.out.println("itemVO에 포함된 데이터 : " + itemVO);
            // 전달받은 아이템 번호로 아이템 추가
            itemService.insertItem(itemVO);

            // 응답 데이터
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Item added successfully");
            response.put("item", itemVO);  // 추가된 아이템 정보

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error adding item: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error adding item");
            return ResponseEntity.status(500).body(response);
        }
    }


    /*********************
     * 아이템 삭제
     *********************/
    @PostMapping("/delItem")
    public ResponseEntity<Map<String, Object>> deleteItem(@RequestBody Map<String, String> requestBody) {
        try {
            String itemNum = requestBody.get("itemNum"); // JSON에서 itemNum 추출

            // 전달받은 아이템 번호로 아이템 삭제
            itemService.deleteItem(itemNum);

            // 응답 데이터
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Item deleted successfully"); // 삭제 성공 메시지로 변경

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error deleting item: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error deleting item"); // 삭제 오류 메시지로 변경
            return ResponseEntity.status(500).body(response);
        }
    }



}
