package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.ReimburseDTO;
import com.revature.dto.ResponseRemDTO;
import com.revature.dto.ResponsegetRemdto;
import com.revature.exception.ImageNotFoundException;
import com.revature.exception.InvalidImageException;
import com.revature.exception.UnauthorizedResponse;
import com.revature.exception.UserNotFoundException;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import org.apache.tika.Tika;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {
    private ReimbursementDao reimbursementDao;
    public ReimbursementService() {
        this.reimbursementDao = new ReimbursementDao();
    }
    public ReimbursementService(ReimbursementDao mockDao){
        this.reimbursementDao =mockDao;
    }


    /*----------------Add Reimburement------------*/
    public ResponsegetRemdto addReimbursement(String userId, ReimburseDTO rdto) throws SQLException, UserNotFoundException, UnauthorizedResponse, InvalidImageException, IOException {

        /*------------validation---------------*/
       // User user = reimbursementDao.getUser(userId);

        try {
            int user_id = Integer.parseInt(userId);
            User user = reimbursementDao.getUser(user_id);
            if (user == null) {
                throw new UserNotFoundException("User with id :" + user_id  + "was not found");
            }
            if ((user.getUserRole()).equals("MANAGER")) {
                throw new UnauthorizedResponse("Manager does not have acess to this endpoint");
            }
            Tika tika = new Tika();
            String mimeType = tika.detect(rdto.getImage());

            if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png") && !mimeType.equals("image/gif")) {
                throw new InvalidImageException("Image must be a JPEG, PNG, or GIF");
            }

            Reimbursement reimbursementAdded = this.reimbursementDao.addReimbursement(user_id, rdto);
           /* return new ResponseRemDTO(reimbursementAdded.getId(), reimbursementAdded.getReimb_amount(),
                                reimbursementAdded.getReimb_date(),reimbursementAdded.getReimb_desc(),
                                reimbursementAdded.getReimb_type(), reimbursementAdded.getReimb_status(),
                                reimbursementAdded.getReimb_author().getFirstName(),reimbursementAdded.getReimb_author().getLastName(),
                              reimbursementAdded.getReimb_author().getEmail(),reimbursementAdded.getReimb_author().getUserRole());*/
            return new ResponsegetRemdto(reimbursementAdded.getId(),reimbursementAdded.getReimb_amount(),reimbursementAdded.getReimb_desc(),reimbursementAdded.getReimb_date(),reimbursementAdded.getResolve_date(),
                    reimbursementAdded.getReimb_author().getFirstName(),reimbursementAdded.getReimb_author().getLastName(),reimbursementAdded.getReimb_author().getEmail(),
                    reimbursementAdded.getReimb_author().getUserRole(),reimbursementAdded.getReimb_resolver().getFirstName(),reimbursementAdded.getReimb_resolver().getLastName(),
                    reimbursementAdded.getReimb_resolver().getEmail(),reimbursementAdded.getReimb_resolver().getUserRole(),reimbursementAdded.getReimb_type(),reimbursementAdded.getReimb_status());



    }catch(NumberFormatException e){
            throw  new  IllegalArgumentException("ID provided for user must be a valid int");
        }
    }
    public List<ResponsegetRemdto> getAllReimbursements() throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllReimbursement();
        if(reimbursements.isEmpty()){
            throw  new  IllegalArgumentException("No Reimbursement available");
        }
        List<ResponsegetRemdto> responsegetRemdto = new ArrayList<>();

        for (Reimbursement reimb : reimbursements) {
            responsegetRemdto.add(new ResponsegetRemdto(reimb.getId(),reimb.getReimb_amount(),reimb.getReimb_desc(),reimb.getReimb_date(),reimb.getResolve_date(),
                    reimb.getReimb_author().getFirstName(),reimb.getReimb_author().getLastName(),reimb.getReimb_author().getEmail(),
                    reimb.getReimb_author().getUserRole(),reimb.getReimb_resolver().getFirstName(),reimb.getReimb_resolver().getLastName(),
                    reimb.getReimb_resolver().getEmail(),reimb.getReimb_resolver().getUserRole(),reimb.getReimb_type(),reimb.getReimb_status()));
        }

        return responsegetRemdto;
    }
    public List<ResponsegetRemdto> getAllReimbursements(int userId) throws SQLException {
        List<Reimbursement> reimbursements = this.reimbursementDao.getAllReimbursementByUserId(userId);
        if(reimbursements.isEmpty()){
            throw  new  IllegalArgumentException("No Reimbursement available");
        }
        List<ResponsegetRemdto> responsegetRemdto = new ArrayList<>();

        for (Reimbursement reimb : reimbursements) {
            responsegetRemdto.add(new ResponsegetRemdto(reimb.getId(),reimb.getReimb_amount(),reimb.getReimb_desc(),reimb.getReimb_date(),reimb.getResolve_date(),
                    reimb.getReimb_author().getFirstName(),reimb.getReimb_author().getLastName(),reimb.getReimb_author().getEmail(),
                    reimb.getReimb_author().getUserRole(),reimb.getReimb_resolver().getFirstName(),reimb.getReimb_resolver().getLastName(),
                    reimb.getReimb_resolver().getEmail(),reimb.getReimb_resolver().getUserRole(),reimb.getReimb_type(),reimb.getReimb_status()));
        }

        return responsegetRemdto;
    }
    public ResponsegetRemdto updateReimbursement(String remId, String status, int user_id) throws SQLException {
        try {
            int reimb_id = Integer.parseInt(remId);
            //int user_id = Integer.parseInt(userId);

            Reimbursement reimbursementUpdated = this.reimbursementDao.Updatereimbursement(reimb_id, status, user_id);

            return new ResponsegetRemdto(reimbursementUpdated.getId(),reimbursementUpdated.getReimb_amount(),reimbursementUpdated.getReimb_desc(),reimbursementUpdated.getReimb_date(),reimbursementUpdated.getResolve_date(),
                    reimbursementUpdated.getReimb_author().getFirstName(),reimbursementUpdated.getReimb_author().getLastName(),reimbursementUpdated.getReimb_author().getEmail(),
                    reimbursementUpdated.getReimb_author().getUserRole(),reimbursementUpdated.getReimb_resolver().getFirstName(),reimbursementUpdated.getReimb_resolver().getLastName(),
                    reimbursementUpdated.getReimb_resolver().getEmail(),reimbursementUpdated.getReimb_resolver().getUserRole(),reimbursementUpdated.getReimb_type(),reimbursementUpdated.getReimb_status());

        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Reimbursement ID and user ID provided must be int values");
        }
    }
    public InputStream getReimburseImage(String remId) throws SQLException, ImageNotFoundException {
        try {
            int rId = Integer.parseInt(remId);
            //int uId = Integer.parseInt(userId);

            InputStream is = this.reimbursementDao.getReimbursementImage(rId);

            if (is == null) {
                throw new ImageNotFoundException("Rembursement with id " + remId + " does not have an image");
            }

            return is;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Rembursement and/or user id must be an int value");
        }
    }

    }

