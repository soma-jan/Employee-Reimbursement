package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.exception.FailedLoginException;
import com.revature.model.User;


import java.sql.SQLException;

public class UserService {

    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public UserService(UserDao mockDao) {
        this.userDao = mockDao;
    }


    public User login(String userName, String password) throws SQLException, FailedLoginException {
        User user =  this.userDao.getUserByUserNameAndPassword(userName,password);
        if(user == null){
            throw  new FailedLoginException("Invalid username or password");
        }

        return user;
    }
}
