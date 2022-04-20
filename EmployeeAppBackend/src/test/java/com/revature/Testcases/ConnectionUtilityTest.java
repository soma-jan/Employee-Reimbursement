package com.revature.Testcases;

import com.revature.utility.ConnectionUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class ConnectionUtilityTest {
    public static Logger logger = LoggerFactory.getLogger(ConnectionUtilityTest.class);
    @BeforeEach
    public void setUp() {

    }
    @ Test
    public void test_getConnection() throws SQLException, SQLException {
        ConnectionUtility.getConnection();


    }
}
