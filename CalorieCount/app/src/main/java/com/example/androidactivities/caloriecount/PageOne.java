package com.example.androidactivities.caloriecount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//Here the implements part is added to use the library/android resource file for the purpose of using spinners only
public class PageOne extends AppCompatActivity implements AdapterView.OnItemSelectedListener { //To define the selection event handler, 'onitemselect..' for a spinner widget

    //---------------------------SPINNER---------------------------------------------------//
    public void onClickSpinnerDayNumber(){

        //--------Initialising the dropdown menu to define its configuration----------//
        Spinner spnrDayNumber = (Spinner) findViewById(R.id.spnrDayNumber);
        // The adaptor stores the string that goes into a spinner (a list in this case). It uses an android build file (...list_item_1) and uses the array of items we created in 'strings.xml' file as input(this is argument 3 below)
        ArrayAdapter<String> myAdaptor= new ArrayAdapter<String>(PageOne.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.Day_Number));

        myAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Specify the layout to use when the list of choices appears. This causes our spinner to be displayed in a list view
        spnrDayNumber.setAdapter(myAdaptor);// Apply the adapter to the spinner
        spnrDayNumber.setOnItemSelectedListener(this);
        //------------------------------//
    }


    // Stores the value in database, under a file/database name 'userinfo', type 'Private' and key value
    // 'target' (stores the input in the textbox)

    @Override //--Function running in background to detect the value selected by user from drop down-----//
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {  //'Position' is the element's position in that dropdown menu

        String daySelected=parent.getItemAtPosition(position).toString(); // An item was selected. You can retrieve the selected item using
        EditText inputTarget= (EditText)findViewById(R.id.inputTarget);
        String target=inputTarget.getText().toString();
        Integer Target_Length =  target.trim().length(); // Stores the length of entered value. An integer

        SharedPreferences dailyTarget = getSharedPreferences("userDailyTarget", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = dailyTarget.edit();
     //--THIS CASES IDENTIFIES WHICH SPINNER ITEM USER SELECTED AND THEN STORES THE TARGET VALUE IN A SPECIFIC LOCATION DEPENDING ON WHAT WAS SELECTED---//
        //NOTE: This works ONLY WHEN THE USER FIRST ENTERS THE NUMBER AND THEN CHOOSES THE SPINNER CLASS. FIX THIS !!!-----------//
        switch (daySelected) {
            case "Day1":
                editorTarget.putString("Target_Day1", inputTarget.getText().toString());
                break;
            case "Day2":
                editorTarget.putString("Target_Day2", inputTarget.getText().toString());
                break;
            case "Day3":
                editorTarget.putString("Target_Day3", inputTarget.getText().toString());
                break;
            case "Day4":
                editorTarget.putString("Target_Day4", inputTarget.getText().toString());
                break;
            case "Day5":
                editorTarget.putString("Target_Day5", inputTarget.getText().toString());
                break;
            case "Day6":
                editorTarget.putString("Target_Day6", inputTarget.getText().toString());
                break;
            case "Day7":
                editorTarget.putString("Target_Day7", inputTarget.getText().toString());
                break;
            default:
                break;
        }
        editorTarget.apply();
        editorTarget.commit();
        /*
        //--------TEST--------//
        Intent transfer = new Intent(PageOne.this, userInfoDatabaseHandler.class);
        transfer.putExtra("VideoID", "HELLO");
        startActivity(transfer);
        //-------------------//
*/

        //---------------------------------------------//
    }



    public void onClickDay1(View view) {
        Intent transitionTwo= new Intent(PageOne.this,dayOneInterface.class);
        startActivity(transitionTwo);

        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = selectedDay.edit();
        editorTarget.putString("DaySelected", "Day1");
        editorTarget.apply();

    }
    public void onClickDay2(View view) {

        Intent transitionTwo= new Intent(PageOne.this,dayOneInterface.class);
        startActivity(transitionTwo);

        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = selectedDay.edit();
        editorTarget.putString("DaySelected", "Day2");
        editorTarget.apply();

    }
    public void onClickDay3(View view) {
        Intent transitionTwo= new Intent(PageOne.this,dayOneInterface.class);
        startActivity(transitionTwo);

        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = selectedDay.edit();
        editorTarget.putString("DaySelected", "Day3");
        editorTarget.apply();
    }
    public void onClickDay4(View view) {
        Intent transitionTwo= new Intent(PageOne.this,dayOneInterface.class);
        startActivity(transitionTwo);

        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = selectedDay.edit();
        editorTarget.putString("DaySelected", "Day4");
        editorTarget.apply();
    }
    public void onClickDay5(View view) {
        Intent transitionTwo= new Intent(PageOne.this,dayOneInterface.class);
        startActivity(transitionTwo);

        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = selectedDay.edit();
        editorTarget.putString("DaySelected", "Day5");
        editorTarget.apply();
    }
    public void onClickDay6(View view) {
        Intent transitionTwo= new Intent(PageOne.this,dayOneInterface.class);
        startActivity(transitionTwo);

        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = selectedDay.edit();
        editorTarget.putString("DaySelected", "Day6");
        editorTarget.apply();
    }
    public void onClickDay7(View view) {
        Intent transitionTwo= new Intent(PageOne.this,dayOneInterface.class);
        startActivity(transitionTwo);

        SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTarget = selectedDay.edit();
        editorTarget.putString("DaySelected", "Day7");
        editorTarget.apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_one);
        getTargetValue_Day1();
        onClickSpinnerDayNumber();

    }

    public void getTargetValue_Day1(){
/*
        SharedPreferences target_value = getSharedPreferences("userDailyTarget", Context.MODE_PRIVATE);
        String target = target_value.getString("Target_Day1", "");
        Integer Target_Length =  target.trim().length(); // Stores the length of entered value. An integer


        // If the text box is empty then show the 'Toast'
        if (Target_Length!=0)
            Toast.makeText(this, "You have already entered a Target for Day 1", Toast.LENGTH_LONG).show();
    */
    }
    @Override //--Function to take action when nothing is selected. Left empty at this stage---//
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

/*------------EXTRA METHODS FOR LEARNING PURPOSES--------------*/

//-----------1------------//
// POP -UP MESSAGES
//    Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show(); // To show pop up messages


//-----2: Decided to use 'Preferenece' over using 'Intent' to transfer data as it stores data in somewhat of a
// database. Using Intent I was loosing information when moving between activities or refreshing the application



//---------3---------//
//  Integer Target_Length =  inputTarget.getText().toString().trim().length(); // Stores the length of entered value. An integer
//String Target =  inputTarget.getText().toString(); // Stores the text entered


/*
        if (Target_Length!=0){ // If the text box is not empty then print the value (FOR TESTING ONLY)
            txtTest.setText(Target);

        }

        else if (Target_Length==0){ // If the textbox is empty, then print this  (FOR TESTING ONLY)
            txtTest.setText("Better Luck next time");

        }
        else // Else do nothing (This is redundant and can replace the above if condition)
        {}
 */ //-------FOR TESTING ONLY-------//

//-----Storing the Day selected by the user-----------//
// SharedPreferences selectedDay = getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
//SharedPreferences.Editor editorTarget = selectedDay.edit();


// For transitioning to the next page. Uses Intent function to move from 'pageone' to 'dayoneInterface'
// This actions is only performed when a button is pressed
// This then later needs to be called in the main 'onCreate' function so it is running in the background
// always and waiting to listen for the user input