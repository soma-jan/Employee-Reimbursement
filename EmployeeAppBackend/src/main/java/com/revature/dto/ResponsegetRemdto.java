package com.revature.dto;

import com.revature.model.User;

import java.io.InputStream;
import java.util.Objects;


public class ResponsegetRemdto {
    private int id;
    private int amount;
    private String description;

    private String submitteddate;
    private String resolveddate;
    private String status;
    private String type;
    /*----------user-------------------*/
    private String firstname;
    private String lastname;
    private  String email;
    private  String userrole;
    /*----------manager-------------------*/
    private String managerFirstname;
    private String managerLastname;
    private  String managerEmail;
    private  String managerUserrole;

    public ResponsegetRemdto(int id, int reimb_amount, String reimb_desc, String reimb_date, String resolve_date,
                             String firstName, String lastName, String email, String userRole,String firstName1,String lasttName1,String email1, String userrole1, String reimb_type, String reimb_status) {
    this.id=id;

        this.amount=reimb_amount;
        this.description=reimb_desc;

        this.submitteddate=reimb_date;
        this.resolveddate=resolve_date;
        //this.status=reimb_status;
        this.status=reimb_status;
        this.firstname=firstName;
        this.lastname=lastName;
        this.email=email;
        this.userrole=userRole;
       this.managerFirstname=firstName1;
        this.managerLastname=lasttName1;
        this.managerEmail=email1;
        this.managerUserrole=userrole1;
       this.type=reimb_type;



    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmitteddate() {
        return submitteddate;
    }

    public void setSubmitteddate(String submitteddate) {
        this.submitteddate = submitteddate;
    }

    public String getResolveddate() {
        return resolveddate;
    }

    public void setResolveddate(String resolveddate) {
        this.resolveddate = resolveddate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }





    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public String getManagerFirstname() {
        return managerFirstname;
    }

    public void setManagerFirstname(String managerFirstname) {
        this.managerFirstname = managerFirstname;
    }

    public String getManagerLastname() {
        return managerLastname;
    }

    public void setManagerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerUserrole() {
        return managerUserrole;
    }

    public void setManagerUserrole(String managerUserrole) {
        this.managerUserrole = managerUserrole;
    }
}
