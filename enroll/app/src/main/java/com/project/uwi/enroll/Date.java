package com.project.uwi.enroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Date extends AppCompatActivity {
    Firebase mRootRef;
    ListView mListView;
    String courseCode, Func;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("courseCode")) {
            courseCode = bundle.getString("courseCode");
        }
        if (bundle.containsKey("Function")) {
            Func = bundle.getString("Function");
        }


        mRootRef = new Firebase("https://enroll.firebaseio.com");
        mListView = (ListView) findViewById(R.id.listStudents);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Date.this, StudentList.class);
                Bundle bundle = new Bundle();
                bundle.putString("Date", parent.getItemAtPosition(position).toString());
                bundle.putString("courseCode", courseCode);
                i.putExtras(bundle);
                startActivity(i);
            }
        });


        Firebase dates = mRootRef.child(courseCode + "date");
        Query query = dates.orderByValue();
        if(dates.getKey() == null){
            Intent i = i = new Intent(Date.this, Scan.class);
            bundle = new Bundle();
            bundle.putString("courseCode", courseCode);
            i.putExtras(bundle);
            startActivity(i);
        }
        FirebaseListAdapter<String> adapter =
                new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, dates) {
                    @Override
                    protected void populateView(View view, String s, int i) {
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setText(s);
                    }
                };
        mListView.setAdapter(adapter);

    }


}