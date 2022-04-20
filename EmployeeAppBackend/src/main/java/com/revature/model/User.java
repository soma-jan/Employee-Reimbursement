package com.revature.model;

import java.util.Objects;

public class User {
    private int id;
    private  String userName;
    private  String password;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String userRole;

    public User() {
    }


    public User(int userid, String username, String pass, String userrole) {
        this.id = userid;
        this.userName =username;
        this.password =pass;

    }


    public User(int usrid, String username, String pass,String firstname, String lastname, String email, String userrole) {
        this.id =usrid;
        this.userName =username;
        this.password =pass;
        this.firstName =firstname;
        this.lastName =lastname;
        this.email =email;
        this.userRole=userrole;
    }
   public User( String firstname, String lastname, String email, String userrole) {

        this.firstName =firstname;
        this.lastName =lastname;
        this.email =email;
        this.userRole=userrole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getUserRole(), user.getUserRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPassword(), getFirstName(), getLastName(), getEmail(), getUserRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }

}
