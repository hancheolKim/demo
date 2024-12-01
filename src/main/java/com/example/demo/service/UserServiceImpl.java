package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserVO getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    public List<UserVO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public int addUser(UserVO userVO) {
        return userDAO.addUser(userVO);
    }

    @Override
    public int updateUser(UserVO userVO) {
        return userDAO.updateUser(userVO);
    }

    @Override
    public int deleteUser(int id) {
        return userDAO.deleteUser(id);
    }
}
