package com.ronakSharma.calorieHunter.caloriecount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CalorieResults extends AppCompatActivity {

    @Override
    //--DISPLAYS THE TOAST, DATA AND OUTCOME BASED ON THE ANALYSIS PERFORMED IN CALORIEBMI CLASS--//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_results);

        SharedPreferences averageCalorieConsumed = getSharedPreferences("CaloriePredictions", Context.MODE_PRIVATE);
        String weightDecision = averageCalorieConsumed.getString("weightDecision", "Invalid");
        String BMIValue = averageCalorieConsumed.getString("BMIValue", "1nvalid");
        String BMRMaintain = averageCalorieConsumed.getString("BMRMaintain", "Invalid");
        String BMRLooseWeight = averageCalorieConsumed.getString("BMRLooseWeight", "Invalid");
        String BMRGainWeight = averageCalorieConsumed.getString("BMRGainWeight", "Invalid");



        TextView txtBMI=(TextView)findViewById(R.id.txtBMI);
        TextView txtBMR=(TextView)findViewById(R.id.txtBMR);
        TextView txtBMRGain=(TextView)findViewById(R.id.txtBMRGain);
        TextView txtLooseBMR=(TextView)findViewById(R.id.txtLooseBMR);
        TextView txtWeightDecision=(TextView)findViewById(R.id.txtWeightDecision);


        if(Integer.valueOf(weightDecision)==1)
        {
            txtWeightDecision.setText("You have managed to maintain your weight. Doing Well ");
        }
        else if (Integer.valueOf(weightDecision)==2)
        {
            txtWeightDecision.setText("Expected to loose 0.5~1 Kg this week");

        }
        else if(Integer.valueOf(weightDecision)==3)
        {
            txtWeightDecision.setText("Expected to gain 0.5~1 Kg this week");

        }
        else
        {
            txtWeightDecision.setText("Waiting for results....");

        }

        txtBMI.setText(BMIValue);
        txtBMR.setText(BMRMaintain + " calories");
        txtLooseBMR.setText(BMRLooseWeight+" calories");
        txtBMRGain.setText(BMRGainWeight+" calories");
    }

    public void onClickHome(View view) {
        Intent transitionToMainActivity= new Intent(this,MainActivity.class);
        startActivity(transitionToMainActivity);

    }
}
