package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class UpdateDialog extends AppCompatDialogFragment {
    private EditText task;
    private DatePicker datePicker;
    private TextView date;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Spinner spinner1,spinner2;
    private Button save;
    String taskName,time,option,priority;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.update_layout,null);
        builder.setView(view).setTitle("Update").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        task=view.findViewById(R.id.task);
        date=view.findViewById(R.id.date);
        save=view.findViewById(R.id.save);






        spinner1=view.findViewById(R.id.spinnerList);
        spinner2=view.findViewById(R.id.spinnerPriority);



        return builder.create();
    }
}
