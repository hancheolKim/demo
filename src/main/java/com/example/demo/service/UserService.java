package com.example.demo.service;

import com.example.demo.vo.UserVO;

import java.util.List;

public interface UserService {
    UserVO getUser(int id);  // 사용자 조회

    List<UserVO> getAllUsers();  // 모든 사용자 조회

    int addUser(UserVO userVO);  // 사용자 추가

    int updateUser(UserVO userVO);  // 사용자 수정

    int deleteUser(int id);  // 사용자 삭제
}
