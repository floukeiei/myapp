package com.data.ets;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by User on 16/7/2560.
 */
@Parcel
public class History  {
    String userCode;
    String histDiabetes;
    String histSmoking;
    String histBloodPressure;
    String histCholesterol;
    String histBloodSugar;
    String histHeight;
    String histWeight;
    String histWaistline;
    String histRisk;
    long histDate;
    String userKey;

    public History(){}

    @ParcelConstructor
    public History(String userCode, String histDiabetes, String histSmoking, String histBloodPressure, String histCholesterol, String histBloodSugar, String histHeight, String histWeight, String histWaistline, String histRisk) {
        this.userCode = userCode;
        this.histDiabetes = histDiabetes;
        this.histSmoking = histSmoking;
        this.histBloodPressure = histBloodPressure;
        this.histCholesterol = histCholesterol;
        this.histBloodSugar = histBloodSugar;
        this.histHeight = histHeight;
        this.histWeight = histWeight;
        this.histWaistline = histWaistline;
        this.histRisk = histRisk;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getHistDiabetes() {
        return histDiabetes;
    }

    public void setHistDiabetes(String histDiabetes) {
        this.histDiabetes = histDiabetes;
    }

    public String getHistSmoking() {
        return histSmoking;
    }

    public void setHistSmoking(String histSmoking) {
        this.histSmoking = histSmoking;
    }

    public String getHistBloodPressure() {
        return histBloodPressure;
    }

    public void setHistBloodPressure(String histBloodPressure) {
        this.histBloodPressure = histBloodPressure;
    }

    public String getHistCholesterol() {
        return histCholesterol;
    }

    public void setHistCholesterol(String histCholesterol) {
        this.histCholesterol = histCholesterol;
    }

    public String getHistBloodSugar() {
        return histBloodSugar;
    }

    public void setHistBloodSugar(String histBloodSugar) {
        this.histBloodSugar = histBloodSugar;
    }

    public String getHistHeight() {
        return histHeight;
    }

    public void setHistHeight(String histHeight) {
        this.histHeight = histHeight;
    }

    public String getHistWeight() {
        return histWeight;
    }

    public void setHistWeight(String histWeight) {
        this.histWeight = histWeight;
    }

    public String getHistWaistline() {
        return histWaistline;
    }

    public void setHistWaistline(String histWaistline) {
        this.histWaistline = histWaistline;
    }

    public String getHistRisk() {
        return histRisk;
    }

    public void setHistRisk(String histRisk) {
        this.histRisk = histRisk;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public long getHistDate() {
        return histDate;
    }

    public void setHistDate(long histDate) {
        this.histDate = histDate;
    }
}
