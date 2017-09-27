package com.example.ciach.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity
{
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private double purchasePrice = 0.0; //purchase amount entered by user
    private double downPayment = 0.0; // down payment entered by user
    private double interestRate = 0.0; // interest rate entered by user
    private double loanDuration = 30.0; // initial length of loan in years
    private double monthlyPayment = 0.0; // configured monthly payment

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to programmatically manipulated TextViews
        purchasePriceTextView = (TextView) findViewById(R.id.purchasePriceTextView);
        downPaymentTextView = (TextView) findViewById(R.id.downPaymentTextView);
        interestRateTextView = (TextView) findViewByID(R.id.interestRateTextView);
        monthlyPaymentTextView = (TextView) findViewByID(R.id.monthlyPaymentTextView);
        loanDurationView.setText(currencyFormat.format(0));
        monthlyPaymentView.setText(currencyFormat.format(0));

        // set purchasePriceEditText's Text Watcher
        EditText purchasePriceEditText =
                (EditText) findViewById(R.id.purchasePriceEditText);
        purchasePriceEditText.addTextChangedListener(purchasePriceEditTextWatcher);

        // set loanDurationSeekBar's OnSeekBarChangeListener
        SeekBar loanDurationSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        loanDurationSeekBar.setOnSeekBarChangeListener(seekBarListener);

        //calculate and display monthly payment amount

    private void calculate()
        {
            // format loan duration amount and display in loanDurationTextView
            loanDurationTextView.setText(numberFormat.format(number));

            // calculate the monthly payment
            double monthlyPayment = ((purchasePrice - downPayment) * (interestRate)/(loanDuration));

            // display monthlyPayment
            loanDurationTextView.setText(currencyFormat.format(tip));
            monthlyPaymentTextView.setText(currencyFormat.format(format(monthlyPayme)));

        }
    }

}
