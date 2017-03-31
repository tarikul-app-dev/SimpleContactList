package com.example.tarikul.simplecontactlist;

/**
 * Created by Alamgir Hossain on 3/30/2017.
 */

public class ContactListModel {
    String userName;
    String userPhoneNumber;

    public ContactListModel(String userName, String userPhoneNumber) {
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }



}
