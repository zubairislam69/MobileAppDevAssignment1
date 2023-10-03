package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        Button goBackButton = findViewById(R.id.btnBackToCalculation);

        // Retrieve the calculated EMI value from the intent
        Intent calculationDataIntent = getIntent();
        double emi = calculationDataIntent.getDoubleExtra("emi", 0.0);

        TextView resultTextView = findViewById(R.id.tvResult);

        // Format and display the calculated EMI value
        String emiMessage = getString(R.string.emi_message, emi);
        resultTextView.setText(emiMessage);

        // Set up a click listener for the "Go Back" button
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create an intent to return to the main activity for a new calculation
                Intent goBackIntent = new Intent(CalculationActivity.this, MainActivity.class);
                startActivity(goBackIntent);
            }
        });
    }
}