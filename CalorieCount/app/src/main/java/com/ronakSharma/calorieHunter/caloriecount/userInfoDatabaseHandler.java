package com.ronakSharma.calorieHunter.caloriecount;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


public class userInfoDatabaseHandler extends SQLiteOpenHelper {

    //---------------DATABASE VERSION------------//
    private static final int DATABASE_VERSION=5; // 1 because creating the database for the first time

    //--------------DATABASE----------//
    private static final String DATABASE_NAME="UserFoodInformation.db";

    //-----------TABLES---------//
    private static final String TABLE_DAYONE_TESTV3="DayOneInformationTest_V3";
    private static final String TABLE_DAYTWO_TESTV3="DayTwoInformationTest_V3";
    private static final String TABLE_DAYTHREE_TESTV3="DayThreeInformationTest_V3";
    private static final String TABLE_DAYFOUR_TESTV3="DayFourInformationTest_V3";
    private static final String TABLE_DAYFIVE_TESTV3="DayFiveInformationTest_V3";
    private static final String TABLE_DAYSIX_TESTV3="DaySixInformationTest_V3";
    private static final String TABLE_DAYSEVEN_TESTV3="DaySevenInformationTest_V3";


    //--------COLUMNS IN THE TABLE OF THE DATABASE----------//
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_FOODNAME="FoodName"; // These rows are declared as string irrespective if they store integers as these are column labels
    private static final String COLUMN_CALPERHNDGRM="CalorieHnrdG";
    private static final String COLUMN_AMOUNT="AmountConsumed";
    private static final String COLUMN_CALORIECONSUMED="CalorieConsumed";


    public userInfoDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION); // Context and factory are left default
    }


    //--------CREATING A SINGLE DATABASE WITH MULTIPLE TABLES FOR EACH DAY-----------//
    @Override
    public void onCreate(SQLiteDatabase db) {


        //----------Create tables for each day in the database----------//
        String queryDay1 = "CREATE TABLE " + TABLE_DAYONE_TESTV3 + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT," + COLUMN_CALPERHNDGRM + " TEXT," +
                COLUMN_AMOUNT + " TEXT," + COLUMN_CALORIECONSUMED + " TEXT " +
                ");";

        String queryDay2 = "CREATE TABLE " + TABLE_DAYTWO_TESTV3 + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT," + COLUMN_CALPERHNDGRM + " TEXT," +
                COLUMN_AMOUNT + " TEXT," + COLUMN_CALORIECONSUMED + " TEXT " +
                ");";

        String queryDay3 = "CREATE TABLE " + TABLE_DAYTHREE_TESTV3 + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT," + COLUMN_CALPERHNDGRM + " TEXT," +
                COLUMN_AMOUNT + " TEXT," + COLUMN_CALORIECONSUMED + " TEXT " +
                ");";

        String queryDay4 = "CREATE TABLE " + TABLE_DAYFOUR_TESTV3 + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT," + COLUMN_CALPERHNDGRM + " TEXT," +
                COLUMN_AMOUNT + " TEXT," + COLUMN_CALORIECONSUMED + " TEXT " +
                ");";

        String queryDay5 = "CREATE TABLE " + TABLE_DAYFIVE_TESTV3 + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT," + COLUMN_CALPERHNDGRM + " TEXT," +
                COLUMN_AMOUNT + " TEXT," + COLUMN_CALORIECONSUMED + " TEXT " +
                ");";

        String queryDay6 = "CREATE TABLE " + TABLE_DAYSIX_TESTV3 + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT," + COLUMN_CALPERHNDGRM + " TEXT," +
                COLUMN_AMOUNT + " TEXT," + COLUMN_CALORIECONSUMED + " TEXT " +
                ");";

        String queryDay7 = "CREATE TABLE " + TABLE_DAYSEVEN_TESTV3 + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT," + COLUMN_CALPERHNDGRM + " TEXT," +
                COLUMN_AMOUNT + " TEXT," + COLUMN_CALORIECONSUMED + " TEXT " +
                ");";

        db.execSQL(queryDay1); // Execute the SQL query that is created above
        db.execSQL(queryDay2);
        db.execSQL(queryDay3);
        db.execSQL(queryDay4);
        db.execSQL(queryDay5);
        db.execSQL(queryDay6);
        db.execSQL(queryDay7);

    }

    //----DELETES THE DATA INSIDE ALL TABLES-----//
    public void onDelete(){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DAYONE_TESTV3);
        db.execSQL("DELETE FROM " + TABLE_DAYTWO_TESTV3);
        db.execSQL("DELETE FROM " + TABLE_DAYTHREE_TESTV3);
        db.execSQL("DELETE FROM " + TABLE_DAYFOUR_TESTV3);
        db.execSQL("DELETE FROM " + TABLE_DAYFIVE_TESTV3);
        db.execSQL("DELETE FROM " + TABLE_DAYSIX_TESTV3);
        db.execSQL("DELETE FROM " + TABLE_DAYSEVEN_TESTV3);
        db.close();//Close database

    }

    //-----ADDS A NEW ENTRY TO THE DATABASE--------//
    public void addFoodItem(UserFoodInformation foodItem) {
        ContentValues values=new ContentValues();

        //------Populating the Table in the Database with user inputs------//
        values.put(COLUMN_FOODNAME,foodItem.getFoodName()); // Column inerted with the Foodname value
        values.put(COLUMN_CALPERHNDGRM,foodItem.getCaloriePerHndrdGram()); // Column inserted with the calorie value
        values.put(COLUMN_AMOUNT,foodItem.getAmountConsumed()); // Column inerted with the Foodname value
        values.put(COLUMN_CALORIECONSUMED,foodItem.getCalorieConsumed()); // Column inerted with the Foodname value
        //-----------------------------------------//

        SQLiteDatabase db=getWritableDatabase(); // Creating a SQL database object to perform the function below of inserting values in Table

        //-----------------------USING SHARED PREFERENCE IN NON-ACTIVITY CLASS----------------------//
        Context applicationContext = dayOneInterface.getContextOfApplication();
        SharedPreferences selectedDay = applicationContext.getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        String daySelected = selectedDay.getString("DaySelected", "Day1");

        //--------SELECTING THE TABLE TO WHICH INFORMATION WILL BE STORED DEPENDING ON WHICH DAY THE USER HAS SELECTED-------------//

        switch (daySelected) {
            case "Day1":
                db.insert(TABLE_DAYONE_TESTV3,null,values); // Inserting the value into the Table in the database
                db.close();//Close database
                break;

            case "Day2":
                db.insert(TABLE_DAYTWO_TESTV3,null,values); // Inserting the value into the Table in the database
                db.close();//Close database
                break;

            case "Day3":
                db.insert(TABLE_DAYTHREE_TESTV3,null,values); // Inserting the value into the Table in the database
                db.close();//Close database
                break;

            case "Day4":
                db.insert(TABLE_DAYFOUR_TESTV3,null,values); // Inserting the value into the Table in the database
                db.close();//Close database
                break;

            case "Day5":
                db.insert(TABLE_DAYFIVE_TESTV3,null,values); // Inserting the value into the Table in the database
                db.close();//Close database
                break;

            case "Day6":
                db.insert(TABLE_DAYSIX_TESTV3,null,values); // Inserting the value into the Table in the database
                db.close();//Close database
                break;

            case "Day7":
                db.insert(TABLE_DAYSEVEN_TESTV3,null,values); // Inserting the value into the Table in the database
                db.close();//Close database
                break;
            default:
                break;

        }

        //------------------TESTING SHARED PREFERENCE WORKING IN NON-ACTIVITY CLASS------------------//
/*
        db.insert(TABLE_DAYONE_TESTV3,null,values); // Inserting the value into the Table in the database
        db.close();//Close database

        Log.i("dslkflsdnfldsnflskdfn", "sdfdsfiudsgfidsgfsdgf");
        Log.i("******PLEASEWORK******", daySelected);
        //Log.i("CANYOUHEARME!!!", daySelected);

*/
    }

    //--------DELETES A SPECIFIC ROW/VALUE FROM THE DATABASE-----------//
    //Delete a user entry from the database. This deletes the information about the particular food item
    // they type in the text box. This is the power of database
    public void deleteFoodItem(String foodDelete){
        SQLiteDatabase db=getWritableDatabase();
        //-----------------------USING SHARED PREFERENCE IN NON-ACTIVITY CLASS----------------------//
        Context applicationContext = dayOneInterface.getContextOfApplication();
        SharedPreferences selectedDay = applicationContext.getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        String daySelected = selectedDay.getString("DaySelected", "Day1");
        //-----------------------------------------------------------------------------------------//

        switch (daySelected) {
            case "Day1":
                db.execSQL("DELETE FROM " + TABLE_DAYONE_TESTV3 + " WHERE " + COLUMN_FOODNAME + "=\"" + foodDelete + "\";");
                db.close();//Close database
                break;

            case "Day2":
                db.execSQL("DELETE FROM " + TABLE_DAYTWO_TESTV3 + " WHERE " + COLUMN_FOODNAME + "=\"" + foodDelete + "\";");
                db.close();//Close database
                break;

            case "Day3":
                db.execSQL("DELETE FROM " + TABLE_DAYTHREE_TESTV3 + " WHERE " + COLUMN_FOODNAME + "=\"" + foodDelete + "\";");
                db.close();//Close database
                break;

            case "Day4":
                db.execSQL("DELETE FROM " + TABLE_DAYFOUR_TESTV3 + " WHERE " + COLUMN_FOODNAME + "=\"" + foodDelete + "\";");
                db.close();//Close database
                break;

            case "Day5":
                db.execSQL("DELETE FROM " + TABLE_DAYFIVE_TESTV3 + " WHERE " + COLUMN_FOODNAME + "=\"" + foodDelete + "\";");
                db.close();//Close database
                break;

            case "Day6":
                db.execSQL("DELETE FROM " + TABLE_DAYSIX_TESTV3 + " WHERE " + COLUMN_FOODNAME + "=\"" + foodDelete + "\";");
                db.close();//Close database
                break;

            case "Day7":
                db.execSQL("DELETE FROM " + TABLE_DAYSEVEN_TESTV3 + " WHERE " + COLUMN_FOODNAME + "=\"" + foodDelete + "\";");
                db.close();//Close database
                break;

            default:
                break;

        }

    }


    // Print database
    public String databaseToString(){
        /*
        String Column_Food="";
        String Column_CaloriePer="";
        String Column_Amount="";
        String Column_CalorieConsumed="";
*/
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();

        //Log.i("*******HLEELE*********", userDaySelected);

        String query="";
        Context applicationContext = dayOneInterface.getContextOfApplication();
        SharedPreferences selectedDay = applicationContext.getSharedPreferences("userDaySelected", Context.MODE_PRIVATE);
        String daySelected = selectedDay.getString("DaySelected", "Day1");

        switch (daySelected) {

            case "Day1":
                query= " SELECT * FROM " + TABLE_DAYONE_TESTV3 + " WHERE 1 "; // 'Select..' select all the columns in the table, 'WHERE ..' -  Select all the information int the row
                break;
            case "Day2":
                query= " SELECT * FROM " + TABLE_DAYTWO_TESTV3 + " WHERE 1 "; // 'Select..' select all the columns in the table, 'WHERE ..' -  Select all the information int the row
                break;

            case "Day3":
                query= " SELECT * FROM " + TABLE_DAYTHREE_TESTV3 + " WHERE 1 "; // 'Select..' select all the columns in the table, 'WHERE ..' -  Select all the information int the row
                break;

            case "Day4":
                query= " SELECT * FROM " + TABLE_DAYFOUR_TESTV3 + " WHERE 1 "; // 'Select..' select all the columns in the table, 'WHERE ..' -  Select all the information int the row
                break;

            case "Day5":
                query= " SELECT * FROM " + TABLE_DAYFIVE_TESTV3 + " WHERE 1 "; // 'Select..' select all the columns in the table, 'WHERE ..' -  Select all the information int the row
                break;

            case "Day6":
                query= " SELECT * FROM " + TABLE_DAYSIX_TESTV3 + " WHERE 1 "; // 'Select..' select all the columns in the table, 'WHERE ..' -  Select all the information int the row
                break;

            case "Day7":
                query= " SELECT * FROM " + TABLE_DAYSEVEN_TESTV3 + " WHERE 1 "; // 'Select..' select all the columns in the table, 'WHERE ..' -  Select all the information int the row
                break;

            default:
                break;

        }


        //Curson points to a location in memory
        Cursor cursor=db.rawQuery(query,null);

        //Move to the first row in the results
        cursor.moveToFirst();


        //Loops through each row in the database and stored in 'dbString'
        while (!cursor.isAfterLast()){

            if(cursor.getString(cursor.getColumnIndex("FoodName"))!=null){

                //--IMPORTANT: There is a delimiter added to the end of the string, because I want to separate this sentence from the next sentence and not have them in one single line,
                // So either add ':' at the end or one in the front to separate lines. Remember '\n' not part of the delimiter, so the sentence is not delimited using that

                // Also the ':' is added in the sentence so that each word can be separated before and after this and then stored in an array
                dbString+=cursor.getString(cursor.getColumnIndex("FoodName"))+":"+ cursor.getString(cursor.getColumnIndex("CalorieHnrdG"))
                +":"+ cursor.getString(cursor.getColumnIndex("AmountConsumed"))+":"+ cursor.getString(cursor.getColumnIndex("CalorieConsumed"))+":"; //'FoodName' is the name of the column through which it loops
                dbString+="\n";
            }

            cursor.moveToNext();
        }
        db.close();
        return dbString;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DAYONE_TESTV3); // Deletes the old table
        onCreate(db); //Create the new table, using the 'onCreate' function
    }
    // Defines the program function, when the database is upgraded, maybe like more columns etc or add data

}
