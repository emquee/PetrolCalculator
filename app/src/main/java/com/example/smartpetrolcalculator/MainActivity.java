package com.example.smartpetrolcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerPetrol;
    EditText editPrice, editUsage;
    RadioGroup radioGroup;
    RadioButton radioYes, radioNo;
    Button btnCalculate, btnReset;
    TextView textGross, textRebate, textResult, textResultType;
    BottomNavigationView bottomNavigation;
    ScrollView scrollView;

    // NEW (only addition for smooth scroll target)
    private android.view.View resultCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerPetrol    = findViewById(R.id.spinnerPetrol);
        editPrice        = findViewById(R.id.editPrice);
        editUsage        = findViewById(R.id.editUsage);
        radioGroup       = findViewById(R.id.radioGroup);
        radioYes         = findViewById(R.id.radioYes);
        radioNo          = findViewById(R.id.radioNo);
        btnCalculate     = findViewById(R.id.btnCalculate);
        btnReset         = findViewById(R.id.btnReset);
        textGross        = findViewById(R.id.textGross);
        textRebate       = findViewById(R.id.textRebate);
        textResult       = findViewById(R.id.textResult);
        textResultType   = findViewById(R.id.textResultType);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        scrollView       = findViewById(R.id.scrollView);

        // NEW
        resultCard = findViewById(R.id.resultCard);

        String[] petrolTypes = {
                "Select Petrol Type",
                "RON95",
                "RON97",
                "Diesel"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                petrolTypes
        );

        spinnerPetrol.setAdapter(adapter);

        btnCalculate.setOnClickListener(v -> calculatePetrol());

        btnReset.setOnClickListener(v -> resetForm());

        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                return true;
            }
            if (item.getItemId() == R.id.nav_about) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigation.setSelectedItemId(R.id.nav_home);
    }

    private void calculatePetrol() {

        String petrolType = spinnerPetrol.getSelectedItem().toString();
        String priceText  = editPrice.getText().toString().trim();
        String usageText  = editUsage.getText().toString().trim();

        if (petrolType.equals("Select Petrol Type")) {
            Toast.makeText(this, "Please select petrol type", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(priceText)) {
            editPrice.setError("Price is required");
            return;
        }

        if (TextUtils.isEmpty(usageText)) {
            editUsage.setError("Fuel usage is required");
            return;
        }

        double price;
        double usage;

        try {
            price = Double.parseDouble(priceText);
            usage = Double.parseDouble(usageText);

            if (price <= 0) {
                editPrice.setError("Price must be greater than 0");
                editPrice.setText("");
                return;
            }

            if (usage <= 0) {
                editUsage.setError("Usage must be greater than 0");
                editUsage.setText("");
                return;
            }

        } catch (Exception e) {
            Toast.makeText(this, "Invalid input value", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!radioYes.isChecked() && !radioNo.isChecked()) {
            Toast.makeText(this, "Please select BUDI eligibility", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalCost = usage * price;
        double rebate    = 0;

        if (petrolType.equals("RON95") && radioYes.isChecked()) {
            rebate = usage * 1.99;
        }

        double finalCost = totalCost - rebate;

        textResultType.setText(petrolType);
        textGross.setText("RM " + String.format("%.2f", totalCost));
        textRebate.setText(rebate > 0 ? "− RM " + String.format("%.2f", rebate) : "RM 0.00");
        textResult.setText("RM " + String.format("%.2f", finalCost));

        // ✅ IMPROVED SCROLL (IMPORTANT CHANGE ONLY)
        scrollView.post(() ->
                scrollView.smoothScrollTo(0, resultCard.getTop())
        );

        Snackbar.make(
                findViewById(android.R.id.content),
                "Calculation complete!",
                Snackbar.LENGTH_SHORT
        ).show();
    }

    private void resetForm() {
        spinnerPetrol.setSelection(0);
        editPrice.setText("");
        editUsage.setText("");
        radioGroup.clearCheck();

        textResultType.setText("—");
        textGross.setText("—");
        textRebate.setText("—");
        textResult.setText("—");

        scrollView.smoothScrollTo(0, 0);

        Toast.makeText(this, "Form reset", Toast.LENGTH_SHORT).show();
    }
}