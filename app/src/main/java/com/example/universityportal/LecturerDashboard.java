package com.example.universityportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.universityportal.databinding.ActivityLecturerDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LecturerDashboard extends AppCompatActivity {
    ActivityLecturerDashboardBinding binding;
    RecyclerView recyclerView;
    ArrayList<RegisteredUnit> registeredunits;
    RegisteredUnitsAdapter.OnUnitClicked onUnitClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLecturerDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onUnitClicked = new RegisteredUnitsAdapter.OnUnitClicked() {
            @Override
            public void onUnitClicked(int position) {
                startActivity(new Intent(LecturerDashboard.this, ViewStudentDetails.class)
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
                    String studentName = ds.child("studentName").getValue(String.class);
                    String regNo = ds.child("regNo").getValue(String.class);

                    RegisteredUnit model = new RegisteredUnit(unitName, unitCode, lecturerName, stage, studentName, regNo);
                    registeredunits.add(model);
                    binding.registeredRecyclerView.setVisibility(View.VISIBLE);
                    binding.registeredProgress.setVisibility(View.GONE);
                }
                recyclerView.setAdapter(new RegisteredUnitsAdapter(registeredunits, LecturerDashboard.this, onUnitClicked));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navHome:
                            // Start HomeActivity or perform relevant action
                            startActivity(new Intent(LecturerDashboard.this, LecturerDashboard.class));
                            Toast.makeText(LecturerDashboard.this, "Home", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navAddUnit:
                            // Start AddUnitActivity or perform relevant action
                            startActivity(new Intent(LecturerDashboard.this, AddUnit.class));
                            Toast.makeText(LecturerDashboard.this, "Add Unit", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navChat:
                            // Start AddUnitActivity or perform relevant action
                            Toast.makeText(LecturerDashboard.this, "Chat with students", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LecturerDashboard.this, Chats.class)
                                    .putExtra("is_viewing_as_lecturer" , true));
                            return true;
                        case R.id.navView:
                            // Start Added Unit Activity or perform relevant action
                            startActivity(new Intent(LecturerDashboard.this, AddedUnits.class));
                            Toast.makeText(LecturerDashboard.this, "View Added Units", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navLogout:
                            // Sign out the user from Firebase Authentication
                            FirebaseAuth.getInstance().signOut();
                            // Start LogoutActivity or perform relevant action
                            startActivity(new Intent(LecturerDashboard.this, LoginActivity.class));
                            Toast.makeText(LecturerDashboard.this, "You have successfully Logged out", Toast.LENGTH_SHORT).show();
                            finish();
                    }
                    finish();
                    return false;
                }
            };
}