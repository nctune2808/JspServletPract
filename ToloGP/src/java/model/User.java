/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class User implements java.io.Serializable {
    private int uID;
    private String uUsername;
    private String uPassword;
    private String uRole;    //Admin, Patient, Staff

    public User() {}

    public User(String uUsername, String uPassword) {
        this.uUsername = uUsername;
        this.uPassword = uPassword;
    }
    
    public User(String uUsername, String uPassword, String uRole) {
        this.uUsername = uUsername;
        this.uPassword = uPassword;
        this.uRole = uRole;
    }
    
    public User(int uID, String uUsername, String uPassword, String uRole) {
        this.uID = uID;
        this.uUsername = uUsername;
        this.uPassword = uPassword;
        this.uRole = uRole;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    @Override
    public String toString() {
        return "User{" + "uID=" + uID + ", uUsername=" + uUsername + ", uPassword=" + uPassword + ", uRole=" + uRole + '}';
    }

    

    
}
