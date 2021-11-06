package com.example.lab4_mealapp_tablelayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import Model.meals;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView[] listOfTextView;
    int widgets[] = {R.id.tvMonTuesB, R.id.tvWedB, R.id.tvThurFriB, R.id.tvMonL, R.id.tvTuesL, R.id.tvWedL, R.id.tvThurL,
            R.id.tvFriL, R.id.tvMonS, R.id.tvTuesWedS, R.id.tvThurFriS};
    meals[] listOfMeal;
    TextView clickedTv;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalize();
    }

    private void initalize() {
        listOfMeal = new meals[11];
        listOfMeal[0] = new meals(1, "Cheerios\n\nBanana\nMilk");
        listOfMeal[1] = new meals(2, "Pancakes\n\nBlueberries\nMilk");
        listOfMeal[2] = new meals(3, "Scrambled Eggs\n& Toast\nPotatoes\n100% Juice");
        listOfMeal[3] = new meals(4, "Mashed\nPotatoes\nPeas & Butter\nMilk");
        listOfMeal[4] = new meals(5, "Tuna Fish\nSandwich\nApplesauce\nCarrot Sticks\nMilk");
        listOfMeal[5] = new meals(6, "Rice & Chicken\nStew\nCarrot & Potatoes\nMilk");
        listOfMeal[6] = new meals(7, "Macaroni &\nCheese\nFish Sticks\nGreen Beans\nMilk");
        listOfMeal[7] = new meals(8, "Whole Wheat\nPizza\nSpinach\nOrange Slices\nMilk");
        listOfMeal[8] = new meals(9,"Crackers with\nPeanut Butter\n\n100% Juice");
        listOfMeal[9] = new meals(10,"Yogurt\nRaisins /\nPeaches\n\n100% Juice");
        listOfMeal[10] = new meals(11, "Home-made\nBlueberry\nMuffin\n\n100% Juice");

        listOfTextView = new TextView[11];
        for (int i = 0; i < 11; i ++) {
            listOfTextView[i] = findViewById(widgets[i]);
            listOfTextView[i].setText(listOfMeal[i].toString());
            listOfTextView[i].setOnClickListener(this);
        }

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            // In this method we will process the result
            // sent bt ChangeSchedule (ChangeActivity) activity
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String newMeal = result.getData().getStringExtra("new_meal");
                    int newTextColor = result.getData().getIntExtra("new_textColor", 0);
                    int newBackgroundColor = result.getData().getIntExtra("new_backgroundColor", 0);
                    clickedTv.setText(newMeal);
                    if (newTextColor != 0){
                        clickedTv.setTextColor(newTextColor);
                    }
                    if (newBackgroundColor != 0){
                        clickedTv.setBackgroundColor(newBackgroundColor);
                    }
                } else if (result.getResultCode() == RESULT_CANCELED) {
                    noResult();
                }
            }
        });
    }

    private void noResult() {
        Toast.makeText(this,"No change in the meal schedule", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        clickedTv = (TextView)view;
        Intent intent = new Intent(this, ChangeActivity.class);
        intent.putExtra("meal", clickedTv.getText().toString());
        activityResultLauncher.launch(intent);
    }
}