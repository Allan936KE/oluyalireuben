package com.example.universityportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        CardView units = findViewById(R.id.cardViewUnits);
        units.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboard.this, ViewUnits.class));
            }
        });
        CardView regunits = findViewById(R.id.cardRegisteredUnits);
        regunits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboard.this, RegisteredUnits.class));
            }
        });
        CardView chat = findViewById(R.id.cardViewChat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboard.this, Chats.class));
            }
        });
        CardView results = findViewById(R.id.cardViewResults);
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboard.this, ViewResults.class));
            }
        });
        CardView logout = findViewById(R.id.cardLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboard.this, LoginActivity.class));
            }
        });
    }
}