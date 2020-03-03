package com.sunnyday.administrator.buryingpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("23333", "onCreate: "+this.getClass().getSimpleName());// 获得类名
        Log.i("23333", "onCreate: "+this.getClass().getCanonicalName());// 获得类名+包名
    }


}
