package com.revature.model;

import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

public class Reimbursement {
    private int id;
    private int reimb_amount;
    private String reimb_desc;
    private String reimb_date;
    private String resolve_date;
    private User reimb_author;
    private User reimb_resolver;
    private String reimb_type;
    private String reimb_status;



    public Reimbursement() {
    }

    public Reimbursement(int id, int reimb_amount,String reimb_date, String reimb_desc, String reimb_type, String reimb_status, User reimb_author) {
        this.id = id;
        this.reimb_amount = reimb_amount;
        this.reimb_date =reimb_date;
        this.reimb_desc = reimb_desc;
        this.reimb_type = reimb_type;
        this.reimb_status = reimb_status;
        this.reimb_author = reimb_author;

    }

    public Reimbursement(int rid, int ramt, String rdesc, String rsubdate, String rresolvedate, User user,User manager, String rstatus, String rtype) {
        this.id = rid;
        this.reimb_amount = ramt;
        this.reimb_desc = rdesc;

        this.reimb_date =rsubdate;
        this.resolve_date = rresolvedate;
        this.reimb_author=user;
       this.reimb_resolver=manager;
        this.reimb_type=rtype;
        this.reimb_status=rstatus;
    }

    public String getResolve_date() {
        return resolve_date;
    }



    public void setResolve_date(String resolve_date) {
        this.resolve_date = resolve_date;
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

    public User getReimb_author() {
        return reimb_author;
    }

    public void setReimb_author(User reimb_author) {
        this.reimb_author = reimb_author;
    }

    public User getReimb_resolver() {
        return reimb_resolver;
    }

    public void setReimb_resolver(User reimb_resolver) {
        this.reimb_resolver = reimb_resolver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement)) return false;
        Reimbursement that = (Reimbursement) o;
        return getId() == that.getId() && getReimb_amount() == that.getReimb_amount() && Objects.equals(getReimb_desc(), that.getReimb_desc()) && Objects.equals(getReimb_date(), that.getReimb_date()) && Objects.equals(getResolve_date(), that.getResolve_date()) && Objects.equals(getReimb_author(), that.getReimb_author()) && Objects.equals(getReimb_resolver(), that.getReimb_resolver()) && Objects.equals(getReimb_type(), that.getReimb_type()) && Objects.equals(getReimb_status(), that.getReimb_status());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReimb_amount(), getReimb_desc(), getReimb_date(), getResolve_date(), getReimb_author(), getReimb_resolver(), getReimb_type(), getReimb_status());
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", reimb_amount=" + reimb_amount +
                ", reimb_desc='" + reimb_desc + '\'' +
                ", reimb_date='" + reimb_date + '\'' +
                ", resolve_date='" + resolve_date + '\'' +
                ", reimb_author=" + reimb_author +
                ", reimb_resolver=" + reimb_resolver +
                ", reimb_type='" + reimb_type + '\'' +
                ", reimb_status='" + reimb_status + '\'' +
                '}';
    }
}
