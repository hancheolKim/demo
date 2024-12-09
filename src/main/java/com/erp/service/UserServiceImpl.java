package com.erp.service;

import com.erp.dao.UserDAO;
import com.erp.dao.UserDAO;
import com.erp.service.UserService;
import com.erp.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public int addUser(UserVO user) {
        return userDAO.insertUser(user);
    }

    @Override
    public UserVO getUserById(String id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<UserVO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public int deleteUser(String id) {
        return userDAO.deleteUser(id);
    }
}
