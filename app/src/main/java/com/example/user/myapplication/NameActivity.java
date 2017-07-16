package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.data.ets.User;

import org.parceler.Parcels;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //START
        Button buttonBack = (Button) findViewById(R.id.name_previous);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();
            }
        });
        //END

        //START
        Button buttonNext = (Button) findViewById(R.id.name_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                User user = new User();
                EditText userName = (EditText) findViewById(R.id.edit_name);
                EditText userSurName = (EditText) findViewById(R.id.edit_name);
                user.setUserName(userName.getText().toString());
                user.setUserSurname(userSurName.getText().toString());
              //  user.setUserCode();
              Intent i = new Intent(v.getContext(), BirthdayActivity.class);
                i.putExtra("user", Parcels.wrap(user));
                startActivity(i);

            }
        });
        //END

    }

}
