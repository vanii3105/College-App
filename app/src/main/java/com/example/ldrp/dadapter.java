package com.example.ldrp;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class dadapter extends FirebaseRecyclerAdapter<putPDF,dadapter.myviewholder> {

    public dadapter(@NonNull FirebaseRecyclerOptions<putPDF> options) {
        super(options);
//        options.getSnapshots()
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull putPDF model) {


        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        holder.topic.setText(String.valueOf(model.getTopic()));
        holder.grade.setText(String.valueOf(model.getGrade()));
        holder.subject.setText(String.valueOf(model.getSubject()));

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(holder.img1.getContext(),viewpdf.class);
                intent.putExtra("name",model.getName());
                intent.putExtra("url",model.getUrl());
//                intent.putExtra("context",(Serializable) holder.img1.getContext());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.img1.getContext().startActivity(intent);

            }
        });

        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(currentuser).child("uploads").child(getRef(holder.getAdapterPosition()).getKey());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        ImageView img1,save;
        TextView subject,topic,grade;

        public myviewholder(@NonNull View itemView){
            super(itemView);
            save=itemView.findViewById(R.id.save);
            img1=itemView.findViewById(R.id.img1);
            subject=itemView.findViewById(R.id.subject);
            topic=itemView.findViewById(R.id.topic);
            grade=itemView.findViewById(R.id.grade);
        }
    }
}
