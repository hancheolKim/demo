package com.erp.service;

import com.erp.vo.UserVO;

import java.util.List;

public interface UserService {
    // 사용자 추가
    int addUser(UserVO user);

    // 사용자 조회 (ID로 조회)
    UserVO getUserById(String id);

    // 사용자 목록 조회
    List<UserVO> getAllUsers();

    // 사용자 삭제
    int deleteUser(String id);
}
