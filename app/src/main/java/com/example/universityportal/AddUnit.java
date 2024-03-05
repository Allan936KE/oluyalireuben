package com.example.universityportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUnit extends AppCompatActivity {

    EditText etUnitName, etUnitCode, etLecturer, etStage;
    Button btn, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);

        etUnitName = findViewById(R.id.editTextUnitName);
        etUnitCode = findViewById(R.id.editTextUnitCode);
        etLecturer = findViewById(R.id.editTextLecturerName);
        etStage = findViewById(R.id.editTextStage);
        btn = findViewById(R.id.buttonAdd);
        btnBack = findViewById(R.id.buttonBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddUnit.this, LecturerDashboard.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String UnitName = etUnitName.getText().toString();
                String UnitCode = etUnitCode.getText().toString();
                String LecturerName = etLecturer.getText().toString();
                String Stage = etStage.getText().toString();

                if (TextUtils.isEmpty(UnitName)) {
                    Toast.makeText(AddUnit.this, "Enter Unit Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(UnitCode)) {
                    Toast.makeText(AddUnit.this, "Enter Unit Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(LecturerName)) {
                    Toast.makeText(AddUnit.this, "Enter Lecturer Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Stage)) {
                    Toast.makeText(AddUnit.this, "Enter Unit Stage", Toast.LENGTH_SHORT).show();
                    return;
                }


                addUnit(etUnitName,etUnitCode,etLecturer,etStage);
                Toast.makeText(AddUnit.this, "You have successfully added the Unit", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddUnit.this, AddedUnits.class));
            }
        });

    }

    private void addUnit(EditText etUnitName, EditText etUnitCode,  EditText etLecturer,  EditText etStage) {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        Unit unit=new Unit(etUnitName.getText().toString(),etUnitCode.getText().toString(),etLecturer.getText().toString(),
                etStage.getText().toString());

        String key=ref.push().getKey();

        ref.child("Units").child(key).setValue(unit).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddUnit.this,"Insertion Complete",Toast.LENGTH_SHORT).show();
            }
        });
    }
}