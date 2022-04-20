package com.revature.dao;

import com.revature.dto.ReimburseDTO;
import com.revature.dto.ResponsegetRemdto;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {
    Long datetime = System.currentTimeMillis();
    public Reimbursement addReimbursement(int userId, ReimburseDTO rdto) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()) {
            con.setAutoCommit(false);

          int typeId =getType(rdto.getReimb_type());

          Timestamp curdate = new Timestamp(datetime);
          String sql1="insert into  ERS_REIMB (REIMB_AMOUNT,REIMB_SUBMITTED,REIMB_DESC,REIMB_RECEIPT,REIMB_AUTHOR,REIMB_TYPE_ID)\n" +
                  "VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,rdto.getReimb_amount());
            pstmt.setTimestamp(2,curdate);
            pstmt.setString(3,rdto.getReimb_desc());
            pstmt.setBinaryStream(4,rdto.getImage());
            pstmt.setInt(5,userId);
           // pstmt.setInt(5,sId);
            pstmt.setInt(6,typeId);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int remb_id = rs.getInt(1);
            User user =getUser(userId);
            String status="PENDING";
            String rresolvedate = "N/A";
            User manager = new User("N/A", "N/A", "N/A", "N/A");
            //Reimbursement reimbursement =new Reimbursement(remb_id,rdto.getReimb_amount(),curdate.toString(),rdto.getReimb_desc(),rdto.getReimb_type(),status,user);
            Reimbursement reimbursement = new Reimbursement(remb_id, rdto.getReimb_amount(),curdate.toString(),rdto.getReimb_desc(),rresolvedate, user, manager, status, rdto.getReimb_type());
            con.setAutoCommit(true);
            return  reimbursement;
        }
    }
    /*-----------get user-------------------------*/
    public User getUser (int userId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql1 = "select ers_users.id ,ers_users.user_firstname ,ers_users.user_lastname ,ers_users.user_email ,ers_user_roles.user_role \n" +
                    "from  ers_users  inner join  ers_user_roles  on ers_users.user_role_id =ers_user_roles.id where ers_users.id =?";
            PreparedStatement pstmt1 = con.prepareStatement(sql1);
            pstmt1.setInt(1, userId);
            ResultSet rs1 = pstmt1.executeQuery();
            if (rs1.next()) {
                //int usrid = rs1.getInt("id");
                String firstname = rs1.getString("user_firstname");
                String lastname = rs1.getString("user_lastname");
                String email = rs1.getString("user_email");
                String userrole = rs1.getString("user_role");
                User user = new User( firstname, lastname, email, userrole);
                return user;

            }

            return null;
        }
    }


    /*-----------get status-------------------------*/
    public String getStatus(int reimb_status_id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql1 = "select REIMB_STATUS from ers_reimb_status ers where id=?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setInt(1, reimb_status_id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String sId = rs.getString("reimb_status");
            return sId;

        }
    }
        /*-----------get statusid-------------------------*/
        public int getStatusid(String reimb_status)  throws SQLException {
            try (Connection con = ConnectionUtility.getConnection()) {
                String sql1 = "select id  from ers_reimb_status ers where reimb_status=?";
                PreparedStatement pstmt = con.prepareStatement(sql1);
                pstmt.setString(1, reimb_status);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                int sId = rs.getInt("id");
                return sId;

            }
    }
    /*-----------get Typeid-------------------------*/
    public int getType (String reimb_Type) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {

            String sql1 = "select * from ers_reimb_type  where REIMB_TYPE=?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, reimb_Type);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int typeId = rs.getInt("id");
            return typeId;

        }
    }
        /*-----------get Username-------------------------*/
        public String userCheck(int sId) throws SQLException {
            try (Connection con = ConnectionUtility.getConnection()) {

                String sql1 = "select * from ers_users  where id=?";
                PreparedStatement pstmt = con.prepareStatement(sql1);
                pstmt.setInt(1, sId);

                ResultSet rs = pstmt.executeQuery();
                rs.next();
                String username = rs.getString("user_firstname");
                return username;

            }
    }

    public List<Reimbursement> getAllReimbursement() throws SQLException {

        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "select r.id ,r.reimb_amount ,r.reimb_submitted,r.reimb_resolved ,r.reimb_desc,r.reimb_receipt,r.reimb_author,r.reimb_resolver," +
                    "r.reimb_status_id,reimb_type_id from ers_reimb as r order by id";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            String rresolvedate = "N/A";
            User manager = new User("N/A", "N/A", "N/A", "N/A");
            int rresolve;
            int rstatus_id = 0;
            String rstatus;


            while (rs.next()) {

                int rid = rs.getInt("id");
                int ramt = rs.getInt("reimb_amount");

                String rsubdate = rs.getTimestamp("reimb_submitted").toString();
                if (!(rs.getTimestamp("reimb_resolved") == null)) {
                    rresolvedate = rs.getTimestamp("reimb_resolved").toString();
                }else{
                    rresolvedate = "N/A";
                }
                String rdesc = rs.getString("reimb_desc");
                int rauthor = rs.getInt("reimb_author");
                User user = getUser(rauthor);
                if (rs.getInt("reimb_resolver") != 0) {
                    rresolve = rs.getInt("reimb_resolver");
                    manager = getUser(rresolve);

                }else{
                     manager = new User("N/A", "N/A", "N/A", "N/A");
                }

                if (rs.getInt("reimb_status_id") != 0) {
                    rstatus_id =rs.getInt("reimb_status_id");
                    rstatus = getStatus(rstatus_id);
                } else {
                    rstatus = "PENDING";
                }


                int rtypeid = rs.getInt("reimb_type_id");
                String sql4 = "select REIMB_TYPE from ers_reimb_type  where id=?";
                PreparedStatement pstmt4 = con.prepareStatement(sql4);
                pstmt4.setInt(1, rtypeid);
                ResultSet rs4 = pstmt4.executeQuery();
                rs4.next();
                String rtype = rs4.getString("reimb_type");

                Reimbursement row = new Reimbursement(rid, ramt, rdesc, rsubdate, rresolvedate, user, manager, rstatus, rtype);
                reimbursements.add(row);
            }

            return reimbursements;
        }
    }

    public InputStream getReimbursementImage(int rId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "select  REIMB_RECEIPT from ers_reimb where id=?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rId);


            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                InputStream is = rs.getBinaryStream("reimb_receipt");

                return is;
            } else {
                return null;
            }
        }
    }
    /*-----------update-------------------------*/
public Reimbursement Updatereimbursement(int remId, String status, int userId) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            con.setAutoCommit(false);
            int sId= getStatusid(status);
            String sql = "update ers_reimb set reimb_resolved=?,reimb_resolver=?,reimb_status_id=? where id=?";
            Timestamp curdate1 = new Timestamp(datetime);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setTimestamp(1, curdate1);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, sId);
            pstmt.setInt(4, remId);
            pstmt.executeUpdate();
            con.setAutoCommit(true);
            Reimbursement reimbursement = getAllReimbursementByRemId ( remId);

            return  reimbursement;

        }}
    public List<Reimbursement> getAllReimbursementByUserId (int userId) throws SQLException {

        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "select r.id ,r.reimb_amount ,r.reimb_submitted,r.reimb_resolved ,r.reimb_desc,r.reimb_receipt,r.reimb_author,r.reimb_resolver,\n" +
                    "r.reimb_status_id,reimb_type_id from ers_reimb as r where r.reimb_author=? order by id";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            String rresolvedate = "N/A";

            int rresolve;
            int rstatus_id = 0;
            String rstatus;
            User manager = null;

            while (rs.next()) {

                int rid = rs.getInt("id");
                int ramt = rs.getInt("reimb_amount");

                String rsubdate = rs.getTimestamp("reimb_submitted").toString();
                if (!(rs.getTimestamp("reimb_resolved") == null)) {
                    rresolvedate = rs.getTimestamp("reimb_resolved").toString();
                }else{rresolvedate = "N/A";}
                String rdesc = rs.getString("reimb_desc");
                int rauthor = rs.getInt("reimb_author");
                User user = getUser(rauthor);
                if (rs.getInt("reimb_resolver") != 0) {
                    rresolve = rs.getInt("reimb_resolver");
                     manager = getUser(rresolve);

                }else{
                     manager = new User("N/A", "N/A", "N/A", "N/A");
                }

                if (rs.getInt("reimb_status_id") != 0) {
                    rstatus_id=rs.getInt("reimb_status_id");
                    rstatus = getStatus(rstatus_id);
                } else {
                    rstatus = "PENDING";
                }


                int rtypeid = rs.getInt("reimb_type_id");
                String sql4 = "select REIMB_TYPE from ers_reimb_type  where id=?";
                PreparedStatement pstmt4 = con.prepareStatement(sql4);
                pstmt4.setInt(1, rtypeid);
                ResultSet rs4 = pstmt4.executeQuery();
                rs4.next();
                String rtype = rs4.getString("reimb_type");

                Reimbursement row = new Reimbursement(rid, ramt, rdesc, rsubdate, rresolvedate, user, manager, rstatus, rtype);
                reimbursements.add(row);
            }

            return reimbursements;
        }

    }
    public Reimbursement getAllReimbursementByRemId (int rId) throws SQLException {
        Reimbursement reimbursement=new Reimbursement();
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
            String sql = "select r.id ,r.reimb_amount ,r.reimb_submitted,r.reimb_resolved ,r.reimb_desc,r.reimb_receipt,r.reimb_author,r.reimb_resolver,\n" +
                    "r.reimb_status_id,reimb_type_id from ers_reimb as r where r.id=? order by id";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rId);
            ResultSet rs = pstmt.executeQuery();
            String rresolvedate = "N/A";
            User manager = new User("N/A", "N/A", "N/A", "N/A");
            int rresolve;
            int rstatus_id = 0;
            String rstatus;


            while (rs.next()) {
                int rid = rs.getInt("id");
                int ramt = rs.getInt("reimb_amount");

                String rsubdate = rs.getTimestamp("reimb_submitted").toString();
                if (!(rs.getTimestamp("reimb_resolved") == null)) {
                    rresolvedate = rs.getTimestamp("reimb_resolved").toString();
                }else{
                    rresolvedate = "N/A";
                }
                String rdesc = rs.getString("reimb_desc");
                int rauthor = rs.getInt("reimb_author");
                User user = getUser(rauthor);
                if (rs.getInt("reimb_resolver") != 0) {
                    rresolve = rs.getInt("reimb_resolver");
                    manager = getUser(rresolve);

                }else
                {
                     manager = new User("N/A", "N/A", "N/A", "N/A");
                }

                if (rs.getInt("reimb_status_id") != 0) {

                    rstatus_id=rs.getInt("reimb_status_id");
                    rstatus = getStatus(rstatus_id);
                } else {
                    rstatus = "PENDING";
                }


                int rtypeid = rs.getInt("reimb_type_id");
                String sql4 = "select REIMB_TYPE from ers_reimb_type  where id=?";
                PreparedStatement pstmt4 = con.prepareStatement(sql4);
                pstmt4.setInt(1, rtypeid);
                ResultSet rs4 = pstmt4.executeQuery();
                rs4.next();
                String rtype = rs4.getString("reimb_type");

                 reimbursement = new Reimbursement(rid, ramt, rdesc, rsubdate, rresolvedate, user, manager, rstatus, rtype);

            }

            return reimbursement;
        }

    } }