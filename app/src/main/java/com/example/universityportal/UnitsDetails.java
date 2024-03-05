package com.example.universityportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.universityportal.databinding.ActivityUnitsDetailsBinding;

public class UnitsDetails extends AppCompatActivity {
    ActivityUnitsDetailsBinding binding;
    String name,code,lecturer,stage;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUnitsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btn = findViewById(R.id.buttonBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UnitsDetails.this, AddedUnits.class));
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
}








