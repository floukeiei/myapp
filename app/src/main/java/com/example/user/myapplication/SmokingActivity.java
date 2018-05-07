package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.data.ets.History;
import com.data.ets.User;

import org.parceler.Parcels;

public class SmokingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoking);

        getSupportActionBar().setTitle(R.string.heading_title_profile);

        //START
        Button buttonBack = (Button) findViewById(R.id.smoking_previous);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();
            }
        });
        //END

        //START
        Button buttonNext = (Button) findViewById(R.id.smoking_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = getIntent();
                History history = Parcels.unwrap(intent.getParcelableExtra("history"));
                Switch switchSmooking = (Switch)findViewById(R.id.smoking_switch_smoking);
                Switch switchDiabetes = (Switch)findViewById(R.id.smoking_switch_diabetes);
                history.setHistSmoking(switchSmooking.isChecked()?"Y":"N");
                history.setHistDiabetes(switchDiabetes.isChecked()?"Y":"N");
                Intent i = new Intent(v.getContext(), BloodActivity.class);
                i.putExtra("history", Parcels.wrap(history));
                User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
                i.putExtra("user", Parcels.wrap(user));
                startActivity(i);

            }
        });
        //END
    }
}
