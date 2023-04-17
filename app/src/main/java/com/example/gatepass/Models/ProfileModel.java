package com.example.gatepass.Models;

public class ProfileModel {
    String department,email,fullname,password,phoneno,rollno,year,url;

    public ProfileModel ( ) {
    }

    public ProfileModel (String department, String email, String fullname, String password, String phoneno, String rollno,String year,String url) {
        this.department = department;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.phoneno = phoneno;
        this.rollno = rollno;
        this.year = year;
        this.url= url;
    }


    public String getUrl ( ) {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public ProfileModel (String year) {
        this.year = year;
    }

    public String getYear ( ) {
        return year;
    }

    public void setYear (String year) {
        this.year = year;
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

    public String getPassword ( ) {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getPhoneno ( ) {
        return phoneno;
    }

    public void setPhoneno (String phoneno) {
        this.phoneno = phoneno;
    }

    public String getRollno ( ) {
        return rollno;
    }

    public void setRollno (String rollno) {
        this.rollno = rollno;
    }
}
