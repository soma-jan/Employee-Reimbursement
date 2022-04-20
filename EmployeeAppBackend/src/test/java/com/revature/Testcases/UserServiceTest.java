package com.revature.Testcases;


import com.revature.dao.UserDao;
import com.revature.exception.FailedLoginException;
import com.revature.model.User;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

/*
    public User login(String userName, String password) throws SQLException, FailedLoginException {
        User user =  this.userDao.getUserByUserNameAndPassword(userName,password);
}*/



@Test
public void LoginTest() throws SQLException, FailedLoginException {
        UserDao mockDao = mock(UserDao.class);
        UserService usserservice = new UserService(mockDao);
        User fakeuser =new User(1,"soma","soma123","soma","jan","soma.j18@gmail.com","employee");
        when(mockDao.getUserByUserNameAndPassword("soma","soma123")).thenReturn(fakeuser);
        User actual=usserservice.login("soma","soma123");
        User expected= fakeuser;
        Assertions.assertEquals(expected,actual);

}
@Test
        public  void InvalidLoginTest(){  UserDao mockDao = mock(UserDao.class);
        UserService usserservice = new UserService(mockDao);
        Assertions.assertThrows(FailedLoginException.class, () -> {
                usserservice.login("sss","kkkk");
        });


}

        }