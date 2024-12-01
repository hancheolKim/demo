package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model) {
        // ID가 1인 사용자 정보를 조회
        UserVO user = userService.getUser(1);
        System.out.println(user);

        // Model에 사용자 정보 추가
        model.addAttribute("user", user);

        // index.html을 반환
        return "index";
    }

    @GetMapping("/signup")
    public String insertMember(){
        return "insertMember";
    }
}
