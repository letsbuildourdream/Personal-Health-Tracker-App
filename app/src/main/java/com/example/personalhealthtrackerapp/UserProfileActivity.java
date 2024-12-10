package com.example.personalhealthtrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class UserProfileActivity extends AppCompatActivity {
    private EditText editName, editAge, editWeight, editHeight;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        btnNext = findViewById(R.id.btnSaveProfile);

        btnNext.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String age = editAge.getText().toString();
            String weight = editWeight.getText().toString();
            String height = editHeight.getText().toString();

            if (validateUserProfile(name, age, weight, height)) {
                Intent intent = new Intent(UserProfileActivity.this, ExerciseTrackingActivity.class);
                intent.putExtra("name", encryptData(name));
                intent.putExtra("age", encryptData(age));
                intent.putExtra("weight", encryptData(weight));
                intent.putExtra("height", encryptData(height));
                startActivity(intent);
            }
        });
    }

    private boolean validateUserProfile(String name, String age, String weight, String height) {
        if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty()) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int ageInt = Integer.parseInt(age);
            float weightFloat = Float.parseFloat(weight);
            float heightFloat = Float.parseFloat(height);

            if (ageInt <= 0 || weightFloat <= 0 || heightFloat <= 0) {
                Toast.makeText(this, "Age, weight, and height must be positive values.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private String encryptData(String data) {
        // Placeholder encryption logic (implement as needed)
        return data;  // For simplicity, no actual encryption here.
    }
}
