package com.data.ets;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.Date;

/**
 * Created by User on 26/11/2560.
 */
@Parcel
public class HistEx {
    private  String histexKey;
    private float histexDistance;
    private int histexTime;
    private Date histexDate;
    private String userKey;
    private double vo2Max;

    public  HistEx(){}
    @ParcelConstructor
    public HistEx(String histexKey, float histexDistance, int histexTime, Date histexDate, String userKey, double vo2Max) {
        this.histexKey = histexKey;
        this.histexDistance = histexDistance;
        this.histexTime = histexTime;
        this.histexDate = histexDate;
        this.userKey = userKey;
        this.vo2Max = vo2Max;
    }




    public String getHistexKey() {
        return histexKey;
    }

    public void setHistexKey(String histexKey) {
        this.histexKey = histexKey;
    }

    public float getHistexDistance() {
        return histexDistance;
    }

    public void setHistexDistance(float histexDistance) {
        this.histexDistance = histexDistance;
    }

    public int getHistexTime() {
        return histexTime;
    }

    public void setHistexTime(int histexTime) {
        this.histexTime = histexTime;
    }

    public Date getHistexDate() {
        return histexDate;
    }

    public void setHistexDate(Date histexDate) {
        this.histexDate = histexDate;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public double getVo2Max() {
        return vo2Max;
    }

    public void setVo2Max(double vo2Max) {
        this.vo2Max = vo2Max;
    }

}
