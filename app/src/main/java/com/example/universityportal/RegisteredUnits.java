package com.example.universityportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.universityportal.databinding.ActivityRegisteredUnitsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisteredUnits extends AppCompatActivity {
    ActivityRegisteredUnitsBinding binding;
    RecyclerView recyclerView;
    ArrayList<RegisteredUnit> registeredunits;
    ImageView imageView;
    RegisteredUnitsAdapter.OnUnitClicked onUnitClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisteredUnitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageView = findViewById(R.id.IV_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisteredUnits.this, StudentDashboard.class));
            }
        });

        onUnitClicked = new RegisteredUnitsAdapter.OnUnitClicked() {
            @Override
            public void onUnitClicked(int position) {
                startActivity(new Intent(RegisteredUnits.this, UnitsDetails.class)
                        .putExtra("unitName", registeredunits.get(position).getUnitName())
                        .putExtra("unitCode", registeredunits.get(position).getUnitCode())
                        .putExtra("lecturerName", registeredunits.get(position).getLecturerName())
                        .putExtra("stage", registeredunits.get(position).getStage())
                        .putExtra("studentName", registeredunits.get(position).getStudentName())
                        .putExtra("regNo", registeredunits.get(position).getRegNo()));
            }
        };

        recyclerView = binding.registeredRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        registeredunits = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Registered Units");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                registeredunits.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String unitName = ds.child("unitName").getValue(String.class);
                    String unitCode = ds.child("unitCode").getValue(String.class);
                    String lecturerName = ds.child("lecturerName").getValue(String.class);
                    String stage = ds.child("stage").getValue(String.class);
                    String studentname = ds.child("studentName").getValue(String.class);
                    String regno = ds.child("regNo").getValue(String.class);

                    RegisteredUnit model = new RegisteredUnit(unitName, unitCode, lecturerName, stage, studentname, regno);
                    registeredunits.add(model);
                    binding.registeredRecyclerView.setVisibility(View.VISIBLE);
                    binding.registeredProgress.setVisibility(View.GONE);
                }
                recyclerView.setAdapter(new RegisteredUnitsAdapter(registeredunits, RegisteredUnits.this, onUnitClicked));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}