package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BloodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        //START
        Button buttonBack = (Button) findViewById(R.id.blood_previous);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();
            }
        });
        //END

        //START
        Button buttonNext = (Button) findViewById(R.id.blood_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ShowUserActivity.class);
                startActivity(i);

            }
        });
        //END
    }
}
