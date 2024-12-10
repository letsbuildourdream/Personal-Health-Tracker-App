package com.example.personalhealthtrackerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize sharedPreferences
        sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);

        // Check if the user has already given consent. If not, show the consent dialog
        if (!hasUserGivenConsent()) {
            // Consent has not been given, show the consent dialog
            showConsentDialog();
        } else {
            // Consent has been given, navigate directly to the user profile
            navigateToUserProfile();
        }
    }

    // Save user consent to SharedPreferences
    private void saveUserConsent(boolean consentGiven) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("userConsent", consentGiven);
        editor.apply();
    }

    // Check if the user has already given consent
    private boolean hasUserGivenConsent() {
        return sharedPreferences.getBoolean("userConsent", false);
    }

    // Show the consent dialog
    private void showConsentDialog() {
        // Create and show the consent dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Data Privacy Consent")
                .setMessage("We respect your privacy. By proceeding, you agree to the collection and processing of your data in compliance with GDPR and APPs.")
                .setPositiveButton("Agree", (dialog, which) -> {
                    // User agrees to the consent
                    saveUserConsent(true);
                    // Navigate to the UserProfileActivity after consent
                    navigateToUserProfile();
                })
                .setNegativeButton("Decline", (dialog, which) -> {
                    // User declines consent, save consent as false
                    saveUserConsent(false);
                    dialog.dismiss();
                    // Close the app if user does not agree to the terms
                    finish();
                })
                .setCancelable(false) // Disable canceling the dialog by tapping outside
                .show();
    }

    // Navigate to UserProfileActivity
    private void navigateToUserProfile() {
        // Once consent is granted, navigate to the user profile page
        Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
        startActivity(intent);
        // Close the main activity so that the user can't go back to it
        finish();
    }
}
