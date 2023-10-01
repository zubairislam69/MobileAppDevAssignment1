package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

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

        //add event listener for calculate button
        //when button is clicked, the user's input for the mortgage, interest and loan tenure
        //will be retrieved and used for calculation of EMI
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the inputs from the text fields
                String mortgageAmountString = mortgageAmountEditText.getText().toString();
                String interestRateString = interestRateEditText.getText().toString();
                String loanTenureString = loanTenureEditText.getText().toString();

                // Display the result in a TextView
                TextView resultTextView = findViewById(R.id.tvResult);

                // Check for empty input or non-numeric input
                if (!mortgageAmountString.isEmpty() && !interestRateString.isEmpty() && !loanTenureString.isEmpty()) {
                    //convert strings to double
                    double mortgageAmount = Double.parseDouble(mortgageAmountString);
                    double interestRate = Double.parseDouble(interestRateString);

                    double monthlyInterestRate = interestRate / 12 / 100;

                    //convert string to int
                    int loanTenure = Integer.parseInt(loanTenureString);
                    int numberOfMonthlyPayments = loanTenure * 12;

                    //calculation of EMI
                    EMI = mortgageAmount * monthlyInterestRate *
                            Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) /
                            (Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) - 1);

                    //stores the value of EMI inside resource
                    String emiMessage = getString(R.string.emi_message, EMI);

                    //sets the value of EMI to the result text view
                    resultTextView.setText(emiMessage);

                } else
                    resultTextView.setText(R.string.error_message);
            }
        });
    }
}