package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }
}
