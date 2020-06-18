package com.example.androidactivities.caloriecount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


//----------PRINTS THE DATABASE IN A NEW ACTIVITY FOR THE USER TO VIEW-------------//
public class Display_Database extends AppCompatActivity {

    userInfoDatabaseHandler databaseHandler;

    //- Transition to the next activity--//
    public void onClickBack(View view) {
        Intent transitionToDayOneInterface= new Intent(this,dayOneInterface.class);
        startActivity(transitionToDayOneInterface);
    }
    public void onClickDisplayData(View view) {
        displayDatabase();
    }

//-----Displays the database--------//
    public void displayDatabase() {

        TextView txtFoodName = (TextView) findViewById(R.id.txtFoodName);
        TextView txtCaloriePerGram = (TextView) findViewById(R.id.txtCaloriePerGram);
        TextView txtAmountConsumed = (TextView) findViewById(R.id.txtAmountConsumed);
        TextView txtCalorieIntake = (TextView) findViewById(R.id.txtCalorieIntake);
        String FoodName = "";
        String CaloriePerGram = "";
        String AmountConsumed = "";
        String CalorieIntake = "";

        //------ACCESS DATABASE-----------//
        String dbString = databaseHandler.databaseToString();
        String[] separatedDatabase = dbString.split(":"); // Delimits the string received (More info in 'userInfoDatabase' or 'dayOneInterface')
        int separatedDatabaseSize = separatedDatabase.length;

        //--------DISPLAY DATABASE INFORMATION---------//
        //-- This function loops through all the user entries, stores the 0,4,8, element in one array (Food Name), the next 1,5,9 in another array (Calorieper100G) etc----//
        // The loop so iterates every 4 times and each time it iterates, it stores the first input (row) entered by the user and the entries in individual arrays
        for (int i = 0; i <= separatedDatabaseSize-4; i+=4) {

            FoodName+=separatedDatabase[i]; //IMPORTANT: The first one is not added '\n' as this already exists in the printDatabase command to add '\n' at the end, so don't need anymore
            CaloriePerGram+=separatedDatabase[i+1]+"\n";
            AmountConsumed+=separatedDatabase[i+2]+"\n";
            CalorieIntake+=separatedDatabase[i+3]+"\n";
        }

        txtFoodName.setText(FoodName);
        txtCaloriePerGram.setText(CaloriePerGram);
        txtAmountConsumed.setText(AmountConsumed);
        txtCalorieIntake.setText(CalorieIntake);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__database);
        databaseHandler=new userInfoDatabaseHandler(this,null,null,5); // Connects to the right database automatically

    }


}
