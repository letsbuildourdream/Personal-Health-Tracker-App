package com.example.personalhealthtrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DietTrackingActivity extends AppCompatActivity {
    private EditText editMeals, editCaloriesConsumed, editWaterIntake;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_tracking);

        editMeals = findViewById(R.id.editMeals);
        editCaloriesConsumed = findViewById(R.id.editCaloriesConsumed);
        editWaterIntake = findViewById(R.id.editWaterIntake);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            String meals = editMeals.getText().toString();
            String caloriesConsumed = editCaloriesConsumed.getText().toString();
            String waterIntake = editWaterIntake.getText().toString();

            if (validateDietData(meals, caloriesConsumed, waterIntake)) {
                Intent intent = new Intent(DietTrackingActivity.this, SummaryActivity.class);
                intent.putExtra("meals", encryptData(meals));
                intent.putExtra("caloriesConsumed", encryptData(caloriesConsumed));
                intent.putExtra("waterIntake", encryptData(waterIntake));

                // Pass the data from previous activities
                Intent prevIntent = getIntent();
                intent.putExtra("name", prevIntent.getStringExtra("name"));
                intent.putExtra("age", prevIntent.getStringExtra("age"));
                intent.putExtra("weight", prevIntent.getStringExtra("weight"));
                intent.putExtra("height", prevIntent.getStringExtra("height"));
                intent.putExtra("exerciseType", prevIntent.getStringExtra("exerciseType"));
                intent.putExtra("exerciseDuration", prevIntent.getStringExtra("exerciseDuration"));
                intent.putExtra("caloriesBurned", prevIntent.getStringExtra("caloriesBurned"));

                startActivity(intent);
            }
        });
    }

    private boolean validateDietData(String meals, String caloriesConsumed, String waterIntake) {
        if (meals.isEmpty() || caloriesConsumed.isEmpty() || waterIntake.isEmpty()) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            float caloriesFloat = Float.parseFloat(caloriesConsumed);
            float waterFloat = Float.parseFloat(waterIntake);

            if (caloriesFloat <= 0 || waterFloat <= 0) {
                Toast.makeText(this, "Calories and water intake must be positive values.", Toast.LENGTH_SHORT).show();
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
