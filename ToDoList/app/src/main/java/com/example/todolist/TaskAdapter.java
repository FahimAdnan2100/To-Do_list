package com.example.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class TaskAdapter extends FirebaseRecyclerAdapter<Task,TaskAdapter.TaskViewHolder> {

    public TaskAdapter(@NonNull FirebaseRecyclerOptions<Task> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskViewHolder holder, int position, @NonNull Task model) {

        holder.taskNames.setText(model.getName());
        holder.taskDates.setText(model.getDate());
        holder.taskTime.setText(model.getTime());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=holder.taskNames.getText().toString();
                DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("TasksName").child(name);
                db.removeValue();


            }
        });
        DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("TasksName").child("LowPriority");
        if(db.toString().trim()=="LowPriority"){
            Log.d("priority",db.toString().trim());
           holder.linearLayout.setBackgroundColor(Color.BLUE);
        }

        //holder.option.setText(model.getOption());
        //holder.priority.setText(model.getPriority());
    }




    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview,parent,false);

        return new TaskViewHolder(view);
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskNames,taskDates,taskTime,priority;
        ImageView delete,edit;
        LinearLayout linearLayout;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskNames=itemView.findViewById(R.id.taskNames);
            taskDates=itemView.findViewById(R.id.taskDates);
            taskTime=itemView.findViewById(R.id.taskTimes);
            delete=itemView.findViewById(R.id.delete);
            edit=itemView.findViewById(R.id.edit);
            linearLayout=itemView.findViewById(R.id.linearLayout);

            //option=itemView.findViewById(R.id.option);
            //priority=itemView.findViewById(R.id.priority);

        }
    }
}
