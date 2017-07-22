package com.data.ets;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by User on 15/7/2560.
 */
@Parcel
public class User {
    String userCode;
    String userName;
    String userSurname;
    String userBirthday;
    String userGender;
    public User(){}

    @ParcelConstructor
    public User(String userCode, String userName, String userSurname, String userBirthday, String userGender) {
        this.userCode = userCode;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}