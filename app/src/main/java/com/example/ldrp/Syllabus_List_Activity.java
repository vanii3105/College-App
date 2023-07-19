package com.example.ldrp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Syllabus_List_Activity extends AppCompatActivity {
Button opdf;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_list);
        opdf=findViewById(R.id.opdf);
        opdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Syllabus_List_Activity.this,Syllabus_Pdf_Activity.class));
            }
        });
    }
}