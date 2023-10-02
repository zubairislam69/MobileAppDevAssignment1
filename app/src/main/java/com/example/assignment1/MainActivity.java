package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    double EMI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateButton = findViewById(R.id.btnCalculate);

        TextInputEditText mortgageAmountEditText = findViewById(R.id.etMortgageAmount);
        TextInputEditText interestRateEditText = findViewById(R.id.etInterestRate);
        TextInputEditText loanTenureEditText = findViewById(R.id.etLoanTenure);

        // Add event listener for "Calculate Payment" button
        // When button is clicked, the user's input for the mortgage, interest and loan tenure
        // will be retrieved and used for calculation of EMI
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the inputs from the text fields
                String mortgageAmountString = mortgageAmountEditText.getText().toString();
                String interestRateString = interestRateEditText.getText().toString();
                String loanTenureString = loanTenureEditText.getText().toString();

                // Display the error in a TextView
                TextView errorTextView = findViewById(R.id.tvError);

                // Check for empty input
                if (!mortgageAmountString.isEmpty() && !interestRateString.isEmpty() && !loanTenureString.isEmpty()) {
                    // Convert strings to double
                    double mortgageAmount = Double.parseDouble(mortgageAmountString);
                    double interestRate = Double.parseDouble(interestRateString);

                    double monthlyInterestRate = interestRate / 12 / 100;

                    // Convert string to int
                    int loanTenure = Integer.parseInt(loanTenureString);
                    int numberOfMonthlyPayments = loanTenure * 12;

                    // Calculate EMI
                    EMI = mortgageAmount * monthlyInterestRate *
                            Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) /
                            (Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) - 1);

                    // Create an Intent to switch to the CalculationActivity and pass the calculated EMI for display
                    Intent calculationIntent = new Intent(MainActivity.this, CalculationActivity.class);
                    calculationIntent.putExtra("emi", EMI);

                    // Start the CalculationActivity
                    startActivity(calculationIntent);

                } else
                    // Display an error message if there are empty inputs
                    errorTextView.setText(R.string.error_message);
            }
        });
    }
}