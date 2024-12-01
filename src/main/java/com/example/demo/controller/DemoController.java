package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if(session.getAttribute("id")!= null) {
            // ID가 1인 사용자 정보를 조회
            UserVO user = userService.getUser(1);
            System.out.println(user);
            // Model에 사용자 정보 추가
            model.addAttribute("user", user);
        }

        // index.html을 반환
        return "index";
    }

    @GetMapping("/signup")
    public String insertMember(){
        return "insertMember";
    }

    @PostMapping("/signup")
    public String insertMem(HttpServletRequest request, Model model){
        UserVO user = new UserVO();
        user.setName((String)request.getParameter("name"));
        user.setEmail((String)request.getParameter("email"));
        try {
            userService.addUser(user);
            System.out.println("성공했어~");
            model.addAttribute("result","success");
        }catch(Exception e){
            model.addAttribute("result","error");
        }
        return "index";
    }
}
