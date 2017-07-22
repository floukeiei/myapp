package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.data.ets.History;
import com.data.ets.User;

import org.parceler.Parcels;

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
                Intent intent = getIntent();
                History history = Parcels.unwrap(intent.getParcelableExtra("history"));
                EditText edittextPressure = (EditText) findViewById(R.id.blood_press_pressure);
                EditText edittextBloodsugar = (EditText) findViewById(R.id.blood_press_bloodsugar);
                EditText edittextCholesterol = (EditText) findViewById(R.id.blood_press_cholesterol);
                history.setHistHeight(edittextPressure.getText().toString());
                history.setHistHeight(edittextBloodsugar.getText().toString());
                history.setHistHeight(edittextCholesterol.getText().toString());
                Intent i = new Intent(v.getContext(), ShowUserActivity.class);
                i.putExtra("history", Parcels.wrap(history));
                User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
                i.putExtra("user", Parcels.wrap(user));
                startActivity(i);

            }
        });
        //END
    }
}