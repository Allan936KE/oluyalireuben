package com.example.universityportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universityportal.databinding.ActivityRegisterUnitBinding;
import com.example.universityportal.databinding.ActivityUnitsDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUnit extends AppCompatActivity {
    ActivityRegisterUnitBinding binding;
    String name,code,lecturer,stage;
    TextView unitname, unitcode, lecturername, unitstage;
    EditText student, regNo;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegisterUnitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        unitname = findViewById(R.id.tvUnitName);
        unitcode = findViewById(R.id.tvUnitCode);
        lecturername = findViewById(R.id.tvLecturerName);
        unitstage = findViewById(R.id.tvStage);
        student = findViewById(R.id.etStudentName);
        regNo = findViewById(R.id.etRegNo);

        btn = findViewById(R.id.buttonRegister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = student.getText().toString();
                String RegNo = regNo.getText().toString();

                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(RegisterUnit.this, "Enter your Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(RegNo)) {
                    Toast.makeText(RegisterUnit.this, "Enter Your Registration Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                registerUnit(unitname,unitcode,lecturername,unitstage,student,regNo);
                Toast.makeText(RegisterUnit.this, "You have successfully Registered this Unit", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterUnit.this, RegisteredUnits.class));
            }
        });

        name=getIntent().getStringExtra("unitName");
        code=getIntent().getStringExtra("unitCode");
        lecturer=getIntent().getStringExtra("lecturerName");
        stage=getIntent().getStringExtra("stage");

        binding.tvUnitName.setText(name);
        binding.tvUnitCode.setText(code);
        binding.tvLecturerName.setText(lecturer);
        binding.tvStage.setText(stage);

    }

    private void registerUnit(TextView unitname, TextView unitcode,  TextView lecturername,  TextView unitstage, EditText student, EditText regNo) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        RegisteredUnit registeredUnit = new RegisteredUnit(unitname.getText().toString(), unitcode.getText().toString(),
                lecturername.getText().toString(), unitstage.getText().toString(), student.getText().toString(), regNo.getText().toString());

        String key = ref.push().getKey();

        ref.child("Registered Units").child(key).setValue(registeredUnit).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegisterUnit.this, "Insertion Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}