package com.example.universityportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.universityportal.databinding.ActivityLecturerDashboardBinding;
import com.example.universityportal.databinding.ActivityParentDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentDashboard extends AppCompatActivity {
    ActivityParentDashboardBinding binding;
    RecyclerView recyclerView;
    ArrayList<Grade> grades;
    GradesAdapter.OnUnitClicked onUnitClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_dashboard);
        binding = ActivityParentDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onUnitClicked = new GradesAdapter.OnUnitClicked(){
            @Override
            public void onUnitClicked(int position) {
                startActivity(new Intent(ParentDashboard.this, ViewGrade.class)
                        .putExtra("unitName", grades.get(position).getUnitName())
                        .putExtra("unitCode", grades.get(position).getUnitCode())
                        .putExtra("lecturerName", grades.get(position).getLecturerName())
                        .putExtra("stage", grades.get(position).getStage())
                        .putExtra("studentName", grades.get(position).getStudentName())
                        .putExtra("regNo", grades.get(position).getRegNo())
                        .putExtra("grade", grades.get(position).getGrade()));
            }
        };

        recyclerView = binding.registeredRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        grades = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Grade");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                grades.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String unitName = ds.child("unitName").getValue(String.class);
                    String unitCode = ds.child("unitCode").getValue(String.class);
                    String lecturerName = ds.child("lecturerName").getValue(String.class);
                    String stage = ds.child("stage").getValue(String.class);
                    String studentName = ds.child("studentName").getValue(String.class);
                    String regNo = ds.child("regNo").getValue(String.class);
                    String grade = ds.child("grade").getValue(String.class);

                    Grade model = new Grade(unitName, unitCode, lecturerName, stage, studentName, regNo,grade);
                    grades.add(model);
                    binding.registeredRecyclerView.setVisibility(View.VISIBLE);
                    binding.registeredProgress.setVisibility(View.GONE);
                }
                recyclerView.setAdapter(new GradesAdapter(grades, ParentDashboard.this, onUnitClicked));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}