package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.data.ets.History;
import com.data.ets.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.utils.EtsUtils;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.Date;

public class ShowUserActivity extends AppCompatActivity {
    private static History history;
    private static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuser);

        getSupportActionBar().setTitle(getResources().getString(R.string.heading_title_check));
        //START
        Button buttonBack = (Button) findViewById(R.id.showuser_edit);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), NameActivity.class);
                startActivity(i);
            }
        });
        //END

        TextView textViewFullname = (TextView) findViewById(R.id.showuser_fullname_value);
        TextView textViewGender = (TextView) findViewById(R.id.showuser_gender_value);
        TextView textViewAge = (TextView) findViewById(R.id.showuser_gender_value);
//        TextView textViewHeight = (TextView) findViewById(R.id.showuser_height_value);
//        TextView textViewWeight = (TextView) findViewById(R.id.showuser_weight_value);
//        TextView textViewWaistline = (TextView) findViewById(R.id.showuser_waistline_value);
//        TextView textViewPressure = (TextView) findViewById(R.id.showuser_pressure_value);
//        TextView textViewBloodsugar = (TextView) findViewById(R.id.showuser_bloodsugar_value);
//        TextView textViewCholesterol = (TextView) findViewById(R.id.showuser_cholesterol_value);


         history = Parcels.unwrap(getIntent().getParcelableExtra("history"));
         user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        Bundle bundle = getIntent().getExtras();
        String page = "";
        int renderSubmitBtn = View.VISIBLE;
        if (bundle != null) {
            page = bundle.getString("fromPage");
        }
        if("Menu".equals(page)){
            renderSubmitBtn = View.INVISIBLE;
             history =  EtsUtils.getSavedObjectFromPreference(getApplicationContext(),"history", History.class);
             user = EtsUtils.getSavedObjectFromPreference(getApplicationContext(),"user", User.class);
        }


        //START ปุ่มยืนยัน
        Button buttonNext = (Button) findViewById(R.id.showuser_submit);
        buttonNext.setVisibility(renderSubmitBtn);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try {
                     switch (EtsUtils.calRisk(user, history)){
                         case  EtsUtils.riskDanger:history.setHistRisk( EtsUtils.riskDanger); break;
                         case  EtsUtils.riskVeryHigh: history.setHistRisk(EtsUtils.riskVeryHigh); break;
                         case  EtsUtils.riskHigh:history.setHistRisk(EtsUtils.riskHigh); break;
                         case  EtsUtils.riskMedium:history.setHistRisk(EtsUtils.riskMedium); break;
                         case  EtsUtils.riskLess: history.setHistRisk(EtsUtils.riskLess); break;
                    }

                    //ทำplan ตรงนี้


                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference mRootRef = database.getReference();
                    DatabaseReference mUsersRef = mRootRef.child("user");



                    mUsersRef = mUsersRef.push(); // ตรงนี้จองคีให้ user
                    mUsersRef.setValue(user);// ตรงนี้ใส่ข้อมูลลงไป
                    String userKey = mUsersRef.getKey(); //ตรงนี้ดึงคีของ user มาเก็บ
                    history.setUserKey(userKey);//เอาคีไปเก็บใqส่ hist
                    history.setHistDate(new Date().getTime());
                    user.setUserCode(userKey);


                    DatabaseReference mHistoryRef = mRootRef.child("history/"+userKey);
                    DatabaseReference mPlanRef = mRootRef.child("plan/"+userKey);
                    mHistoryRef.push().setValue(history); //บันทึก hist ลง firebase
                    mPlanRef.push().setValue(EtsUtils.getExercisePlan(userKey,history,user));  //บันทึก plan

                    EtsUtils.saveObjectToSharedPreference(getApplicationContext(),"user",user);
                    Intent i = new Intent(v.getContext(), MenuActivity.class);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        //END

        if(user!= null) {
            textViewFullname.setText(user.getUserName() + " " + user.getUserSurname());
            textViewGender.setText(user.getUserName() + " " + user.getUserSurname());
            textViewAge.setText(user.getUserName() + " " + user.getUserSurname());
        }
//        textViewHeight.setText(history.getHistHeight());
//        textViewWeight.setText(history.getHistWeight());
//        textViewWaistline.setText(history.getHistWaistline());
//        textViewPressure.setText(history.getHistBloodPressure());
//        textViewBloodsugar.setText(history.getHistBloodSugar());
//        textViewCholesterol.setText(history.getHistCholesterol());




    }
}
