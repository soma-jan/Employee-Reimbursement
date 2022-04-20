package com.revature.Testcases;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.dto.ReimburseDTO;

import com.revature.exception.UnauthorizedResponse;
import com.revature.exception.UserNotFoundException;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import org.apache.tika.Tika;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReimbursementServiceTest {
    @Test
    public  void addReimbursementInvalidUserId() {
        ReimbursementDao reimbursementDao = mock(ReimbursementDao.class);
        ReimburseDTO fakeRemdto = new ReimburseDTO(100, "2022-10-11", "kkk", "travel", "pending",
                new User());
        ReimbursementService reimbursementservice = new ReimbursementService(reimbursementDao);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            reimbursementservice.addReimbursement("100", fakeRemdto);
        });

    }

    @Test
    public  void addReimbursementInvalid() {
        ReimbursementDao reimbursementDao = mock(ReimbursementDao.class);
        ReimburseDTO fakeRemdto = new ReimburseDTO(100, "2022-10-11", "kkk", "travel", "pending",
                new User());

        ReimbursementService reimbursementservice = new ReimbursementService(reimbursementDao);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            reimbursementservice.addReimbursement("ABC", fakeRemdto);
        });

    }

    @Test
    public  void addReimbursementManager() throws SQLException {
        ReimbursementDao mockreimbursementDao = mock(ReimbursementDao.class);
        UserDao mockUserDao = mock(UserDao.class);


        User fakeuser = new User(1, "soma", "soma123", "soma", "jan", "soma.j18@gmail.com", "MANAGER");
        ReimburseDTO fakeRemdto = new ReimburseDTO(100, "2022-10-11", "kkk", "travel", "pending",
                fakeuser);
        when(mockreimbursementDao.getUser(1)).thenReturn(fakeuser);
        ReimbursementService reimbursementservice = new ReimbursementService(mockreimbursementDao);
        Assertions.assertThrows(UnauthorizedResponse.class, () -> {
            reimbursementservice.addReimbursement("1", fakeRemdto);
        });
    }

    @Test
    public  void getAllReimbursementInvalidUserId() {
        ReimbursementDao reimbursementDao = mock(ReimbursementDao.class);
        ReimbursementService reimbursementservice = new ReimbursementService(reimbursementDao);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            reimbursementservice.getAllReimbursements(0);
        });

    }
}
