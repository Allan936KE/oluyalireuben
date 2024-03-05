package com.example.universityportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.universityportal.databinding.ActivityAddedUnitsBinding;
import com.example.universityportal.databinding.ActivityViewUnitsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewUnits extends AppCompatActivity {
    ActivityViewUnitsBinding binding;
    RecyclerView recyclerView;
    ArrayList<Unit> units;
    ImageView imageView;
    UnitsAdapter.OnUnitClicked onUnitClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewUnitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageView = findViewById(R.id.IV_back1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewUnits.this, StudentDashboard.class));
            }
        });

        onUnitClicked = new UnitsAdapter.OnUnitClicked() {
            @Override
            public void onUnitClicked(int position) {
                startActivity(new Intent(ViewUnits.this, RegisterUnit.class)
                        .putExtra("unitName", units.get(position).getUnitName())
                        .putExtra("unitCode", units.get(position).getUnitCode())
                        .putExtra("lecturerName", units.get(position).getLecturerName())
                        .putExtra("stage", units.get(position).getStage()));
            }
        };

        recyclerView = binding.addedRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        units = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Units");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                units.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String unitName = ds.child("unitName").getValue(String.class);
                    String unitCode = ds.child("unitCode").getValue(String.class);
                    String lecturerName = ds.child("lecturerName").getValue(String.class);
                    String stage = ds.child("stage").getValue(String.class);

                    Unit model = new Unit(unitName, unitCode, lecturerName, stage);
                    units.add(model);
                    binding.addedRecyclerView.setVisibility(View.VISIBLE);
                    binding.addedProgress.setVisibility(View.GONE);
                }
                recyclerView.setAdapter(new UnitsAdapter(units, ViewUnits.this, onUnitClicked));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}