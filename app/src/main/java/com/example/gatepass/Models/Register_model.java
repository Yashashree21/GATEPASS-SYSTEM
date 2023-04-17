package com.example.gatepass.Models;

public class Register_model {
    String fullname,email,rollno,phoneno,department,password,reason,year,url;

    public Register_model ( ) {
    }


    public Register_model (String fullname, String email, String rollno, String phoneno,String year, String department, String password, String reason,String url) {
        this.fullname = fullname;
        this.email = email;
        this.rollno = rollno;
        this.phoneno = phoneno;
        this.year  = year;
        this.department = department;
        this.password = password;
        this.reason = reason;
        this.url = url;
    }


    public String getUrl ( ) {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getFullname ( ) {
        return fullname;
    }

    public void setFullname (String fullname) {
        this.fullname = fullname;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getRollno ( ) {
        return rollno;
    }

    public void setRollno (String rollno) {
        this.rollno = rollno;
    }

    public String getPhoneno ( ) {
        return phoneno;
    }

    public void setPhoneno (String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDepartment ( ) {
        return department;
    }

    public void setDepartment (String department) {
        this.department = department;
    }

    public String getPassword ( ) {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getReason ( ) {
        return reason;
    }

    public void setReason (String reason) {
        this.reason = reason;
    }

    public String getYear ( ) {
        return year;
    }

    public void setYear (String year) {
        this.year = year;
    }
}
