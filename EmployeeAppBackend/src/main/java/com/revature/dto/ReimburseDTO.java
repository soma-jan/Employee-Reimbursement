package com.revature.dto;

import com.revature.model.Reimbursement;
import com.revature.model.User;

import java.io.InputStream;
import java.util.Objects;

public class ReimburseDTO {

    private int reimb_amount;
    private String reimb_date;
    private String reimb_desc;
    private String reimb_type;
    private String reimb_status;
    private InputStream image;
    private User reimb_author;



    public ReimburseDTO() {
    }
    public ReimburseDTO(int reimb_amount, String reimb_desc, String reimb_type) {

        this.reimb_amount = reimb_amount;
        this.reimb_desc = reimb_desc;
        this.reimb_type = reimb_type;


    }


    public ReimburseDTO(int reimb_amount, String reimb_date, String reimb_desc, String reimb_type, String reimb_status, User reimb_author) {

        this.reimb_amount = reimb_amount;
        this.reimb_date = reimb_date;
        this.reimb_desc = reimb_desc;
        this.reimb_type = reimb_type;
        this.reimb_status = reimb_status;
        this.reimb_author = reimb_author;
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

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public User getReimb_author() {
        return reimb_author;
    }

    public void setReimb_author(User reimb_author) {
        this.reimb_author = reimb_author;
    }

    @Override
    public String toString() {
        return "ReimburseDTO{" +
                "reimb_amount=" + reimb_amount +
                ", reimb_date='" + reimb_date + '\'' +
                ", reimb_desc='" + reimb_desc + '\'' +
                ", reimb_type='" + reimb_type + '\'' +
                ", reimb_status='" + reimb_status + '\'' +
                ", image=" + image +
                ", reimb_author=" + reimb_author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReimburseDTO)) return false;
        ReimburseDTO that = (ReimburseDTO) o;
        return getReimb_amount() == that.getReimb_amount() && Objects.equals(getReimb_date(), that.getReimb_date()) && Objects.equals(getReimb_desc(), that.getReimb_desc()) && Objects.equals(getReimb_type(), that.getReimb_type()) && Objects.equals(getReimb_status(), that.getReimb_status()) && Objects.equals(getImage(), that.getImage()) && Objects.equals(getReimb_author(), that.getReimb_author());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReimb_amount(), getReimb_date(), getReimb_desc(), getReimb_type(), getReimb_status(), getImage(), getReimb_author());
    }
}
