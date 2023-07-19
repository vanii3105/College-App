package com.example.ldrp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Decision_Activity extends AppCompatActivity {

    ImageView back_btn;
    Button rider;
    Button driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);
        rider=findViewById(R.id.riderbtn);
        driver=findViewById(R.id.driverbtn);
        back_btn=findViewById(R.id.back_button);

        rider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Decision_Activity.this,Student_Register_Activity.class));
            }
        });
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Decision_Activity.this, Faculty_Register_Activity.class));
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Decision_Activity.this,Register_Activity.class));
            }
        });

    }
}