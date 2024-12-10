package com.example.personalhealthtrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {
    private TextView textSummary;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        textSummary = findViewById(R.id.textSummary);
        btnEdit = findViewById(R.id.btnEdit);

        // Receive data
        Intent intent = getIntent();
        String name = decryptData(intent.getStringExtra("name"));
        String age = decryptData(intent.getStringExtra("age"));
        String weight = decryptData(intent.getStringExtra("weight"));
        String height = decryptData(intent.getStringExtra("height"));
        String exerciseType = decryptData(intent.getStringExtra("exerciseType"));
        String exerciseDuration = decryptData(intent.getStringExtra("exerciseDuration"));
        String caloriesBurned = decryptData(intent.getStringExtra("caloriesBurned"));
        String meals = decryptData(intent.getStringExtra("meals"));
        String caloriesConsumed = decryptData(intent.getStringExtra("caloriesConsumed"));
        String waterIntake = decryptData(intent.getStringExtra("waterIntake"));

        // Display the summary
        String summary = "Name: " + name + "\n"
                + "Age: " + age + "\n"
                + "Weight: " + weight + "\n"
                + "Height: " + height + "\n"
                + "Exercise: " + exerciseType + " for " + exerciseDuration + " minutes\n"
                + "Calories Burned: " + caloriesBurned + "\n"
                + "Meals: " + meals + "\n"
                + "Calories Consumed: " + caloriesConsumed + "\n"
                + "Water Intake: " + waterIntake + "L";

        textSummary.setText(summary);

        // Edit the profile
        btnEdit.setOnClickListener(v -> {
            // Pass the current data to UserProfileActivity for editing
            Intent editIntent = new Intent(SummaryActivity.this, UserProfileActivity.class);
            editIntent.putExtra("name", name);
            editIntent.putExtra("age", age);
            editIntent.putExtra("weight", weight);
            editIntent.putExtra("height", height);
            editIntent.putExtra("exerciseType", exerciseType);
            editIntent.putExtra("exerciseDuration", exerciseDuration);
            editIntent.putExtra("caloriesBurned", caloriesBurned);
            editIntent.putExtra("meals", meals);
            editIntent.putExtra("caloriesConsumed", caloriesConsumed);
            editIntent.putExtra("waterIntake", waterIntake);
            startActivityForResult(editIntent, 1);  // Request code 1 for identifying the activity
        });
    }

    private String decryptData(String data) {
        // Placeholder decryption logic (implement as needed)
        return data;  // For simplicity, no actual decryption here.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result is from the UserProfileActivity
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Retrieve updated data from UserProfileActivity
            String updatedName = data.getStringExtra("name");
            String updatedAge = data.getStringExtra("age");
            String updatedWeight = data.getStringExtra("weight");
            String updatedHeight = data.getStringExtra("height");
            String updatedExerciseType = data.getStringExtra("exerciseType");
            String updatedExerciseDuration = data.getStringExtra("exerciseDuration");
            String updatedCaloriesBurned = data.getStringExtra("caloriesBurned");
            String updatedMeals = data.getStringExtra("meals");
            String updatedCaloriesConsumed = data.getStringExtra("caloriesConsumed");
            String updatedWaterIntake = data.getStringExtra("waterIntake");

            // Update the summary with the new data
            String updatedSummary = "Name: " + updatedName + "\n"
                    + "Age: " + updatedAge + "\n"
                    + "Weight: " + updatedWeight + "\n"
                    + "Height: " + updatedHeight + "\n"
                    + "Exercise: " + updatedExerciseType + " for " + updatedExerciseDuration + " minutes\n"
                    + "Calories Burned: " + updatedCaloriesBurned + "\n"
                    + "Meals: " + updatedMeals + "\n"
                    + "Calories Consumed: " + updatedCaloriesConsumed + "\n"
                    + "Water Intake: " + updatedWaterIntake + "L";

            textSummary.setText(updatedSummary);  // Display updated information
        }
    }
}
