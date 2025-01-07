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

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*********************
     * 로그인
     *********************/
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String id = credentials.get("id");
        String password = credentials.get("password");
        UserVO user = userService.getUserById(id);

        Map<String, Object> response = new HashMap<>();

        if (user == null) {
            // 존재하지 않는 ID의 경우
            response.put("success", false);
            response.put("error", "INVALID_ID");
            response.put("message", "존재하지 않는 아이디입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else if (!user.getPassword().equals(password)) {
            // ID는 있지만 비밀번호가 일치하지 않는 경우
            response.put("success", false);
            response.put("error", "INVALID_PASSWORD");
            response.put("message", "비밀번호가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // 로그인 성공 시
        response.put("success", true);
        response.put("user_num", user.getUserNum());        // 유저의 num
        response.put("user_name",user.getUserName());
        response.put("user_id", user.getId());              // 유저의 id
        response.put("user_status", user.getStatus());      // 유저의 status
        return ResponseEntity.ok(response);
    }


}
