package com.example.ldrp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Faculty_Home_Activity extends AppCompatActivity {
    LinearLayout Uploads,Archive,Account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);
        Uploads=findViewById(R.id.Uploads);
        Archive=findViewById(R.id.Archive);
        Account=findViewById(R.id.Account);

        Uploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Faculty_Home_Activity.this,Syllabus_List_Activity.class));
            }
        });
        Archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Faculty_Home_Activity.this,Faculty_List_Activity.class));
            }
        });
        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Faculty_Home_Activity.this,Faculty_Profile_Activity.class));
            }
        });

    }
}