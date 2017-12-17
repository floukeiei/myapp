package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class TotoloActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totolo);



        gifImageView = (GifImageView)findViewById(R.id.gifView);

        try{
            InputStream inputStream = getAssets().open("totolo.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException ex)
        {

        }
        //Wait for 3 seconds and start Activity Main
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TotoloActivity.this.startActivity(new Intent(TotoloActivity.this,MainActivity.class));
                TotoloActivity.this.finish();
            }
        },3000); // 3000 = 3seconds


    }

}
