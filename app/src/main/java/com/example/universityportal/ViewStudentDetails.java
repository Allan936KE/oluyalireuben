package com.example.universityportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universityportal.databinding.ActivityUnitsDetailsBinding;
import com.example.universityportal.databinding.ActivityViewStudentDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewStudentDetails extends AppCompatActivity {
    ActivityViewStudentDetailsBinding binding;
    String name,code,lecturer,stage, student, regno;
    TextView unitname, unitcode, lecturername, unitstage,rstudent, rregno;
    EditText grade;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityViewStudentDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        unitname = findViewById(R.id.tvUnitName);
        unitcode = findViewById(R.id.tvUnitCode);
        lecturername = findViewById(R.id.tvLecturerName);
        unitstage = findViewById(R.id.tvStage);
        rstudent = findViewById(R.id.tvStudentName);
        rregno = findViewById(R.id.tvRegNo);
        grade = findViewById(R.id.etGrade);
        btn = findViewById(R.id.buttonAward);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGrade(unitname,unitcode,lecturername,unitstage,rstudent,rregno, grade);
                Toast.makeText(ViewStudentDetails.this, "You have successfully awarded Grade", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ViewStudentDetails.this, AddedUnits.class));
            }
        });

        name=getIntent().getStringExtra("unitName");
        code=getIntent().getStringExtra("unitCode");
        lecturer=getIntent().getStringExtra("lecturerName");
        stage=getIntent().getStringExtra("stage");
        student=getIntent().getStringExtra("studentName");
        regno=getIntent().getStringExtra("regNo");

        binding.tvUnitName.setText(name);
        binding.tvUnitCode.setText(code);
        binding.tvLecturerName.setText(lecturer);
        binding.tvStage.setText(stage);
        binding.tvStudentName.setText(student);
        binding.tvRegNo.setText(regno);

    }
    private void addGrade(TextView unitname, TextView unitcode, TextView lecturername, TextView unitstage, TextView student, TextView regNo, EditText grade) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Grade grade1 = new Grade(unitname.getText().toString(), unitcode.getText().toString(),
                lecturername.getText().toString(), unitstage.getText().toString(), student.getText().toString(), regNo.getText().toString(),
                grade.getText().toString());

        String key = ref.push().getKey();

        ref.child("Grade").child(key).setValue(grade1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ViewStudentDetails.this, "Insertion Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}