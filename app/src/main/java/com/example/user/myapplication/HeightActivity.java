package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);


        //START
        Button buttonBack = (Button) findViewById(R.id.height_previous);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();
            }
        });
        //END

        //START
        Button buttonNext = (Button) findViewById(R.id.height_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), SmokingActivity.class);
                startActivity(i);

            }
        });
        //END
    }

}
