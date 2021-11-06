package com.example.lab4_mealapp_tablelayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText edDescription;
    Button btnReturn;
    RadioGroup rgTextColor, rgBackColor;
    RadioButton rdRed, rdGreen, rdMagenta, rdYellow, rdWhite;
    String meal;
    int textColorId = 0, backColorId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        initialize();
    }

    private void initialize() {
        edDescription = findViewById(R.id.edDescription);
        rgTextColor = findViewById(R.id.rgTextColor);
        rgBackColor = findViewById(R.id.rgBackColor);
        rdRed = findViewById(R.id.rdRed);
        rdGreen = findViewById(R.id.rdGreen);
        rdMagenta = findViewById(R.id.rdMagenta);
        rdYellow = findViewById(R.id.rdYellow);
        rdWhite = findViewById(R.id.rdWhite);
        btnReturn = findViewById(R.id.btnReturn);

        meal = getIntent().getStringExtra("meal");
        edDescription.setText(meal);
        btnReturn.setOnClickListener(this);
        rdRed.setOnClickListener(this);
        rdGreen.setOnClickListener(this);
        rdMagenta.setOnClickListener(this);
        rdYellow.setOnClickListener(this);
        rdWhite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rdRed:
                textColorId = Color.RED;
                break;
            case R.id.rdGreen:
                textColorId = Color.GREEN;
                break;
            case R.id.rdMagenta:
                textColorId = Color.MAGENTA;
                break;
        }
        switch (id) {
            case R.id.rdYellow:
                backColorId = Color.YELLOW;
                break;
            case R.id.rdWhite:
                backColorId = Color.WHITE;
                break;
        }

        if (id == R.id.btnReturn){
            sendIntent(textColorId, backColorId);
        }
    }

    private void sendIntent(int textColorId, int backColorId) {
        String newMeal = edDescription.getText().toString();
        Intent intent = new Intent();

        if (meal.equals(newMeal) && textColorId == 0 && backColorId == 0){
            setResult(RESULT_CANCELED,intent);
        }else{
            intent.putExtra("new_meal", newMeal);
            intent.putExtra("new_textColor", textColorId);
            intent.putExtra("new_backgroundColor", backColorId);
            setResult(RESULT_OK,intent);
        }
        finish();
    }
}