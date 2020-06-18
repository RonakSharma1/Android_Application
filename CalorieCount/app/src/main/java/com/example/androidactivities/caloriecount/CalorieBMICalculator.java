package com.example.androidactivities.caloriecount;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalorieBMICalculator extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;

    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText editAge;
    EditText editHeight;
    EditText editWeight;
    String Gender="Male";

    //------------CALCULATES BMI VALUE AND PREDICTS IF WEIGHT GAIN OR LOSS-------------//
    @TargetApi(Build.VERSION_CODES.O)
    public void onClickSubmit(View view) {

        double BMR=0;
        int weightDecision=1;
        editAge = (EditText) (findViewById(R.id.editAge));
        editHeight = (EditText) (findViewById(R.id.editHeight));
        editWeight = (EditText) (findViewById(R.id.editWeight));

        final double doubleAge = Double.parseDouble(editAge.getText().toString());
        final double doubleHeight = Double.parseDouble(editHeight.getText().toString());
        final double doubleWeight = Double.parseDouble(editWeight.getText().toString());

        //-----------BMR CALCULATION-----------//
        if(Gender=="Male"){
            BMR= (10*doubleWeight)+(6.25*doubleHeight) - (5*doubleAge) + 5;
        }
        else
        {
            BMR= (10*doubleWeight)+(6.25*doubleHeight) - (5*doubleAge) - 161;
        }

        //----------BMI STORING------------//
        int BMI= (int)((doubleWeight)/((doubleHeight/100)*(doubleHeight/100)));
        SharedPreferences averageCalorieConsumed = getSharedPreferences("AverageCalorieConsumed", Context.MODE_PRIVATE);
        String averageWeeklyCalorie = averageCalorieConsumed.getString("weeklyAverageCalorie", "0");

        int integerBMR=(int) BMR;
        float floataverageWeeklyCalorie=Float.valueOf(averageWeeklyCalorie);
        int integeraverageWeeklyCalorie=(int)floataverageWeeklyCalorie;
        int looseWeightBMR=integerBMR-480;
        int gainWeightBMR=integerBMR+480;

        //-----------PREDICTING WEIGHT GAIN OR LOSS----------//
        if((integeraverageWeeklyCalorie<(integerBMR+200)) && (integeraverageWeeklyCalorie>(integerBMR-200)))
        {
            weightDecision=1;
        }

        else if(integeraverageWeeklyCalorie<looseWeightBMR)
        {
            weightDecision=2;

        }
        else if(integeraverageWeeklyCalorie>gainWeightBMR)
        {
            weightDecision=3;

        }
        else
        {
            weightDecision=1;
        }


        //---------RESULTS SEND TO THE NEXT ACTIVITY----------//
        SharedPreferences caloriePredictions = getSharedPreferences("CaloriePredictions", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorCalorie = caloriePredictions.edit();
        editorCalorie.putString("weightDecision", String.valueOf(weightDecision));
        editorCalorie.putString("BMIValue",String.valueOf(BMI));
        editorCalorie.putString("BMRMaintain",String.valueOf(integerBMR));
        editorCalorie.putString("BMRLooseWeight",String.valueOf(integerBMR-500));
        editorCalorie.putString("BMRGainWeight",String.valueOf(integerBMR+500));
        editorCalorie.apply();
        //Log.i("*************", String.valueOf(BMI));
        //Log.i("@@@@@@@@@@@@", String.valueOf(integerBMR));



        //-----------------NOTIFICATION----------------//
/*

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //------------------------CREATING A CHANNEL------------------------//
        String id = "CHANNEL_01";        // The id of the channel.
        CharSequence name = "CHANNEL_01";  // The user-visible name of the channel
        String description = "notification";  // The user-visible description of the channel


        int importance = NotificationManager.IMPORTANCE_LOW;

        NotificationChannel mChannel = new NotificationChannel(id, name,importance);

        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        mNotificationManager.createNotificationChannel(mChannel);
        //----------------------------------------------------------------//


        //  -   -   -   -CREATING NOTIFICATIONS -   -   -   -   //
        //Build the notification
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Weekly Calorie Consumption");
        notification.setContentText("Your weekly average is at " + String.valueOf(integeraverageWeeklyCalorie) + " calories");
        notification.setChannelId("CHANNEL_01");

        Intent transitionToMain = new Intent(this, GraphNetCalorie.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, transitionToMain, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Builds notification and issues it
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());

        //  -   -   -   -   -   -   -   -   -   -   -   -   -//

        */
        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,30,40);
        TextView tv=new TextView(CalorieBMICalculator.this);

        tv.setBackgroundColor(Color.MAGENTA);
        tv.setPadding(10,10,10,10);
        tv.setTextColor(Color.WHITE);
        toast.setDuration(Toast.LENGTH_LONG);
        tv.setTextSize(16);
        tv.setText("Average Calorie Consumption is: "+String.valueOf(integeraverageWeeklyCalorie));
        toast.setView(tv);
        toast.show();

/*
        Toast.makeText(this, "Average Calorie Consumed: "+String.valueOf(integeraverageWeeklyCalorie), Toast.LENGTH_LONG).show;

*/

        Intent transitionToCalorieResults= new Intent(this,CalorieResults.class);
        startActivity(transitionToCalorieResults);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_bmicalculator);
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

    }

    //--- Method to check which radio button is pressed -----//
    public void onRadioButtonClicked(View view) {

        radioGroup=findViewById(R.id.radioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()) {
            case R.id.radio_one:
                if (checked)
                    Gender="Male";
                break;
            case R.id.radio_two:
                if (checked)
                    Gender="Female";
                break;
        }
    }

}
