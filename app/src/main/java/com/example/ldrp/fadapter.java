package com.example.ldrp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class fadapter extends FirebaseRecyclerAdapter<fmodel, fadapter.myviewholder> {

        Context context;
public fadapter(@NonNull FirebaseRecyclerOptions<fmodel> options, Context context) {
        super(options);
        this.context=context;
        }

@Override
protected void onBindViewHolder(@NonNull fadapter.myviewholder holder, int position, @NonNull fmodel model) {

        holder.name.setText(model.getName());
        holder.department.setText(model.getDepartment());
        holder.designation.setText(model.getDesignation());
        holder.qualification.setText(model.getQualification());
        holder.experience.setText(model.getExperience());
        holder.email.setText(model.getEmail());
        Glide.with(holder.purl.getContext()).load(model.getPurl()).into(holder.purl);

        }

@NonNull
@Override
public fadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fsingle_row,parent,false);
        return new myviewholder(view);
        }

class myviewholder extends RecyclerView.ViewHolder{
    TextView name,department,designation,qualification,experience,email;
    ImageView purl;
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.t1);
        department = itemView.findViewById(R.id.t2);
        designation = itemView.findViewById(R.id.t3);
        qualification = itemView.findViewById(R.id.t4);
        experience = itemView.findViewById(R.id.t5);
        email = itemView.findViewById(R.id.t6);
        purl = itemView.findViewById(R.id.i);
    }
}
}
