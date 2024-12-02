package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    @ResponseBody
    public UserVO home(HttpServletRequest request, @RequestParam int id) {
        try {
            // id가 한 자리 숫자인지 체크
            if (id < 0 || id > 9) {
                throw new IllegalArgumentException("id는 한자리 숫자여야 합니다.");
            }

            // ID가 1인 사용자 정보를 조회
            UserVO user = userService.getUser(id); // 예시: 사용자 조회
            return user; // 사용자 정보를 반환
        } catch (Exception e) {
            // 예외 처리 및 빈 사용자 객체 반환
            return new UserVO(); // 빈 사용자 객체를 반환
        }
    }

}
