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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    @ResponseBody // 이 어노테이션을 추가하여 반환값을 JSON으로 반환
    public UserVO home(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute("id") != null) {
            // ID가 1인 사용자 정보를 조회
            UserVO user = userService.getUser(1); // 예시: 사용자 조회
            return user; // 사용자 정보를 반환
        }

        // 로그인하지 않았다면 빈 사용자 객체 반환
        return new UserVO(); // 빈 사용자 객체를 반환할 수도 있음
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
