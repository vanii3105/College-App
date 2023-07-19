package com.example.ldrp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class Student_Register_Activity extends AppCompatActivity {
    ImageView r2_back_button,redit,rprofile;
    Button rregisterbtn;
    TextInputEditText rname,raddress,rphonenumber;
    RadioButton rfemale,rmale,rother;
    private Uri filepath;
    Bitmap bitmap;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    String cname,cgender;
    RadioGroup radioGroup;
    Spinner myspinner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        radioGroup=findViewById(R.id.radiogroup);
        redit=findViewById(R.id.redit);
        rname=findViewById(R.id.rname);
        raddress=findViewById(R.id.raddress);
        rphonenumber=findViewById(R.id.rphonenumber);
        myspinner=(Spinner) findViewById(R.id.myspinner);
        r2_back_button=findViewById(R.id.r2_back_button);
        rregisterbtn=findViewById(R.id.rregisterbtn);
        rfemale=findViewById(R.id.rfemale);
        rmale=findViewById(R.id.rmale);
        rother=findViewById(R.id.rother);
        rprofile=findViewById(R.id.rprofile);

        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        spinnerDataList= new ArrayList<>();
        adapter= new ArrayAdapter<String>(Student_Register_Activity.this,android.R.layout.simple_spinner_dropdown_item,spinnerDataList);
        myspinner.setAdapter(adapter);
//        retrivedata();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                cgender= (String) radioButton.getText();
            }
        });
//        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Student_Register_Activity.this, myspinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
//                cname = myspinner.getSelectedItem().toString();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
        r2_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student_Register_Activity.this,Decision_Activity.class));
            }
        });

        redit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Student_Register_Activity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        spinnerDataList= new ArrayList<>();
        adapter= new ArrayAdapter<String>(Student_Register_Activity.this,android.R.layout.simple_spinner_dropdown_item,spinnerDataList);
        myspinner.setAdapter(adapter);
//        retrivedata();
//        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Student_Register_Activity.this, myspinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
//                cname = myspinner.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        rregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog p = new ProgressDialog(Student_Register_Activity.this);
                p.setTitle("Uploading...");
                p.show();
                if(filepath!=null)
                {
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference uploader = storage.getReference("Image1"+new Random().nextInt(50)).child("picture/" + UUID.randomUUID().toString());
                    uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                            uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    {
                                        p.dismiss();
                                        Toast.makeText(Student_Register_Activity.this, "Saved!!", Toast.LENGTH_SHORT).show();
                                        HashMap<String,Object> m = new HashMap<String,Object>();
                                        m.put("name",rname.getText().toString());
                                        m.put("location",raddress.getText().toString());
                                        m.put("gender",cgender);
//                                        m.put("company",cname);
                                        m.put("contact",rphonenumber.getText().toString());
                                        m.put("role","student");
                                        m.put("purl",uri.toString());
                                        m.put("userid",currentuser.toString());
                                        FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).setValue(m);
                                        FirebaseDatabase.getInstance().getReference().child("students").child(currentuser).setValue(m);
//                                        FirebaseDatabase.getInstance().getReference().child("companyusers").child(cname).child("riders").child(currentuser).updateChildren(m);
                                        startActivity(new Intent(Student_Register_Activity.this,Student_Home_Activity.class));
                                    }
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Student_Register_Activity.this, "ERROR!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            filepath=data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                rprofile.setImageBitmap(bitmap);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

//    private void retrivedata() {
//        listener = FirebaseDatabase.getInstance().getReference().child("Company").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot item:snapshot.getChildren())
//                {
//                    spinnerDataList.add(item.child("name").getValue().toString());
//                }
//                spinnerDataList.add("Other");
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}