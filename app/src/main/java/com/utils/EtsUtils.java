package com.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.data.ets.History;
import com.data.ets.Plan;
import com.data.ets.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Semaphore;

import nodomain.freeyourgadget.gadgetbridge.impl.GBDevice;

import static android.content.Context.MODE_PRIVATE;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Created by User on 22/7/2560.
 */

public class EtsUtils {
    public static final String riskDanger = "5";
    public static final String riskVeryHigh = "4";
    public static final String riskHigh = "3";
    public static final String riskMedium = "2";
    public static final String riskLess = "1";

    private static final String male = "M";

    public static final String lightweightExercise = "L";
    public static final String moderateExercise = "M";
    public static final String heavyExercise = "H";

    private static final double kilotoPound = 2.2046226218;
    private static final String fileName = "ETS";

    private static GBDevice mGBDevice;

    public static String calRisk(User user, History history) throws Exception {
        int age = getAge(user.getUserBirthday());

        float cholesterol = (StringUtils.isNotEmpty(history.getHistCholesterol())) ? Float.valueOf(history.getHistCholesterol()) : 0.0f;
        float bloodpressure = (StringUtils.isNotEmpty(history.getHistBloodPressure())) ? Float.valueOf(history.getHistBloodPressure()) : 0.0f;
        float waistline = (StringUtils.isNotEmpty(history.getHistWaistline())) ? Float.valueOf(history.getHistWaistline()) : 0.0f;
        float height = (StringUtils.isNotEmpty(history.getHistHeight())) ? Float.valueOf(history.getHistHeight()) : 0.0f;
        //float bloodpressure = (history.getHistBloodPressure() != null) ? Float.valueOf(history.getHistBloodPressure()) : 0.0f;
        //float waistline = (history.getHistWaistline() != null) ? Float.valueOf(history.getHistWaistline()) : 0.0f;
        //float height = (history.getHistHeight() != null) ? Float.valueOf(history.getHistHeight()) / 2 : 0.0f;

        if (history.getHistCholesterol() != null) { //รู้คอลเลสเตอรอล
            if ("Y".equals(history.getHistDiabetes())) { //เป็นเบาหวาน
                if (male.equals(user.getUserGender())) { //เพศชาย
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
                if (male.equals(user.getUserGender())) { //เพศชาย
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
                if (male.equals(user.getUserGender())) { //เพศชาย
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
                if (male.equals(user.getUserGender())) { //เพศชาย
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
                            if (bloodpressure < 120) {
                                return riskLess;
                            } else if (waistline < height && bloodpressure >= 120 && bloodpressure < 140) {
                                return riskLess;
                            } else if (waistline > height && bloodpressure >= 120 && bloodpressure < 140) {
                                return riskMedium;
                            } else if (waistline < height && bloodpressure >= 140 && bloodpressure < 160) {
                                return riskMedium;
                            } else if (waistline > height && bloodpressure >= 180) {
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

        return null;
    }


    public static int getAge(String birthdate) throws ParseException {
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

    public static String getExerciseMaxLevel(History history) {
        float bloodpressure = (StringUtils.isNotEmpty(history.getHistBloodPressure())) ? Float.valueOf(history.getHistBloodPressure()) : 0.0f;
        float bloodsugar = (StringUtils.isNotEmpty(history.getHistBloodSugar())) ? Float.valueOf(history.getHistBloodSugar()) : 0.0f;

        if (bloodsugar > 250 && bloodpressure >= 180) {
            return lightweightExercise;
        } else if (riskLess.equals(history.getHistRisk()) || riskMedium.equals(history.getHistRisk())) {
            return heavyExercise;
        } else  {
            return moderateExercise;
        }


    }

    public static Plan getExercisePlan(String userKey, History history) {
        Plan plan = new Plan();
        String maxLevel = getExerciseMaxLevel(history);
        switch (maxLevel) {
            case lightweightExercise:
                plan.setPlanMaxLevel(maxLevel);
                plan.setPlanTime(5);
                break;
            case moderateExercise:
                plan.setPlanMaxLevel(maxLevel);
                plan.setPlanTime(5);
                break;
            case heavyExercise:
                plan.setPlanMaxLevel(maxLevel);
                plan.setPlanTime(3);
                break;
        }
        plan.setPlanLevel(lightweightExercise);
        plan.setUserKey(userKey);
        plan.setPlanDate(Calendar.getInstance().getTime());
        return plan;
    }

    public static double calVo2Max(User user,History history,double avgHR,double time) throws Exception {

            int age = getAge(user.getUserBirthday());
        double pound = Double.parseDouble(history.getHistWeight()) * kilotoPound;
        int gender = male.equals(user.getUserGender())? 1  : 0    ;
        return 132.28-(0.077*pound)-(0.39*age)+(6.32*gender)-(3.26*time)-(0.16*avgHR);
    }
    public static void saveObjectToSharedPreference(Context context, String objectKey, Object object) {

        SharedPreferences  mPrefs = context.getSharedPreferences(fileName,MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = mPrefs.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(objectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }


     private  static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final static  DatabaseReference mRootRef = database.getReference();
    private final static   DatabaseReference mPlanRef = mRootRef.child("plan");
    private final static   DatabaseReference mHistoryRef = mRootRef.child("history");
    private static History history = new History();

    public static History getHistoryByUserID(String userKey)throws Exception{


        mHistoryRef.orderByChild("userKey").equalTo(userKey).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                history = dataSnapshot.getValue(History.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            // ...
        });
        return history;
    }

    public static GBDevice getmGBDevice() {
        return mGBDevice;
    }

    public static void setmGBDevice(GBDevice mGBDevice) {
        EtsUtils.mGBDevice = mGBDevice;
    }
}
