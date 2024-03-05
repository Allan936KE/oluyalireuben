package com.example.universityportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.universityportal.databinding.ActivityUnitsDetailsBinding;
import com.example.universityportal.databinding.ActivityViewGradeBinding;

public class ViewGrade extends AppCompatActivity {
    ActivityViewGradeBinding binding;
    String name,code,lecturer,stage, studname, regno, grade;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grade);
        binding= ActivityViewGradeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btn = findViewById(R.id.buttonBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewGrade.this, ViewResults.class));
            }
        });

        name=getIntent().getStringExtra("unitName");
        code=getIntent().getStringExtra("unitCode");
        lecturer=getIntent().getStringExtra("lecturerName");
        stage=getIntent().getStringExtra("stage");
        studname=getIntent().getStringExtra("studentName");
        regno=getIntent().getStringExtra("regNo");
        grade=getIntent().getStringExtra("grade");

        binding.tvUnitName.setText(name);
        binding.tvUnitCode.setText(code);
        binding.tvLecturerName.setText(lecturer);
        binding.tvStage.setText(stage);
        binding.tvStudentName.setText(studname);
        binding.tvRegNo.setText(regno);
        binding.tvGrade.setText(grade);

    }
}