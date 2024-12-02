package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    // 사용자 리스트 조회 (GET 요청)
    @GetMapping("/home")
    @ResponseBody
    public List<UserVO> home() {
        List<UserVO> users = userService.getAllUsers(); // 사용자 조회
        return users; // 사용자 정보 반환
    }

    // 방명록에 사용자 추가 (POST 요청)
    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestParam("name") String name, @RequestParam("email") String email) {
        UserVO userVO = new UserVO();
        userVO.setName(name);
        userVO.setEmail(email);

        int result = userService.addUser(userVO); // 사용자 추가

        if (result > 0) {
            return "사용자 추가 성공!";
        } else {
            return "사용자 추가 실패!";
        }
    }
}

