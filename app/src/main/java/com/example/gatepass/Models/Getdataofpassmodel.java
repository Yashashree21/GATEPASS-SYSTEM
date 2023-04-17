package com.example.gatepass.Models;

public class Getdataofpassmodel {

    String department,email,fullname,phoneno,reason,rollno,year,url;
    public Getdataofpassmodel () {
    }

    public Getdataofpassmodel (String department, String email, String fullname, String phoneno, String reason, String rollno, String year,String url) {
        this.department = department;
        this.email = email;
        this.fullname = fullname;
        this.phoneno = phoneno;
        this.reason = reason;
        this.rollno = rollno;
        this.year = year;
        this.url = url;
    }


    public String getUrl ( ) {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getDepartment ( ) {
        return department;
    }

    public void setDepartment (String department) {
        this.department = department;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getFullname ( ) {
        return fullname;
    }

    public void setFullname (String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneno ( ) {
        return phoneno;
    }

    public void setPhoneno (String phoneno) {
        this.phoneno = phoneno;
    }

    public String getReason ( ) {
        return reason;
    }

    public void setReason (String reason) {
        this.reason = reason;
    }

    public String getRollno ( ) {
        return rollno;
    }

    public void setRollno (String rollno) {
        this.rollno = rollno;
    }

    public String getYear ( ) {
        return year;
    }

    public void setYear (String year) {
        this.year = year;
    }
}
