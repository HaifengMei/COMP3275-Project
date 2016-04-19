package com.project.uwi.enroll;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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

    }

    protected void onStart() {
        super.onStart();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);
        mListView.setAdapter(adapter);


        Firebase course = mRootRef.child(courseCode);
        course.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String student = dataSnapshot.getValue(String.class);
                students.add(student);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
