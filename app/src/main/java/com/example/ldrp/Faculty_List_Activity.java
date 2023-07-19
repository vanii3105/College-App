package com.example.ldrp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Faculty_List_Activity extends AppCompatActivity {
    ImageView archiveBackbtn;
RecyclerView recview;
    fadapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_list);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        archiveBackbtn = findViewById(R.id.archiveBackbtn);
        recview=findViewById(R.id.recview);
        archiveBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseDatabase.getReference().child("users").child(currentuser).child("role").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String role = snapshot.getValue(String.class);
                        if(role.equals("students")){
                            Intent intent = new Intent(Faculty_List_Activity.this, Student_Home_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(role.equals("faculties")){
                            Intent intent = new Intent(Faculty_List_Activity.this, Faculty_Home_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<fmodel> options =
                new FirebaseRecyclerOptions.Builder<fmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("faculties"), fmodel.class).build();
        adapter = new fadapter(options, getApplicationContext());
        recview.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}