package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    TaskAdapter taskAdapter;
    String task,date;
    String priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        /*Bundle bundle = getIntent().getExtras();
        priority=bundle.getString("priority");*/
        DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("TasksName").child("LowPriority");
        if(db.toString()=="Lowpriority"){
            LinearLayout linearLayout=findViewById(R.id.linearLayout);
            linearLayout.setBackgroundColor(Color.BLUE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Task> options=
                new FirebaseRecyclerOptions.Builder<Task>().
                        setQuery(FirebaseDatabase.getInstance().getReference().child("TasksName"),Task.class).build();
        taskAdapter = new TaskAdapter(options);
        recyclerView.setAdapter(taskAdapter);



    }
    @Override protected void onStart()
    {
        super.onStart();
        taskAdapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        taskAdapter.stopListening();
    }
}