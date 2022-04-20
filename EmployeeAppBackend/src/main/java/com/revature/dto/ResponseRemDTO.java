package com.revature.dto;

import java.util.Objects;

public class ResponseRemDTO {
    private  int id;
    private int reimb_amount;
    private String reimb_desc;
    private String reimb_date;
     private String reimb_type;
    private String reimb_status;

  /*----------user-------------------*/
    private String firstname;
    private String lastname;
    private  String email;
    private  String userrole;




    public ResponseRemDTO() {
    }

    public ResponseRemDTO(int id, int reimb_amount, String reimb_date, String reimb_desc, String reimb_type, String reimb_status, String firstName, String lastName, String email, String userRole) {
    this.id =id;
    this.reimb_amount=reimb_amount;
    this.reimb_date =reimb_date;

    this.reimb_desc =reimb_desc;
    this.reimb_type =reimb_type;
    this.reimb_status =reimb_status;
    this.firstname=firstName;
    this.lastname =lastName;
    this.email=email;
    this.userrole=userRole;
    }



    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getReimb_amount() {
        return reimb_amount;
    }

    public void setReimb_amount(int reimb_amount) {
        this.reimb_amount = reimb_amount;
    }

    public String getReimb_date() {
        return reimb_date;
    }

    public void setReimb_date(String reimb_date) {
        this.reimb_date = reimb_date;
    }

    public String getReimb_desc() {
        return reimb_desc;
    }

    public void setReimb_desc(String reimb_desc) {
        this.reimb_desc = reimb_desc;
    }

    public String getReimb_type() {
        return reimb_type;
    }

    public void setReimb_type(String reimb_type) {
        this.reimb_type = reimb_type;
    }

    public String getReimb_status() {
        return reimb_status;
    }

    public void setReimb_status(String reimb_status) {
        this.reimb_status = reimb_status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseRemDTO)) return false;
        ResponseRemDTO that = (ResponseRemDTO) o;
        return getId() == that.getId() && getReimb_amount() == that.getReimb_amount() && Objects.equals(getReimb_desc(), that.getReimb_desc()) && Objects.equals(getReimb_date(), that.getReimb_date()) && Objects.equals(getReimb_type(), that.getReimb_type()) && Objects.equals(getReimb_status(), that.getReimb_status()) && Objects.equals(getFirstname(), that.getFirstname()) && Objects.equals(getLastname(), that.getLastname()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getUserrole(), that.getUserrole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReimb_amount(), getReimb_desc(), getReimb_date(),  getReimb_type(), getReimb_status(), getFirstname(), getLastname(), getEmail(), getUserrole());
    }

    @Override
    public String toString() {
        return "ResponseRemDTO{" +
                "id=" + id +
                ", reimb_amount=" + reimb_amount +
                ", reimb_desc='" + reimb_desc + '\'' +
                ", reimb_date='" + reimb_date + '\'' +

                ", reimb_type='" + reimb_type + '\'' +
                ", reimb_status='" + reimb_status + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", userrole='" + userrole + '\'' +
                '}';
    }
}
