package com.example.ldrp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {

    ImageView login_back_button;
    Button register,loginBtn;
    TextInputEditText lemail,lpwd;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lemail=findViewById(R.id.email);
        lpwd=findViewById(R.id.pwd);
        loginBtn=findViewById(R.id.loginBtn);
        login_back_button= findViewById(R.id.login_back_button);
        register=findViewById(R.id.registerBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this, Register_Activity.class));

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = lemail.getText().toString();
                final String password = lpwd.getText().toString();
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),"Please enter email!!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            String uid = task.getResult().getUser().getUid();
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                            firebaseDatabase.getReference().child("users").child(uid).child("role").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String role = snapshot.getValue(String.class);
                                    if(role.equals("students")){
                                        Intent intent = new Intent(Login_Activity.this, Student_Home_Activity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(),"Login successful!!", Toast.LENGTH_SHORT).show();
                                    }
                                    if(role.equals("faculties")){
                                        Intent intent = new Intent(Login_Activity.this, Faculty_Home_Activity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(),"Login successful!!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(Login_Activity.this, "sign up error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

    }
}