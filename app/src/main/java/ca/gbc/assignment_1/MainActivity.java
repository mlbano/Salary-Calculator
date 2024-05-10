package ca.gbc.assignment_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText hoursWorkedEditText;
    private EditText hourlyRateEditText;
    private TextView payTextView;
    private TextView overtimePayTextView;
    private TextView totalPayTextView;
    private TextView taxTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        hoursWorkedEditText = findViewById(R.id.hoursWorkedEditText);
        hourlyRateEditText = findViewById(R.id.hourlyRateEditText);
        payTextView = findViewById(R.id.payTextView);
        overtimePayTextView = findViewById(R.id.overtimePayTextView);
        totalPayTextView = findViewById(R.id.totalPayTextView);
        taxTextView = findViewById(R.id.taxTextView);
    }

    public void calculatePay(View view) {
        // Get input values
        double hoursWorked = Double.parseDouble(hoursWorkedEditText.getText().toString());
        double hourlyRate = Double.parseDouble(hourlyRateEditText.getText().toString());

        // Calculate pay, overtime pay, and tax
        double pay;
        double overtimePay;
        double totalPay;
        double tax;

        if (hoursWorked <= 40) {
            pay = hoursWorked * hourlyRate;
            overtimePay = 0;
        } else {
            pay = (hoursWorked - 40) * hourlyRate * 1.5 + 40 * hourlyRate;
            overtimePay = (hoursWorked - 40) * hourlyRate * 1.5;
        }

        totalPay = pay + overtimePay;
        tax = totalPay * 0.18;

        payTextView.setText("Pay: $" + pay);
        overtimePayTextView.setText("Overtime Pay: $" + overtimePay);
        totalPayTextView.setText("Total Pay: $" + totalPay);
        taxTextView.setText("Tax: $" + tax);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about_item) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}