package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.data.ets.History;
import com.data.ets.User;
import com.utils.EtsUtils;

import org.parceler.Parcels;

import java.math.BigDecimal;

public class HeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);


        getSupportActionBar().setTitle(getResources().getString(R.string.heading_title_profile));

        //START
        Button buttonBack = (Button) findViewById(R.id.height_previous);
        EditText edittextHeight = (EditText) findViewById(R.id.height_press_height);
        String key = getIntent().getStringExtra("fromPage");
        edittextHeight.setEnabled(true);
        if("Menu".equalsIgnoreCase(key)) {
            buttonBack.setVisibility(View.INVISIBLE);

            History  history =  EtsUtils.getSavedObjectFromPreference(getApplicationContext(),"history", History.class);
            edittextHeight.setText(history.getHistHeight());
            edittextHeight.setEnabled(false);
        }

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
                EditText edittextHeight = (EditText) findViewById(R.id.height_press_height);
                EditText edittextWeight = (EditText) findViewById(R.id.height_press_weight);
                EditText edittextWaistline = (EditText) findViewById(R.id.height_press_waistline);
                History history = new History();
                history.setHistHeight(edittextHeight.getText().toString());
                history.setHistWeight(edittextWeight.getText().toString());
                //แปลงนิ้วเป็น เซ็นติเมตร  0.39370
                if(edittextWaistline.getText().toString() != null) {
                    BigDecimal waist = new BigDecimal(edittextWaistline.getText().toString());
                    BigDecimal waistCM = waist.divide(BigDecimal.valueOf(0.39370), BigDecimal.ROUND_UP);
                    history.setHistWaistline(waistCM.toString());
                }
                Intent i = new Intent(v.getContext(), SmokingActivity.class);
                User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
                i.putExtra("user", Parcels.wrap(user));
                i.putExtra("history", Parcels.wrap(history));

                startActivity(i);

            }
        });
        //END
    }

}
