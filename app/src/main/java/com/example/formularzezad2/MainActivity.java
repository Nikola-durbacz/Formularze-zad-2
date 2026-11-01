
package com.example.formularzezad2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private Spinner spPizza;
    private RadioGroup rgSize;
    private CheckBox cbCheese, cbHam, cbMushrooms;
    private SeekBar sbQuantity;
    private TextView tvQuantity, tvSummary;
    private Button btnOrder;

    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectViews();
        setupPizzaSpinner();
        setupQuantity();
        setupListeners();
    }

    private void connectViews() {
        etName = findViewById(R.id.etName);
        spPizza = findViewById(R.id.spPizza);
        rgSize = findViewById(R.id.rgSize);
        cbCheese = findViewById(R.id.cbCheese);
        cbHam = findViewById(R.id.cbHam);
        cbMushrooms = findViewById(R.id.cbMushrooms);
        sbQuantity = findViewById(R.id.sbQuantity);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvSummary = findViewById(R.id.tvSummary);
        btnOrder = findViewById(R.id.btnOrder);
    }

    private void setupPizzaSpinner() {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.pizzas,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spPizza.setAdapter(adapter);
    }

    private void setupQuantity() {
        sbQuantity.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {
                        quantity = progress + 1;
                        tvQuantity.setText("Ilość: " + quantity);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }

    private void setupListeners() {
        btnOrder.setOnClickListener(v -> showOrderSummary());
    }

    private void showOrderSummary() {
        String name = etName.getText().toString();
        String pizza = spPizza.getSelectedItem().toString();

        String size = "Nie wybrano";
        int selectedSize = rgSize.getCheckedRadioButtonId();

        if (selectedSize == R.id.rbSmall) {
            size = "Mała";
        } else if (selectedSize == R.id.rbMedium) {
            size = "Średnia";
        } else if (selectedSize == R.id.rbLarge) {
            size = "Duża";
        }

        String toppings = "";

        if (cbCheese.isChecked()) {
            toppings += "Dodatkowy ser, ";
        }

        if (cbHam.isChecked()) {
            toppings += "Szynka, ";
        }

        if (cbMushrooms.isChecked()) {
            toppings += "Pieczarki, ";
        }

        if (toppings.isEmpty()) {
            toppings = "Brak";
        }

        String summary =
                "Zamówienie:\n" +
                        "Imię: " + name + "\n" +
                        "Pizza: " + pizza + "\n" +
                        "Rozmiar: " + size + "\n" +
                        "Dodatki: " + toppings + "\n" +
                        "Ilość: " + quantity;

        tvSummary.setText(summary);
    }
}
