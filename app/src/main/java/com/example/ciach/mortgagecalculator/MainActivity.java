package com.example.ciach.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.math.*;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import static android.R.id.progress;
import static java.text.Format.*;


public class MainActivity extends AppCompatActivity
{
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private double purchasePrice = 0.0; //purchase amount entered by user
    private double downPayment = 0.0; // down payment entered by user
    private double interestRate = 0.0; // interest rate entered by user
    private int loanDuration = 15; // initial length of loan in years
    private TextView purchasePriceTextView;
    private TextView downPaymentTextView;
    private TextView interestRateTextView;
    private TextView loanDurationTextView;
    private TextView monthlyPaymentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to programmatically manipulated TextViews
        purchasePriceTextView = (TextView) findViewById(R.id.purchasePriceTextView);
        downPaymentTextView = (TextView) findViewById(R.id.downPaymentTextView);
        loanDurationTextView = (TextView) findViewById(R.id.loanDurationTextView);
        interestRateTextView = (TextView) findViewById(R.id.interestRateTextView);
        monthlyPaymentTextView = (TextView) findViewById(R.id.monthlyPaymentTextView);
        loanDurationTextView.setText(Integer.toString(0));



        // set purchasePriceEditText's Text Watcher
        EditText purchasePriceEditText =
                (EditText) findViewById(R.id.purchasePriceEditText);
        purchasePriceEditText.addTextChangedListener(purchasePriceEditTextWatcher);

         // set loanDurationSeekBar's OnSeekBarChangeListener
        SeekBar loanDurationSeekBar = (SeekBar) findViewById(R.id.loanDurationSeekBar);
        loanDurationSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    //calculate and display monthly payment amount

    private void calculate()
        {
            // format duration and display in loanDurationTextView
            loanDurationTextView.setText(Integer.toString(loanDuration));

            interestRate /= 100.0;

            double loanAmount = purchasePrice - downPayment;

            int termInMonths = loanDuration * 12;

            double monthlyAmount = (loanAmount / termInMonths);

            // calculate the monthly payment

            double monthlyPayment = monthlyAmount * (interestRate/100);

            // display monthlyPayment
            monthlyPaymentTextView.setText(currencyFormat.format(monthlyPayment));


        }

        // listener object for the SeekBar's progress changed events
    private final OnSeekBarChangeListener seekBarListener =
                new OnSeekBarChangeListener()
                {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                    {
                        loanDuration = progress; // set loanDuration based on progress
                        calculate(); // calculate and display loanDuration and monthlyPayment

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {

                    }
                };

                // listener object for the EditText's text changed events
    private final TextWatcher purchasePriceEditTextWatcher = new TextWatcher()
                {
                    // called when user changes purchasePrice
                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count)
                    {
                        try { // get amount and display currency formatted value
                            purchasePrice = Double.parseDouble(s.toString()) / 100.0;
                            purchasePriceTextView.setText(currencyFormat.format(purchasePrice));

                        } catch (NumberFormatException e)
                        { //if s is empty or non-numeric
                            purchasePriceTextView.setText("");
                            purchasePrice = 0.0;
                        }

                        calculate(); // update the Text Views
                    }
                    @Override
                    public void afterTextChanged(Editable s)
                    {

                    }

                    @Override
                    public void beforeTextChanged(
                            CharSequence s, int start, int count, int after)
                    {

                    }



                };


}
