package com.example.gatepass.Models;

public class CommonModel {
    String name,department,status,url;

    public CommonModel ( ) {
    }

    public CommonModel (String name, String department, String status,String url) {
        this.name = name;
        this.department = department;
        this.status = status;
        this.url=url;
    }

    public String getName ( ) {
        return name;
    }

    public String getUrl ( ) {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDepartment ( ) {
        return department;
    }

    public void setDepartment (String department) {
        this.department = department;
    }

    public String getStatus ( ) {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }
}
