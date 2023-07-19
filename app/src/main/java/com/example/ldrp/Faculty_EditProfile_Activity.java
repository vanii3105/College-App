package com.example.ldrp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;

public class Faculty_EditProfile_Activity extends AppCompatActivity{
    private long pressed;
        ImageView AccountSettingBackButton, changePhoto, editname, editemail, desig, quali, depart,phone;
        CircularImageView profile;
        EditText editname1, editemail1, editdesig,editquali, editdepart,editphone;
        Button save;
        String age,name,email;

        @SuppressLint({"MissingInflatedId", "WrongViewCast"})
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_faculty_edit_profile);

            AccountSettingBackButton=findViewById(R.id.AccountSettingBackButton);

            profile=findViewById(R.id.profile);
            changePhoto=findViewById(R.id.changePhoto);

            editname =findViewById(R.id.editname);
            editname1=findViewById(R.id.editname1);


            editemail=findViewById(R.id.editemail1);
            editemail1=findViewById(R.id.editemail);

            desig=findViewById(R.id.desig);
            editdesig=findViewById(R.id.editdesig);

            quali=findViewById(R.id.quali);
            editquali=findViewById(R.id.editquali);

            depart=findViewById(R.id.depart);
            editdepart=findViewById(R.id.editdepart);

            phone=findViewById(R.id.phone);
            editphone=findViewById(R.id.editphone);

            save=findViewById(R.id.save);

            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


            FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.child("name").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();
                    String designation = snapshot.child("designation").getValue().toString();
                    String qualification = snapshot.child("qualification").getValue().toString();
                    String department = snapshot.child("department").getValue().toString();
                    String phone = snapshot.child("contact").getValue().toString();
                    String url = snapshot.child("purl").getValue().toString();

                    editname1.setText(name);
                    editemail1.setText(email);
                    editdesig.setText(designation);
                    editquali.setText(qualification);
                    editdepart.setText(department);
//                EditAccSettingPassword.setText(email);
                    editphone.setText(phone);
                    Glide.with(Faculty_EditProfile_Activity.this).load(url).into(profile);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            editname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder mydialog = new AlertDialog.Builder(Faculty_EditProfile_Activity.this);
                    mydialog.setTitle("New Name?");

                    final EditText nameInput = new EditText(Faculty_EditProfile_Activity.this);
                    nameInput.setInputType(InputType.TYPE_CLASS_TEXT);
                    mydialog.setView(nameInput);

                    mydialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            name = nameInput.getText().toString();
                            HashMap<String,Object> m = new HashMap<String,Object>();
                            m.put("name",name);
                            FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).updateChildren(m);
                        }
                    });
                    mydialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    mydialog.show();
                }
            });

            editemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder mydialog = new AlertDialog.Builder(Faculty_EditProfile_Activity.this);
                    mydialog.setTitle("New Email Address?");

                    final EditText emailInput = new EditText(Faculty_EditProfile_Activity.this);
                    emailInput.setInputType(InputType.TYPE_CLASS_TEXT);
                    mydialog.setView(emailInput);

                    mydialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            email = emailInput.getText().toString();
                            HashMap<String,Object> m = new HashMap<String,Object>();
                            m.put("email",email);
                            FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).updateChildren(m);
                        }
                    });
                    mydialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    mydialog.show();
                }
            });

//        editpass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditProfileName.setFocusableInTouchMode(true);
//            }
//        });

            desig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder mydialog = new AlertDialog.Builder(Faculty_EditProfile_Activity.this);
                    mydialog.setTitle("New Age?");
                    final EditText desigip = new EditText(Faculty_EditProfile_Activity.this);
                    desigip.setInputType(InputType.TYPE_CLASS_TEXT);
                    mydialog.setView(desigip);
                    mydialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            HashMap<String,Object> m = new HashMap<String,Object>();
                            m.put("designation",desigip.getText().toString());
                            FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).updateChildren(m);
                        }
                    });
                    mydialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    mydialog.show();
                }
            });
            quali.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder mydialog = new AlertDialog.Builder(Faculty_EditProfile_Activity.this);
                    mydialog.setTitle("New Phone Number?");

                    final EditText qualiip = new EditText(Faculty_EditProfile_Activity.this);
                    qualiip.setInputType(InputType.TYPE_CLASS_TEXT);
                    mydialog.setView(qualiip);

                    mydialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //phone = Editphone.getText().toString();
                            HashMap<String,Object> m = new HashMap<String,Object>();
                            m.put("qualification",qualiip.getText().toString());
                            FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).updateChildren(m);
                        }
                    });
                    mydialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    mydialog.show();
                }
            });
            depart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder mydialog = new AlertDialog.Builder(Faculty_EditProfile_Activity.this);
                    mydialog.setTitle("New Phone Number?");

                    final EditText departip = new EditText(Faculty_EditProfile_Activity.this);
                    departip.setInputType(InputType.TYPE_CLASS_TEXT);
                    mydialog.setView(departip);

                    mydialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //phone = Editphone.getText().toString();
                            HashMap<String,Object> m = new HashMap<String,Object>();
                            m.put("department",departip.getText().toString());
                            FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).updateChildren(m);
                        }
                    });
                    mydialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    mydialog.show();
                }
            });
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder mydialog = new AlertDialog.Builder(Faculty_EditProfile_Activity.this);
                    mydialog.setTitle("New Phone Number?");

                    final EditText Editphone = new EditText(Faculty_EditProfile_Activity.this);
                    Editphone.setInputType(InputType.TYPE_CLASS_PHONE);
                    mydialog.setView(Editphone);

                    mydialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //phone = Editphone.getText().toString();
                            HashMap<String,Object> m = new HashMap<String,Object>();
                            m.put("contact",Editphone);
                            FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).updateChildren(m);
                        }
                    });
                    mydialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    mydialog.show();
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Faculty_EditProfile_Activity.this,Faculty_Profile_Activity.class));
                }
            });

            AccountSettingBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Faculty_EditProfile_Activity.this,Faculty_Profile_Activity.class));
                }
            });

        }

        @Override
        public void onBackPressed(){
            if(pressed + 2000> System.currentTimeMillis()){
                super.onBackPressed();
                Intent i = new Intent(Faculty_EditProfile_Activity.this, Faculty_Profile_Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(Faculty_EditProfile_Activity.this, "Press Back again to go Back!", Toast.LENGTH_SHORT).show();
            }
            pressed = System.currentTimeMillis();
        }

}

//    editname.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            toggleEditableofEditText(EditProfileName);
////                EditProfileName.setFocusableInTouchMode(true);
////                EditProfileName.setSelection(EditProfileName.getText().length());
//        }
//    });
//    private void toggleEditableofEditText(EditText editProfileName) {
//        if(editProfileName.getKeyListener() != null){
//            editProfileName.setTag(editProfileName.getKeyListener());
//            editProfileName.setKeyListener(null);
//        }
//        else{
//            editProfileName.setKeyListener((KeyListener) editProfileName.getTag());
//            editProfileName.setFocusableInTouchMode(true);
//            editProfileName.setSelection(editProfileName.getText().length());
//        }
//    }
