package com.erp.controller;

import com.erp.service.ItemService;
import com.erp.util.PagingUtil;
import com.erp.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getItemList(@RequestParam(defaultValue="1") int pageNum,
                                                           @RequestParam(defaultValue="1") int order,
                                                           @RequestParam(defaultValue="") String category,
                                                           String keyfield,String keyword) {
        Map<String,Object> map =
                new HashMap<String,Object>();
        map.put("category", category);
        map.put("keyfield", keyfield);
        map.put("keyword", keyword);

        //전체,검색 레코드수
        int count = itemService.getItemCount(map);

        //페이지 처리
        PagingUtil page =
                new PagingUtil(keyfield,keyword,pageNum,
                        count,20,10,"list",
                        "&category="+category+"&order="+order);
        List<ItemVO> items = null;
        if(count > 0) {
            map.put("order", order);
            map.put("start", page.getStartRow());
            map.put("end", page.getEndRow());

            items = itemService.getAllItems(map);
        }
        Map<String,Object> response = new HashMap<String,Object>();
        response.put("items", items);
        response.put("page", page.getPage());
        response.put("count", count);

        return ResponseEntity.ok(response);
    }
}
