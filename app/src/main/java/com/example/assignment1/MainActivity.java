package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mortgageAmountString = mortgageAmountEditText.getText().toString();

                String interestRateString = interestRateEditText.getText().toString();

                String loanTenureString = loanTenureEditText.getText().toString();



                // Check for empty input or non-numeric input
                if (!mortgageAmountString.isEmpty() || !interestRateString.isEmpty()) {
                    try {
                        double mortgageAmount = Double.parseDouble(mortgageAmountString);
                        double interestRate = Double.parseDouble(interestRateString);
                        double monthlyInterestRate = interestRate / 12 / 100;
                        int loanTenure = Integer.parseInt(loanTenureString);

                        int numberOfMonthlyPayments = loanTenure * 12;


                        EMI = mortgageAmount * monthlyInterestRate *
                                Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) /
                                (Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) - 1);

                        // Display the result in a TextView
                        TextView resultTextView = findViewById(R.id.tvResult);

                        String emiMessage = getString(R.string.emi_message, EMI);
                        resultTextView.setText(emiMessage);

                    } catch (NumberFormatException e) {
                        // Handle invalid input (non-numeric)
                        // Show an error message or perform appropriate action
                    }
                } else {
                    // Handle empty input
                    // Show an error message or perform appropriate action
                }
            }


        });

    }
}