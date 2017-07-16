package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.data.ets.User;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class BirthdayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);



      final Calendar myCalendar = Calendar.getInstance();

       final EditText edittextBirthDay = (EditText) findViewById(R.id.birthday_press_birthday);
       final EditText edittextAge = (EditText) findViewById(R.id.birthday_cal_birthday);
       final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(YEAR, year);
                myCalendar.set(MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(edittextBirthDay,myCalendar);
            }

        };

        edittextBirthDay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(BirthdayActivity.this, date, myCalendar
                        .get(YEAR), myCalendar.get(MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //START
        Button buttonBack = (Button) findViewById(R.id.birthday_previous);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();
            }
        });
        //END

        //START
        Button buttonNext = (Button) findViewById(R.id.birthday_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                Intent intent = getIntent();
                User user = Parcels.unwrap(intent.getParcelableExtra("user"));
                user.setUserBirthday(edittextBirthDay.getText().toString());
                Intent i = new Intent(v.getContext(), HeightActivity.class);
                startActivity(i);

            }
        });
        //END
    }


    private void updateLabel(EditText edittext,Calendar myCalendar) {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        edittext.setText(sdf.format(myCalendar.getTime()));
        EditText edittextAge = (EditText) findViewById(R.id.birthday_cal_birthday);
        edittextAge.setText(String.valueOf(getDiffYears(myCalendar,Calendar.getInstance())) );
    }




    public static int getDiffYears(Calendar first, Calendar last) {
        Calendar a = first;
        Calendar b = last;
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }
}
