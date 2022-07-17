package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddActivity extends AppCompatActivity {

    private EditText task;

    private TextView date,timePick;
    private int mYear, mMonth, mDay;
    private Spinner spinner2;
    private Button save;
    String taskName,time,option,priority,stime;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        task=findViewById(R.id.task);
        date=findViewById(R.id.date);
        timePick=findViewById(R.id.time);
        save=findViewById(R.id.save);


        timePick.setOnClickListener(new View.OnClickListener() {
            String am_pm;
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minutes=cldr.get(Calendar.MINUTE);
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(AddActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay>12){
                            hourOfDay=hourOfDay-12;
                            am_pm="AM";
                        }else{
                            am_pm="PM";
                        }
                        timePick.setText(hourOfDay+":"+minute+" "+am_pm);
                    }
                },hour,minutes,true);

                timePickerDialog.show();
            }
        });





        spinner2=findViewById(R.id.spinnerPriority);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.priority, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog= new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"-"+month+"-"+year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskName=task.getText().toString();
                time=date.getText().toString();
                stime=timePick.getText().toString();

                priority=spinner2.getSelectedItem().toString().trim();
                if(taskName.isEmpty()){
                    task.setError("Enter task");
                }else{
                    Task obj=new Task(taskName,stime,time,priority);
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    DatabaseReference node =db.getReference("TasksName");
                    node.child(taskName).setValue(obj);
                    Toast.makeText(AddActivity.this, "Task added successfully.", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(AddActivity.this,MainActivity.class);
                    intent.putExtra("priority",priority);

                    startActivity(intent);
                }



            }

        });

    }


}
