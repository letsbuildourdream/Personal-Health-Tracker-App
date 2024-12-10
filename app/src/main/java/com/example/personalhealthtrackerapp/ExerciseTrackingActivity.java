package com.example.personalhealthtrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseTrackingActivity extends AppCompatActivity {
    private EditText editExerciseType, editDuration, editCaloriesBurned;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_tracking);

        editExerciseType = findViewById(R.id.editExerciseType);
        editDuration = findViewById(R.id.editDuration);
        editCaloriesBurned = findViewById(R.id.editCaloriesBurned);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            String exerciseType = editExerciseType.getText().toString();
            String duration = editDuration.getText().toString();
            String caloriesBurned = editCaloriesBurned.getText().toString();

            if (validateExerciseData(exerciseType, duration, caloriesBurned)) {
                Intent intent = new Intent(ExerciseTrackingActivity.this, DietTrackingActivity.class);
                Intent prevIntent = getIntent();
                intent.putExtra("name", prevIntent.getStringExtra("name"));
                intent.putExtra("age", prevIntent.getStringExtra("age"));
                intent.putExtra("weight", prevIntent.getStringExtra("weight"));
                intent.putExtra("height", prevIntent.getStringExtra("height"));
                intent.putExtra("exerciseType", encryptData(exerciseType));
                intent.putExtra("exerciseDuration", encryptData(duration));
                intent.putExtra("caloriesBurned", encryptData(caloriesBurned));
                startActivity(intent);
            }
        });
    }

    private boolean validateExerciseData(String exerciseType, String duration, String caloriesBurned) {
        if (exerciseType.isEmpty() || duration.isEmpty() || caloriesBurned.isEmpty()) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int durationInt = Integer.parseInt(duration);
            int caloriesInt = Integer.parseInt(caloriesBurned);

            if (durationInt <= 0 || caloriesInt <= 0) {
                Toast.makeText(this, "Duration and calories burned must be positive values.", Toast.LENGTH_SHORT).show();
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

