package com.erp.controller;

import com.erp.service.UserService;
import com.erp.vo.UserVO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ERPController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String id = credentials.get("id");
        String password = credentials.get("password");

        Map<String, Object> response = new HashMap<>();
        if ("user123".equals(id) && "pass123".equals(password)) { // 예제 인증
            response.put("success", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
