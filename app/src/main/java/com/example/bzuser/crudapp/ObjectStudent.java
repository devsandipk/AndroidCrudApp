package com.example.bzuser.crudapp;

/**
 * Created by bzuser on 7/7/17.
 */
public class ObjectStudent {
    int id;
    String firstname;
    String email;

    public ObjectStudent() {
    }

    public ObjectStudent(String firstname, String email) {
        this.firstname = firstname;
        this.email = email;
    }

    public ObjectStudent(int id, String firstname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
