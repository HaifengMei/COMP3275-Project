package com.project.uwi.enroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {

    Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Firebase.setAndroidContext(this);
//        mRootRef = new Firebase("https://enroll.firebaseio.com");
//
//        long date = System.currentTimeMillis();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = sdf.format(date);
//        Firebase time = mRootRef.child("TIME");
//        time.setValue(dateString);
//
//        Toast toast = Toast.makeText(getApplicationContext(),
//                dateString, Toast.LENGTH_SHORT);
//        toast.show();


    }

    public void onClickScan(View v) {
        Intent i = new Intent(getBaseContext(), CourseList.class);
        Bundle bundle = new Bundle();
        bundle.putString("Function", "Scan");
        i.putExtras(bundle);
        startActivity(i);
    }

    public void onClickView(View v) {
        Intent i = new Intent(getBaseContext(), CourseList.class);
        Bundle bundle = new Bundle();
        bundle.putString("Function", "View");
        i.putExtras(bundle);
        startActivity(i);
    }

    public void start(Class c) {
        Intent i = new Intent(this, c);
        startActivity(i);
    }

}
