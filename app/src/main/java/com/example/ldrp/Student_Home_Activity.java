package com.example.ldrp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Student_Home_Activity extends AppCompatActivity {

    LinearLayout Uploads,Archive,Account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        Uploads=findViewById(R.id.Uploads);
        Archive=findViewById(R.id.Archive);
        Account=findViewById(R.id.Account);

        Uploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Home_Activity.this,Syllabus_List_Activity.class));
            }
        });
        Archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Home_Activity.this,Faculty_List_Activity.class));
            }
        });
        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Home_Activity.this,Student_Home_Activity.class));
            }
        });



    }


}