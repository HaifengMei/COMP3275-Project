package com.project.uwi.enroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickScan(View v){
        start(Scan.class);
    }

    public void onClickView(View v){
        start(Display.class);
    }

    public void start(Class c){
        Intent i = new Intent(this,c);
        startActivity(i);
    }

}
