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
        Intent i = new Intent(getBaseContext(), CourseList.class);
        Bundle bundle = new Bundle();
        bundle.putString("Function","Scan");
        i.putExtras(bundle);
        startActivity(i);
    }

    public void onClickView(View v){
        Intent i = new Intent(getBaseContext(), CourseList.class);
        Bundle bundle = new Bundle();
        bundle.putString("Function","View");
        i.putExtras(bundle);
        startActivity(i);
    }

    public void start(Class c){
        Intent i = new Intent(this,c);
        startActivity(i);
    }

}
