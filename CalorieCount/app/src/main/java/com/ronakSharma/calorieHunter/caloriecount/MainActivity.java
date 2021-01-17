package com.ronakSharma.calorieHunter.caloriecount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

////https://material.io/design/color/#tools-for-picking-colors

public class MainActivity extends AppCompatActivity {

    userInfoDatabaseHandler databaseHandler;

    // A new feature was added in the '.xml' file of this activity, called android: onClick
    // Describing that feature there, allowed to use the following method
    // This method transitions between activities when the 'onClick' feature is used (which is when button 1 is pressed)
    public void onClick(View view) { // Using this you do not have to call this in the 'onCreate function'

        Intent transitionOne= new Intent(this,PageOne.class);
        startActivity(transitionOne);
    }
    // This method permanently deletes that particular preference. In this case the 'Target Value', when pressed
    public void onClearHistory(View view) {

        SharedPreferences settings = getSharedPreferences("userDailyTarget", Context.MODE_PRIVATE);
        SharedPreferences netCalorieDaily = getSharedPreferences("netCalorieConsumed", Context.MODE_PRIVATE);
        SharedPreferences totalCalorieDaily = getSharedPreferences("totalCalorieConsumed", Context.MODE_PRIVATE);

        databaseHandler.onDelete();
        settings.edit().remove("Target_Day1").commit();// Delete the target preference
        settings.edit().remove("Target_Day2").commit();// Delete the target preference
        settings.edit().remove("Target_Day3").commit();// Delete the target preference
        settings.edit().remove("Target_Day4").commit();// Delete the target preference
        settings.edit().remove("Target_Day5").commit();// Delete the target preference
        settings.edit().remove("Target_Day6").commit();// Delete the target preference
        settings.edit().remove("Target_Day7").commit();// Delete the target preference
        netCalorieDaily.edit().remove("NetCalorieDay1").commit();
        netCalorieDaily.edit().remove("NetCalorieDay2").commit();
        netCalorieDaily.edit().remove("NetCalorieDay3").commit();
        netCalorieDaily.edit().remove("NetCalorieDay4").commit();
        netCalorieDaily.edit().remove("NetCalorieDay5").commit();
        netCalorieDaily.edit().remove("NetCalorieDay6").commit();
        netCalorieDaily.edit().remove("NetCalorieDay7").commit();

        totalCalorieDaily.edit().remove("TotalCalorieDay1").commit();
        totalCalorieDaily.edit().remove("TotalCalorieDay2").commit();
        totalCalorieDaily.edit().remove("TotalCalorieDay3").commit();
        totalCalorieDaily.edit().remove("TotalCalorieDay4").commit();
        totalCalorieDaily.edit().remove("TotalCalorieDay5").commit();
        totalCalorieDaily.edit().remove("TotalCalorieDay6").commit();
        totalCalorieDaily.edit().remove("TotalCalorieDay7").commit();

        Toast.makeText(this, "Successfully deleted history", Toast.LENGTH_SHORT).show();
    }


    public void onClickGraphButton(View view) {
        Intent transitionToWeeklyCalorie= new Intent(this,GraphNetCalorie.class);
        startActivity(transitionToWeeklyCalorie);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler=new userInfoDatabaseHandler(this,null,null,5);
    }

}











/*------------EXTRA METHODS FOR LEARNING PURPOSES--------------*/
/*


//--------------METHOD -1----------//

// This method transitions from Main Activity to Page One activity. However this method is too long and so commented out
    public void firstTransition(){
        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transitionOne= new Intent(MainActivity.this,PageOne.class);
                startActivity(transitionOne);
            }
        }
        );

    }






*/

// NEEDS TO GO WITHIN THE ONCREATE FUNCTION
//----------INITIALISATION OF PARAMETERS---------------//
        /*
        SharedPreferences netCalorieDaily = getSharedPreferences("netCalorieConsumed", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = netCalorieDaily.edit();
        String grossCalories_String="0";
        if (username != null && password != null )
{
    //username and password are present, do your stuff
}
        editorTarget.putString("NetCalorieDay1", grossCalories_String);
        editorTarget.putString("NetCalorieDay2", grossCalories_String);
        editorTarget.putString("NetCalorieDay3", grossCalories_String);
        editorTarget.putString("NetCalorieDay4", grossCalories_String);
        editorTarget.putString("NetCalorieDay5", grossCalories_String);
        editorTarget.putString("NetCalorieDay6", grossCalories_String);
        editorTarget.putString("NetCalorieDay7", grossCalories_String);
        editorTarget.apply();
        */
//----------------------------------------------------//