package com.example.ldrp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Faculty_Profile_Activity extends AppCompatActivity {
TextView editp,uploaddoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);
        editp=findViewById(R.id.editp);
        uploaddoc=findViewById(R.id.uploaddoc);
        editp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Faculty_Profile_Activity.this,Faculty_EditProfile_Activity.class));
            }
        });
        uploaddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Faculty_Profile_Activity.this,Faculty_Upload_Document.class));
            }
        });
    }
}