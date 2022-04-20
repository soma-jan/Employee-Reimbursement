package com.revature.dao;

import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public UserDao() {
    }

    public UserDao(int userid, String username, String pass, String userrole) {
    }

    public User getUserByUserNameAndPassword(String userName, String password) throws SQLException {

        try (Connection con = ConnectionUtility.getConnection()) {

            String sql1 = "select ERS_USERS.id, ERS_USERS.ERS_USERNAME,ERS_USERS.ERS_PASSWORD,ERS_USERS.user_firstname ,ERS_USERS.user_lastname ,ERS_USERS.user_email ,ERS_USER_ROLES.user_role from ERS_USERS inner join ERS_USER_ROLES on ERS_USERS.user_role_id = ERS_USER_ROLES.id where \n" +
                    "ERS_USERS.ers_username =? and ERS_USERS.ers_password =? ";
            //String sql1 ="select * from ERS_USERS";
            PreparedStatement psmt = con.prepareStatement(sql1);
            psmt.setString(1, userName);
            psmt.setString(2, password);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                int userid = rs.getInt("id");
                String username = rs.getString("ers_username");
                String pass = rs.getString("ers_password");
                String firstname = rs.getString("user_firstname");
                String lastname = rs.getString("user_lastname");
                String email = rs.getString("user_email");
                String userrole = rs.getString("user_role");
                //return new User(userid,username,pass,userrole);
                return new User(userid, username, pass, firstname, lastname, email, userrole);
            }

        }
        //return new User(userid,username,pass, firstname, lastname, email, userrole);
        return null;
        //return null;
     }
}
