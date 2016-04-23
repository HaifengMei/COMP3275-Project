package com.project.uwi.enroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.project.uwi.enroll.com.google.zxing.integration.android.IntentIntegrator;
import com.project.uwi.enroll.com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Scan extends AppCompatActivity implements View.OnClickListener {

    private ImageView scanBtn;
    private TextView txt_courseCode, StudentID;
    private String courseCode = "Code";
    private Map<String, Integer> map = new HashMap<>();
    ImageView img_present;
    Firebase mRootRef, mdate;
    String Date, DATE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("courseCode")) {
            courseCode = bundle.getString("courseCode");
            mRootRef = new Firebase("https://enroll.firebaseio.com/");
        }
        if (bundle.containsKey("Date")) {
            Date = bundle.getString("Date");
        }


        mdate = mRootRef.child("TIME").child(courseCode);

        mdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DATE = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        setUpUi();
    }

    public void setUpUi() {
        scanBtn = (ImageView) findViewById(R.id.scan_button);
        map.put("scan_success", R.drawable.present);
        img_present = (ImageView) findViewById(R.id.img_scan);
        StudentID = (TextView) findViewById(R.id.Student_ID);
        txt_courseCode = (TextView) findViewById(R.id.txt_courseCode);
        txt_courseCode.setText(courseCode);
        scanBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        //respond to clicks
        if (v.getId() == R.id.scan_button) {
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            StudentID.setText("ID: " + scanContent);
            img_present.setImageResource(map.get("scan_success"));

            long date = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(date);

            Firebase course = mRootRef.child(courseCode + "/" + dateString);
            Firebase temp = mRootRef.child(courseCode + "date");

            if (DATE == null){
                temp.push().setValue(dateString);
                mdate.setValue(dateString);
            }
            else if(!DATE.equalsIgnoreCase(dateString)) {
                temp.push().setValue(dateString);
                mdate.setValue(dateString);
            }


            course.push().setValue(scanContent);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
