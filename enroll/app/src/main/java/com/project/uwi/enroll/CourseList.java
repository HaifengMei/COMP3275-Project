package com.project.uwi.enroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;

//Allows user to select which course they wish to interact with.
public class CourseList extends AppCompatActivity {
    Firebase mRootRef;
    ArrayList<String> courses = new ArrayList<>();
    ListView lv;
    String Func;
    int firstLoad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://enroll.firebaseio.com");

        lv = (ListView) findViewById(R.id.course_list);

        //Retrieves the course code from courselist
        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("Function")) {
            Func = bundle.getString("Function");
        }

        Firebase course = mRootRef.child("courses");
        FirebaseListAdapter<String> adapter =
                new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1, course) {
                    @Override
                    protected void populateView(View view, String s, int i) {
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setText(s);
                    }
                };
        lv.setAdapter(adapter);
       SelectCourse();

    }



    protected void SelectCourse() {// Pases the course code to the respective activity for use
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = null;
                if (Func.equals("Scan")) {
                    i = new Intent(CourseList.this, Scan.class);
                } else if (Func.equals("View")) {
                    i = new Intent(CourseList.this, StudentList.class);
                }
                Bundle bundle = new Bundle();
                bundle.putString("courseCode", parent.getItemAtPosition(position).toString());

                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }


}
