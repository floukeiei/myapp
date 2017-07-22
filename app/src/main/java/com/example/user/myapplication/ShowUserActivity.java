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

import org.parceler.Parcels;
import org.w3c.dom.Text;

public class ShowUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuser);

        //START
        Button buttonBack = (Button) findViewById(R.id.showuser_edit);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), NameActivity.class);
                startActivity(i);
            }
        });
        //END

        //START
        Button buttonNext = (Button) findViewById(R.id.showuser_submit);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {



            }
        });
        //END

        TextView textViewFullname = (TextView) findViewById(R.id.showuser_fullname_value);
        TextView textViewGender = (TextView) findViewById(R.id.showuser_gender_value);
        TextView textViewAge = (TextView) findViewById(R.id.showuser_gender_value);
        TextView textViewHeight = (TextView) findViewById(R.id.showuser_height_value);
        TextView textViewWeight = (TextView) findViewById(R.id.showuser_weight_value);
        TextView textViewWaistline = (TextView) findViewById(R.id.showuser_waistline_value);
        TextView textViewPressure = (TextView) findViewById(R.id.showuser_pressure_value);
        TextView textViewBloodsugar = (TextView) findViewById(R.id.showuser_bloodsugar_value);
        TextView textViewCholesterol = (TextView) findViewById(R.id.showuser_cholesterol_value);
        History history = Parcels.unwrap(getIntent().getParcelableExtra("history"));
        User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));



         textViewFullname.setText(user.getUserName()+" "+user.getUserSurname());
        textViewGender.setText(user.getUserName()+" "+user.getUserSurname());
        textViewAge.setText(user.getUserName()+" "+user.getUserSurname());
        textViewHeight.setText(history.getHistHeight());
        textViewWeight.setText(history.getHistWeight());
        textViewWaistline.setText(history.getHistWaistline());
        textViewPressure.setText(history.getHistBloodPressure());
        textViewBloodsugar.setText(history.getHistBloodSugar());
        textViewCholesterol.setText(history.getHistCholesterol());


    }
}