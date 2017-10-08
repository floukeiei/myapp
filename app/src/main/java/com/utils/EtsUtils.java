package com.utils;

import com.data.ets.History;
import com.data.ets.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Created by User on 22/7/2560.
 */

public class EtsUtils {
    private  String  riskDanger= "5";
    private  String  riskVeryHigh= "4";
    private  String  riskHigh= "3";
    private  String  riskMedium= "2";
    private  String  riskLess= "1";

    public String calRisk(User user, History history) throws ParseException {
        try {
                        int age = getAge(user.getUserBirthday());
            float cholesterol = Float.valueOf(history.getHistCholesterol());
            float bloodpressure = Float.valueOf(history.getHistBloodPressure());
            float waistline = Float.valueOf(history.getHistWaistline());
            float height = Float.valueOf(history.getHistHeight()) / 2;

            if (history.getHistCholesterol() != null) { //รู้คอลเลสเตอรอล
                if ("Y".equals(history.getHistDiabetes())) { //เป็นเบาหวาน
                    if ("F".equals(user.getUserGender())) { //เพศชาย
                        if ("Y".equals(history.getHistSmoking())) { //สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else if (cholesterol >= 160 && bloodpressure >= 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (cholesterol >= 280 && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (cholesterol >= 280 && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (cholesterol < 280 && bloodpressure < 140) {
                                    return riskMedium;
                                } else if (cholesterol >= 320 && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskDanger;
                                } else if (cholesterol >= 160 && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskHigh;
                                }
                            } else if (age >= 65) {    //65+
                                if (cholesterol < 320 && bloodpressure < 140) {
                                    return riskHigh;
                                } else if (cholesterol >= 320 && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskVeryHigh;
                                } else {
                                    return riskDanger;
                                }
                            }
                        } else { //ไม่สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                if (cholesterol >= 240 && bloodpressure >= 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (cholesterol >= 240 && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskMedium;
                                } else if (cholesterol >= 160 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) {
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (cholesterol < 280 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) {
                                    return riskHigh;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (cholesterol >= 240 && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (cholesterol < 280 && bloodpressure < 140) {
                                    return riskMedium;
                                } else if (cholesterol >= 280 && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskDanger;
                                } else if (cholesterol >= 160 && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskHigh;
                                }
                            }
                        }

                    } else { //เพศหญิง
                        if ("Y".equals(history.getHistSmoking())) { //สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล > 240 && ความดัน > 180
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (cholesterol >= 280 && bloodpressure >= 140 && bloodpressure < 160) { //คอลลเลสเตอรอล > 280 && ความดัน 140-159
                                    return riskMedium;
                                } else if (cholesterol >= 160 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล > 160 && ความดัน160-179
                                    return riskMedium;
                                } else if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) { //คอลเลสเตอรอล 160-239 && ความดัน > 180
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล >240 && ความดัน > 180
                                    return riskHigh;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (cholesterol < 280 && bloodpressure < 140) { //คอลเลสเตอรอล < 280 && ความดัน <140
                                    return riskLess;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล > 280 && ความดัน 160-179
                                    return riskHigh;
                                } else if (cholesterol >= 160 && bloodpressure >= 180) { //คอลเลสเตอรอล >160 && ความดัน > 180
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 180) { //คอลเลสเตอรอล > 280 && ความดัน > 180
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (cholesterol >= 280 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล > 280 && ความดัน 140-159
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล 160-279 && ความดัน 160-179
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>280 && ความดัน 160-179
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 180) { //คอลเลสเตอรอล 160-279 && ความดัน > 180
                                    return riskVeryHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 180) { //คอลเลสเตอรอล > 280 && ความดัน > 180
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (cholesterol < 280 && bloodpressure < 140) { //คอลเลสเตอรอล< 280 && ความดัน < 140
                                    return riskMedium;
                                } else if (cholesterol >= 280 && bloodpressure >= 120 && bloodpressure < 140) { //คอลเลสเตอรอล > 280 && ความดัน 120-139
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 320 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล 160 -319 && ความดัน140 - 159
                                    return riskHigh;
                                } else if (cholesterol >= 320 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล >320 && ความดัน 140-159
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล 160 -279 && ความดัน 160-179
                                    return riskVeryHigh;
                                } else {
                                    return riskDanger;
                                }
                            }
                        } else { //ไม่สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (cholesterol >= 320 && bloodpressure >= 180) { // คอลเลสเตอรอล > 320 && ความดัน > 180
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 320 && bloodpressure >= 180) { // คอลเลสเตอรอล 160-319 && ความดัน > 180
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล >240 && ความดัน 160-179
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (cholesterol >= 280 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล > 280 && ความดัน 140-159
                                    return riskMedium;
                                } else if (cholesterol >= 160 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล > 160 && ความดัน 160-179
                                    return riskMedium;
                                } else if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) { //คอลเลสเตอรอล 160-239 && ความดัน > 180
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล > 240 && ความดัน > 180
                                    return riskHigh;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (cholesterol < 280 && bloodpressure < 140) { //คอลเลสเตอรอล < 280 && ความดัน <140
                                    return riskLess;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล > 240 && ความดัน 160-179
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 180) { //คอลเลสเตอรอล 160-279 && ความดัน > 180
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 180) { //คอลเลสเตอรอลล > 280 && ความดัน >180
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (cholesterol >= 240 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล > 240 && ความดัน 140-159
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล 160-279&& ความดัน 160-179
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล > 280 && ความดัน 160-179
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) { //คอลเลสเตอรอลล 160-239 && ความดัน > 180
                                    return riskVeryHigh;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล > 240 && ความดัน > 180
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            }
                        }
                    }


                } else { //ไม่เป็นเบาหวาน
                    if ("F".equals(user.getUserGender())) { //เพศชาย
                        if ("Y".equals(history.getHistSmoking())) { //สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                if (cholesterol >= 160 && bloodpressure >= 180) { //คอลเลสเตอรอล>160&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>240&&ความดัน>160-179
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (cholesterol >= 160 && bloodpressure >= 180) { //คอลเลสเตอรอล>160&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>240&&ความดัน160-179
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-599
                                if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) { //คอลเลสเตอรอล160-239&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 160 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>160&&ความดัน160-179
                                    return riskMedium;
                                } else if (cholesterol >= 280 && bloodpressure >= 140 && bloodpressure < 160) { // คอลเลสเตอรอล>280&&ความดัน140-159
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล>240&&ความดัน>180
                                    return riskHigh;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 180) { //คอลเลสเตอรอล160-279&&ความดัน>180
                                    return riskHigh;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>240&&ความดัน160-179
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 180) { //คอลเลสเตอรอล>280&&ความดัน>180
                                    return riskVeryHigh;
                                } else if (cholesterol < 280 && bloodpressure < 140) { //คอลเลสเตอรอล<280&&ความดัน<140
                                    return riskLess;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล160-279&&ความดัน160-179
                                    return riskHigh;
                                } else if (cholesterol >= 240 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล>240&&ความดัน140-159
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>280&&ความดัน160-179
                                    return riskVeryHigh;
                                } else if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) { //คอลเลสเตอรอล160-239&&ความดัน>180
                                    return riskVeryHigh;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล>240&&ความดัน>180
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            }
                        } else { //ไม่สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (cholesterol >= 200 && bloodpressure >= 180) { //คอลเลสเตอรอล > 200 && ความดัน > 180
                                    return riskMedium;
                                } else if (cholesterol >= 320 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล > 320 && ความดัน160 - 179
                                    return riskMedium;
                                } else return riskLess;
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (cholesterol >= 160 && cholesterol < 320 && bloodpressure >= 180) {//คอลเลสเตอรอล160-319&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล > 240 && ความดัน160 - 179
                                    return riskMedium;
                                } else if (cholesterol >= 320 && bloodpressure >= 180) { //คอลเลสเตอรอล > 320 && ความดัน > 180
                                    return riskHigh;
                                } else return riskLess;
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (cholesterol >= 160 && cholesterol < 200 && bloodpressure >= 180) { // คอลเลสเตอรอล160-199&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 160 && cholesterol < 320 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล160-319&&ความดัน160-179
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล>240&&ความดัน140-159
                                    return riskMedium;
                                } else if (cholesterol >= 200 && bloodpressure >= 180) { //คอลเลสเตอรอล>200&&ความดัน>180
                                    return riskHigh;
                                } else if (cholesterol >= 320 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>320&&ความดัน160-179
                                    return riskHigh;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 65) {    //65+
                                if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) { //คอลเลสเตอรอล160-239&&ความดัน>180
                                    return riskHigh;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>240&&ความดัน160-179
                                    return riskHigh;
                                } else if (cholesterol < 240 && bloodpressure >= 120 && bloodpressure < 140) { //คอลเลสเตอรอล<240&&ความดัน120-139
                                    return riskLess;
                                } else if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล>240&&ความดัน>180
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            }
                        }

                    } else { //เพศหญิง
                        if ("Y".equals(history.getHistSmoking())) { //สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล>240&&ความดัน>180
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (cholesterol >= 160 && bloodpressure >= 180) { //คอลเลสเตอรอล>160&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>240&&ความดัน160-179
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (cholesterol >= 240 && bloodpressure >= 180) { //คอลเลสเตอรอล>240&&ความดัน>180
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 240 && bloodpressure >= 180) { //คอลเลสเตอรอล160-239&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 160 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>160&&ความดัน160-179
                                    return riskMedium;
                                } else if (cholesterol >= 280 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล>280&&ความดัน140-159
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 65) {    //65+
                                if (cholesterol < 280 && bloodpressure < 140) { // คอลเลสเตอรอล<280&&ความดัน<140
                                    return riskLess;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>240&&ความดัน160-179
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 280 && bloodpressure >= 180) { //คอลเลสเตอรอล160-279&&ความดัน>180
                                    return riskHigh;
                                } else if (cholesterol >= 280 && bloodpressure >= 180) { //คอลเลสเตอรอล>280&&ความดัน>180
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            }
                        } else { //ไม่สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (cholesterol >= 320 && bloodpressure >= 180) { //คอลเลสเตอรอล>320&&ความดัน>180
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (cholesterol >= 240 && bloodpressure >= 180) {  //คอลเลสเตอรอล>240&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 320 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>320&&ความดัน160-179
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (cholesterol >= 320 && bloodpressure >= 180) { //คอลเลสเตอรอล>320&&ความดัน>180
                                    return riskHigh;
                                } else if (cholesterol >= 160 && cholesterol < 320 && bloodpressure >= 180) { //คอลเลสเตอรอล160-319&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>240&&ความดัน160-179
                                    return riskMedium;
                                } else {

                                }
                            } else if (age >= 65) {    //65+
                                if (cholesterol >= 200 && bloodpressure >= 180) { //คอลเลสเตอรอล>200&&ความดัน>180
                                    return riskHigh;
                                } else if (cholesterol >= 320 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล>320&&ความดัน160-179
                                    return riskHigh;
                                } else if (cholesterol >= 160 && bloodpressure < 200 && bloodpressure >= 180) { //คอลเลสเตอรอล160-199&&ความดัน>180
                                    return riskMedium;
                                } else if (cholesterol >= 160 && cholesterol < 320 && bloodpressure >= 160 && bloodpressure < 180) { //คอลเลสเตอรอล160-319&&ความดัน160-179
                                    return riskMedium;
                                } else if (cholesterol >= 240 && bloodpressure >= 140 && bloodpressure < 160) { //คอลเลสเตอรอล>240&&ความดัน140-159
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            }
                        }
                    }
                }
            } else { //ไม่รู้คอลเลสเตอรอล
                if ("Y".equals(history.getHistDiabetes())) { //เป็นเบาหวาน
                    if ("F".equals(user.getUserGender())) { //เพศชาย
                        if ("Y".equals(history.getHistSmoking())) { //สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                if (bloodpressure >= 180) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (bloodpressure < 120) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (bloodpressure < 120) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskMedium;
                                } else if (waistline < height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskHigh;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (bloodpressure < 120) {
                                    return riskMedium;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskHigh;
                                }
                            } else if (age >= 65) {    //65+
                                if (bloodpressure < 120) {
                                    return riskMedium;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskVeryHigh;
                                } else if (waistline < height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskDanger;
                                } else if (bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskHigh;
                                }
                            }
                        } else { //ไม่สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                if (waistline > height && bloodpressure >= 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskMedium;
                                } else if (bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (bloodpressure < 120) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskHigh;
                                } else if (bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (bloodpressure < 120) {
                                    return riskMedium;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskHigh;
                                }
                            }
                        }

                    } else { //เพศหญิง
                        if ("Y".equals(history.getHistSmoking())) { //สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                if (waistline > height && bloodpressure >= 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskMedium;
                                } else if (bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (bloodpressure < 120) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (bloodpressure < 120) {
                                    return riskMedium;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskVeryHigh;
                                } else if (waistline < height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else {
                                    return riskDanger;
                                }
                            }
                        } else { //ไม่สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (bloodpressure >= 180) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else {

                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (bloodpressure < 120) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            }
                        }
                    }


                } else { //ไม่เป็นเบาหวาน
                    if ("F".equals(user.getUserGender())) { //เพศชาย
                        if ("Y".equals(history.getHistSmoking())) { //สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else if (bloodpressure >= 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-599
                                if (bloodpressure < 120) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (waistline < height && bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskVeryHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskDanger;
                                } else {
                                    return riskMedium;
                                }
                            }
                        } else { //ไม่สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (waistline > height && bloodpressure >= 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else if (bloodpressure >= 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (waistline > height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskLess;
                                } else if (bloodpressure < 140) {
                                    return riskLess;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (waistline > height && bloodpressure >= 180) {
                                    return riskVeryHigh;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (bloodpressure < 120) {
                                    return riskLess;
                                } else {
                                    return riskMedium;
                                }
                            }
                        }

                    } else { //เพศหญิง
                        if ("Y".equals(history.getHistSmoking())) { //สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (bloodpressure >= 180) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (waistline > height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskMedium;
                                } else if (bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else if (waistline < height && bloodpressure >= 180) {
                                    return riskMedium;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (bloodpressure < 120) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (bloodpressure<120) {
                                    return riskLess;
                                } else if (waistline<height&&bloodpressure>=120&&bloodpressure<140) {
                                    return riskLess;
                                } else if (waistline>height&&bloodpressure>=120&&bloodpressure<140) {
                                    return riskMedium;
                                } else if (waistline<height&&bloodpressure>=140&&bloodpressure<160) {
                                    return riskMedium;
                                } else if (waistline>height&&bloodpressure>=180) {
                                    return riskVeryHigh;
                                } else {
                                    return riskHigh;
                                }
                            }
                        } else { //ไม่สูบบุหรี่
                            if (age >= 40 && age <= 49) {   //40-49
                                return riskLess;
                            } else if (age >= 50 && age <= 54) {    //50-54
                                if (waistline > height && bloodpressure > 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 55 && age <= 59) {    //55-59
                                if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskMedium;
                                } else if (bloodpressure > 180) {
                                    return riskMedium;
                                } else {
                                    return riskLess;
                                }
                            } else if (age >= 60 && age <= 64) {    //60-64
                                if (bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 140 && bloodpressure < 160) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskMedium;
                                }
                            } else if (age >= 65) {    //65+
                                if (bloodpressure < 120) {
                                    return riskLess;
                                } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                    return riskLess;
                                } else if (waistline > height && bloodpressure >= 160 && bloodpressure < 180) {
                                    return riskHigh;
                                } else if (bloodpressure >= 180) {
                                    return riskHigh;
                                } else {
                                    return riskMedium;
                                }
                            }
                        }
                    }
                }

            }
            if (false) {
                return "Y";
            }
        } catch (ParseException e)

        {
            e.printStackTrace();
        }
        return null;
    }


    public int getAge(String birthdate) throws ParseException {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTime(sdf.parse(birthdate));
        return getDiffYears(myCalendar, Calendar.getInstance());
    }

    public static int getDiffYears(Calendar first, Calendar last) {
        Calendar a = first;
        Calendar b = last;
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }
}
