package com.data.ets;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.Date;


@Parcel
public class Plan {
    private String planKey;
    private String planLevel;
    private String planMaxLevel;
    private float planVo2Max;
    private float planDistance;
    private int planTime;
    private long planDate;
    private String userKey;

    private int startHR;
    private int endHR;


    public Plan(){}
    @ParcelConstructor
    public Plan(String planLevel, String planMaxLevel, float planVo2Max, float planDistance, int planTime, long planDate, String userKey) {
        this.planLevel = planLevel;
        this.planMaxLevel = planMaxLevel;
        this.planVo2Max = planVo2Max;
        this.planDistance = planDistance;
        this.planTime = planTime;
        this.planDate = planDate;
        this.userKey = userKey;
    }




    public String getPlanKey() {
        return planKey;
    }

    public void setPlan3Key(String planKey) {
        this.planKey = planKey;
    }

    public String getPlanLevel() {
        return planLevel;
    }

    public void setPlanLevel(String planLevel) {
        this.planLevel = planLevel;
    }

    public float getPlanVo2Max() {
        return planVo2Max;
    }

    public void setPlanVo2Max(float planVo2Max) {
        this.planVo2Max = planVo2Max;
    }

    public float getPlanDistance() {
        return planDistance;
    }

    public void setPlanDistance(float planDistance) {
        this.planDistance = planDistance;
    }

    public int getPlanTime() {
        return planTime;
    }

    public void setPlanTime(int planTime) {
        this.planTime = planTime;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getPlanMaxLevel() {
        return planMaxLevel;
    }

    public void setPlanMaxLevel(String planMaxLevel) {
        this.planMaxLevel = planMaxLevel;
    }

    public long getPlanDate() {
        return planDate;
    }

    public void setPlanDate(long planDate) {
        this.planDate = planDate;
    }

    public int getStartHR() {
        return startHR;
    }

    public void setStartHR(int startHR) {
        this.startHR = startHR;
    }

    public int getEndHR() {
        return endHR;
    }

    public void setEndHR(int endHR) {
        this.endHR = endHR;
    }
}
