package com.example.androidactivities.caloriecount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.androidactivities.caloriecount.dayOneInterface;

public class dayOneFood extends AppCompatActivity {

    userInfoDatabaseHandler databaseHandler; // Declaring an object here will allow to use this is all methods withing this class
    EditText inputFoodType;
    EditText inputCalorie;
    EditText inputAmount;

    public void onClickHome(View view) {
        Intent transitionHome= new Intent(dayOneFood.this,MainActivity.class);
        startActivity(transitionHome);

    }
    public void onClickBack(View view) {
        Intent transitionBack= new Intent(dayOneFood.this,dayOneInterface.class);
        startActivity(transitionBack);

        dayOneInterface day=new dayOneInterface();
    }
    // When this button is pressed, that particular 'FoodType' is removed from the database//
    // You just need to specify the 'FoodType' in this case and then it will automatically take out the whole row associated with that parameter


    public void onClickAddFood(View view) {
        inputFoodType=(EditText)findViewById(R.id.inputFoodType); // Connects the button to the text
        inputCalorie=(EditText)findViewById(R.id.inputCalorie);
        inputAmount=(EditText)findViewById(R.id.inputAmount);

        final double CalorieInput=Double.parseDouble(inputCalorie.getText().toString());
        final double CalorieAmount=Double.parseDouble(inputAmount.getText().toString());

        //CALCULATES THE AMOUNT OF CALORIES CONSUMED BY EATING THAT SPECIFIC FOOD ITEM//
        double calculateCalorieConsumed=(CalorieInput/100.0)*CalorieAmount; // Test value for calorie consumed. This will be calculated and populated appropriately

        //--------VALUES CONVERSION DATABASE ----------------//
        String foodItemName=inputFoodType.getText().toString();
        String foodCaloriePerHndrG=inputCalorie.getText().toString();
        String foodAmountConsmd=inputAmount.getText().toString();
        String calculateCalorieConsumed_String=String.format("%.2f", calculateCalorieConsumed); // Converts the double to a String and restricts to 2 decimal places only
        //------------------------------------------------//

        //--------PASSING THE VALUES TO BE STORED IN THE DATABASE-----------//
        //Stores the data as an object and passess the input values from the user into the database. Creates the database using constructor
        UserFoodInformation userinfo=new UserFoodInformation(foodItemName,foodCaloriePerHndrG,foodAmountConsmd,calculateCalorieConsumed_String);
        databaseHandler.addFoodItem(userinfo); // An object is passed in this method as described in 'UserFoodInformation' class
        // To clear the input added by the user
        inputFoodType.setText("");
        inputCalorie.setText("");
        inputAmount.setText("");

        //printDatabase();
    }

    public void onClickDeleteFood(View view) {
        inputFoodType=(EditText)findViewById(R.id.inputFoodType);
        String inputText=inputFoodType.getText().toString();
        databaseHandler.deleteFoodItem(inputText);
        inputFoodType.setText("");
        //printDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_one_food);
        databaseHandler=new userInfoDatabaseHandler(this,null,null,5);
        //printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = databaseHandler.databaseToString();
        TextView txtCalorie=(TextView)findViewById(R.id.txtCalorie);

        txtCalorie.setText(dbString);
        inputFoodType.setText("");
        inputCalorie.setText("");
        inputAmount.setText("");
    }

}
