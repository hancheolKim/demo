package com.erp.controller;

import com.erp.service.UserService;
import com.erp.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class ERPController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String id = credentials.get("id");
        String password = credentials.get("password");
        UserVO user = userService.getUserById(id);

        Map<String, Object> response = new HashMap<>();
        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공 시 User 정보와 함께 반환
            response.put("success", true);
            response.put("user_num", user.getUserNum());        // 유저의 num
            response.put("user_id", user.getId());          // 유저의 id
            response.put("user_status", user.getStatus());  // 유저의 status
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
