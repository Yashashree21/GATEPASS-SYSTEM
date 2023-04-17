package com.example.gatepass.Models;

public class ApprovedModel {
    String fullname,department,email,url;

    public ApprovedModel ( ) {
    }

    public ApprovedModel (String fullname, String department, String email,String url) {
        this.fullname = fullname;
        this.department = department;
        this.email = email;
        this.url=url;
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
}
