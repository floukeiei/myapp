package com.data.ets;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by User on 26/11/2560.
 */
@Parcel
public class Follower {
    private  String email;

    private String userKey;
    private String followKey;


    public Follower(){}
    @ParcelConstructor
    public Follower(String email, String userKey) {

        this.userKey = userKey;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getFollowKey() {
        return followKey;
    }

    public void setFollowKey(String followKey) {
        this.followKey = followKey;
    }
}
