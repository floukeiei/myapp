package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.data.ets.History;
import com.data.ets.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.utils.EtsUtils;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

public class NameActivity extends AppCompatActivity {

    private static History history;
    private static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        getSupportActionBar().setTitle(getResources().getString(R.string.heading_title_profile));
        Bundle bundle = getIntent().getExtras();
        String page = "";
        if (bundle != null) {
            page = bundle.getString("fromPage");
        }

       final EditText textFname = (EditText) findViewById(R.id.name_press_name);
       final EditText textLname = (EditText) findViewById(R.id.name_press_surname);
       final RadioGroup usrGender = (RadioGroup) findViewById(R.id.name_gender);

        int renderBtn = View.VISIBLE;
        int renderSaveBtn = View.INVISIBLE;
        if("Menu".equals(page)){
            renderBtn = View.INVISIBLE;
            renderSaveBtn = View.VISIBLE;
            history =  EtsUtils.getSavedObjectFromPreference(getApplicationContext(),"history", History.class);
            user = EtsUtils.getSavedObjectFromPreference(getApplicationContext(),"user", User.class);
            textFname.setText(user.getUserName());
            textLname.setText(user.getUserSurname());
//            if("M".equals(user.getUserGender())) {
//                usrGender.check(R.id.name_press_male);
//            }else{
//                usrGender.check(R.id.name_press_female);
//            }
            usrGender.setVisibility(renderBtn);
        }
        usrGender.setVisibility(renderBtn);
        Button buttonSave = (Button) findViewById(R.id.name_save);
        buttonSave.setVisibility(renderSaveBtn);
        buttonSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int selectedId = usrGender.getCheckedRadioButtonId();
                if(R.id.name_press_male == selectedId){
                    user.setUserGender("M");
                }else{
                    user.setUserGender("F");
                }

                user.setUserName(textFname.getText().toString());
                user.setUserSurname(textLname.getText().toString());
                EtsUtils.saveObjectToSharedPreference(getApplicationContext(), "user", user);
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mRootRef = database.getReference();
                DatabaseReference mUsersRef = mRootRef.child("user/"+user.getUserCode());
                mUsersRef.child("userName").setValue(user.getUserName());
                mUsersRef.child("userSurname").setValue(user.getUserSurname());

              //  mUsersRef.child("userGender").setValue(user.getUserGender());
                finish();
            }
        });


        //START
        Button buttonBack = (Button) findViewById(R.id.name_previous);
        buttonBack.setVisibility(renderBtn);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();
            }
        });
        //END

        //START
        Button buttonNext = (Button) findViewById(R.id.name_next);
        buttonNext.setVisibility(renderBtn);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                User user = new User();
                EditText userName = (EditText) findViewById(R.id.name_press_name);
                EditText userSurName = (EditText) findViewById(R.id.name_press_surname);
                RadioGroup userGender = (RadioGroup) findViewById(R.id.name_gender);

                int selectedId = userGender.getCheckedRadioButtonId();

              if(R.id.name_press_male == selectedId){
                  user.setUserGender("M");
              }else{
                  user.setUserGender("F");
              }

                if(StringUtils.isEmpty(userName.getText().toString())){
                    userName.setError( "name is required" );
                }

                if(StringUtils.isEmpty(userSurName.getText().toString())){
                    userSurName.setError( "surname is required" );
                }
                if(StringUtils.isNotEmpty(userName.getText().toString()) && StringUtils.isNotEmpty(userSurName.getText().toString())){
                    user.setUserName(userName.getText().toString());
                    user.setUserSurname(userSurName.getText().toString());


                    FirebaseUser userLogin = FirebaseAuth.getInstance().getCurrentUser();
                    user.setUserEmail(userLogin.getEmail());
                    Intent i = new Intent(v.getContext(), BirthdayActivity.class);
                    i.putExtra("user", Parcels.wrap(user));
                    startActivity(i);
                }


            }
        });
        //END

    }

}
