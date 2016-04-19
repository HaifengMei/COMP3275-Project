package com.project.uwi.enroll;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;

public class StudentList extends AppCompatActivity {

    ArrayList<String> students = new ArrayList<>();
    Firebase mRootRef;
    ListView mListView;
    String courseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRootRef = new Firebase("https://enroll.firebaseio.com");
        mListView = (ListView) findViewById(R.id.listStudents);

        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("courseCode")){
            courseCode = bundle.getString("courseCode");
        }

        Firebase course = mRootRef.child(courseCode);
        FirebaseListAdapter<String> adapter =
                new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1, course) {
                    @Override
                    protected void populateView(View view, String s, int i) {
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setText(s);
                    }
                };
        mListView.setAdapter(adapter);

    }

}
