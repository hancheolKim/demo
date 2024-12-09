package com.erp.dao;

import com.erp.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDAO {
    // 사용자 추가
    int insertUser(UserVO user);

    // 사용자 조회 (ID로 조회)
    UserVO getUserById(String id);

    // 사용자 목록 조회
    List<UserVO> getAllUsers();

    // 사용자 삭제
    int deleteUser(String id);
}
