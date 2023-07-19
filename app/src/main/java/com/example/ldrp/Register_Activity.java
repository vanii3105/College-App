package com.example.ldrp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Register_Activity extends AppCompatActivity {

    FirebaseAuth auth;
    ImageView d_back_button;
    Button rnextBtn;
    TextInputEditText remail,rpwd,crpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        remail=findViewById(R.id.remail);
        rpwd= findViewById(R.id.rpwd);
        crpwd= findViewById(R.id.crpwd);
        d_back_button=findViewById(R.id.d_back_button);

        rnextBtn=(Button)findViewById(R.id.rnextBtn);
        d_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register_Activity.this,Login_Activity.class));
            }
        });
        rnextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = remail.getText().toString();
                String Password = rpwd.getText().toString();
                if(TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password))
                {
                    Toast.makeText(Register_Activity.this , "Enter Values!!" , Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Register_Activity.this,"HELLLLLL",Toast.LENGTH_SHORT).show();
                    regist(Email,Password);
                }
            }

            private void regist(String Email, String Password) {
                auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(Register_Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register_Activity.this , "Registered Successfully!!" , Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Register_Activity.this,Decision_Activity.class);
//                    intent.putExtra(Email.toString(),Email);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Register_Activity.this , "Registration Failed!!" , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}