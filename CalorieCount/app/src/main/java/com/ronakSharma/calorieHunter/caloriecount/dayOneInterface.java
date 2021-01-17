package com.ronakSharma.calorieHunter.caloriecount;

import android.content.ContextWrapper;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;


public class dayOneInterface extends AppCompatActivity {

    userInfoDatabaseHandler databaseHandler;
    public static Context contextOfApplication;
    // Describes the transition between activites using intent function on clicking the button
    public void onClickAddItem(View view) {
        Intent transitionThree= new Intent(dayOneInterface.this,dayOneFood.class);
        startActivity(transitionThree);
        Intent transferDaySelected=new Intent(this,userInfoDatabaseHandler.class);

    }
    public void onClickBack(View view) {
        Intent transitionBack= new Intent(dayOneInterface.this,PageOne.class);
        startActivity(transitionBack);
    }
    public void onClickDisplayDatabase(View view) {
        Intent transitionToDisplayDatabase= new Intent(dayOneInterface.this,Display_Database.class);
        startActivity(transitionToDisplayDatabase);
    }
    public void onClickPieChart(View view) {
        Intent transitionPieChart= new Intent(dayOneInterface.this,PieChartNetCalories.class);
        startActivity(transitionPieChart);
    }


    //-----PRESSING THE UDPATE BUTTON TELLS WHICH DAY USER HAS SELECTED AND GETS THE TARGET VALUE ASSOCIATED----///
    public void onRefresh(View view) {

        TextView txtTarget = (TextView) findViewById(R.id.txtTargetVal);

        //-----Opening the parameters shared by Shared Preferences-----------//
        SharedPreferences target_value = getSharedPreferences("userDailyTarget", Context.MODE_PRIVATE);
        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        String daySelected = selectedDay.getString("DaySelected", "Day1");
        //-------------------------------//

        String target="";

        switch (daySelected) {
            case "Day1":
                target = target_value.getString("Target_Day1", "2000");
                break;
            case "Day2":
                target = target_value.getString("Target_Day2", "2000");
                break;
            case "Day3":
                target = target_value.getString("Target_Day3", "2000");
                break;
            case "Day4":
                target = target_value.getString("Target_Day4", "2000");
                break;
            case "Day5":
                target = target_value.getString("Target_Day5", "2000");
                break;
            case "Day6":
                target = target_value.getString("Target_Day6", "2000");
                break;
            case "Day7":
                target = target_value.getString("Target_Day7", "2000");
                break;
            default:
                break;
        }
        // Returns the string stored in the database 'userinfo' (as using the same object name 'SharedPreferences target_value')
        // To use variable from different database, just create another object connecting to that database
        txtTarget.setText(target);
        CalculateTotalCaloriesDay1();
    }

    //------CALCULATES THE SUM TOTAL CALORIE CONSUMED BY ACCESSING THE DATABASE---------//
    public void CalculateTotalCaloriesDay1(){
        String dbString = databaseHandler.databaseToString();
        TextView txtTotalCalories=(TextView)findViewById(R.id.txtTotalCalories);


        String[] separatedDatabase = dbString.split(":"); // Values received from the database are delimited and stored in this array of unknown length
        // Each word above is stored in individual array. Example s[0]=Pizza, s[1]=100 ... s[4]=Poridge etc, s[5]=500..
        int separatedDatabaseSize = separatedDatabase.length; // Calculating the length of the array, which counts all the total inputs by the user. Each column entry is counted individually
        double CalorieIntake = 0;


        //------Calculating the total calories consumed in Day 1 by adding all values in the database------//
        for (int i = 0; i <= separatedDatabaseSize-4; i+=4) { //The loop stops at '-4' as we iterate this loop increases the 'i' by 4 count
            CalorieIntake+=Double.parseDouble(separatedDatabase[i+3].toString()); // Only takes the calorie consumed in that product and adds them together
        }

        String CalorieIntake_String=String.format("%.2f", CalorieIntake);
        txtTotalCalories.setText(CalorieIntake_String); // Displaying the total calories count

        NetCalorie(CalorieIntake_String);
        //----------------------------------//
    }

    //----CALCULATES THE NETCALORIES AND ALSO STORES THE TOTAL CALORIES AND NET CALORIES---------//
    public void NetCalorie(String calorieConsumed) {
        double calorieTarget;
        double calorieIntake;
        String string_calorieIntake;
        String extraText;
        double grossCalories = 0;


        TextView txtTotalCalories=(TextView)findViewById(R.id.txtTotalCalories);
        TextView txtTargetVal=(TextView)findViewById(R.id.txtTargetVal);
        TextView txtDisplayNetCalories=(TextView)findViewById(R.id.txtDisplayNetCalories);


        calorieTarget= Double.parseDouble(txtTargetVal.getText().toString());
        calorieIntake= Double.parseDouble(txtTotalCalories.getText().toString());
        string_calorieIntake=txtTotalCalories.getText().toString();

        grossCalories=calorieTarget-calorieIntake;


        if(grossCalories<0) {
            grossCalories=0.0;
            extraText="\nOvereaten:-(";
        }
        else {
            extraText="";
        }
        String grossCalories_String=String.format("%.2f", grossCalories);

        //-----------STORING NET CLAORIE FOR EACH DAY USING SHARED PREFERENCES---------------//

        //-----------GETTING INFORMATION ABOUT WHICH DAY DID THE USER SELECT--------------//
        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        String daySelected = selectedDay.getString("DaySelected", "");
        //--------------------                                                  -------//

        //-----------CREATING PREFERENCES TO STORE DATA INTO DIFFERENT AREAS---------//
        SharedPreferences netCalorieDaily = getSharedPreferences("netCalorieConsumed", Context.MODE_PRIVATE);
        SharedPreferences totalCalorieDaily = getSharedPreferences("totalCalorieConsumed", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = netCalorieDaily.edit();
        SharedPreferences.Editor editorcalorie = totalCalorieDaily.edit();

        //-------------------                                                ----//

        switch (daySelected) {
            case "Day1":
                editorTarget.putString("NetCalorieDay1", grossCalories_String);
                editorcalorie.putString("TotalCalorieDay1", string_calorieIntake);
                break;
            case "Day2":
                editorTarget.putString("NetCalorieDay2", grossCalories_String);
                editorcalorie.putString("TotalCalorieDay2", string_calorieIntake);
                break;
            case "Day3":
                editorTarget.putString("NetCalorieDay3", grossCalories_String);
                editorcalorie.putString("TotalCalorieDay3", string_calorieIntake);
                break;
            case "Day4":
                editorTarget.putString("NetCalorieDay4", grossCalories_String);
                editorcalorie.putString("TotalCalorieDay4", string_calorieIntake);
                break;
            case "Day5":
                editorTarget.putString("NetCalorieDay5", grossCalories_String);
                editorcalorie.putString("TotalCalorieDay5", string_calorieIntake);
                break;
            case "Day6":
                editorTarget.putString("NetCalorieDay6", grossCalories_String);
                editorcalorie.putString("TotalCalorieDay6", string_calorieIntake);
                break;
            case "Day7":
                editorTarget.putString("NetCalorieDay7", grossCalories_String);
                editorcalorie.putString("TotalCalorieDay7", string_calorieIntake);
                break;

            default:
                break;
        }
        editorTarget.apply();
        editorcalorie.apply();

        //-----------------------------------------------------------------------------------//

        txtDisplayNetCalories.setText(grossCalories_String+extraText);

        //------Below are the Percentage Calculations used for Pie Chart formation----------//
        String targetDaily= txtTargetVal.getText().toString();
        double percentageCalorieConsumed=(Double.parseDouble(calorieConsumed))*100/(Double.parseDouble(targetDaily));
        if(percentageCalorieConsumed>100) {
            percentageCalorieConsumed=100.0;
        }
        double percentageCalorieLeft=100.00-percentageCalorieConsumed;


        //--Storing in the Shared Preferences so they can be accessed by Pie Chart--//
        SharedPreferences pieChartInformation = getSharedPreferences("pieChartInformation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorCalorie = pieChartInformation.edit();
        editorCalorie.putString("percentageCalorieConsumed", String.format("%.2f", percentageCalorieConsumed));
        editorCalorie.putString("percentageCalorieLeft", String.format("%.2f", percentageCalorieLeft));
        editorCalorie.apply();
        //-------------------------------------------------------------------------------//

    }




    public static Context getContextOfApplication(){
        return contextOfApplication;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_one_interface);
        databaseHandler=new userInfoDatabaseHandler(this,null,null,5); // Connects to the right database automatically
        contextOfApplication = getApplicationContext();

        //-----SETTING DEFAULT VALUES TO THE DAY AND TARGET COLORIE FOR THAT DAY-----------//
        SharedPreferences target_value = getSharedPreferences("userDailyTarget", Context.MODE_PRIVATE);
        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        String daySelected = selectedDay.getString("DaySelected", "Day1");
        //-------------------------------//

        String target="";

        switch (daySelected) {
            case "Day1":
                target = target_value.getString("Target_Day1", "2000");
                break;
            case "Day2":
                target = target_value.getString("Target_Day2", "2000");
                break;
            case "Day3":
                target = target_value.getString("Target_Day3", "2000");
                break;
            case "Day4":
                target = target_value.getString("Target_Day4", "2000");
                break;
            case "Day5":
                target = target_value.getString("Target_Day5", "2000");
                break;
            case "Day6":
                target = target_value.getString("Target_Day6", "2000");
                break;
            case "Day7":
                target = target_value.getString("Target_Day7", "2000");
                break;
            default:
                break;
        }

    }



}





//public String daySelected_User;
//SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
//String daySelected_User = selectedDay.getString("DaySelected", "");