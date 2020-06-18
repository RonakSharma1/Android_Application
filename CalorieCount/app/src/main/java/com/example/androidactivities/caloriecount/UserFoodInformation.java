package com.example.androidactivities.caloriecount;

public class UserFoodInformation {

    //---Each variable is a specific column in the database----//
    //Declared to set and get the values to and from the database
    private int _id;
    private String foodName;
    private String caloriePerHndrdGram;
    private String amountConsumed;
    private String calorieConsumed;

    //----------CONSTRUCTOR------------//
    // These are called when file running, to store the values locally within a class for setting and getting purposes
    public UserFoodInformation(String foodName, String caloriePerHndrdGram, String amountConsumed, String calorieConsumed) {
        this.foodName = foodName;
        this.caloriePerHndrdGram = caloriePerHndrdGram;
        this.amountConsumed=amountConsumed;
        this.calorieConsumed=calorieConsumed;
    }

    //------------SETTERS--------------//
    public void set_id(int _id) {
        this._id = _id; //'_' before the id as this variable serves as a primary key. So this makes the variable special in a database
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public void setCaloriePerHndrdGram(String caloriePerHndrdGram) {
        this.caloriePerHndrdGram = caloriePerHndrdGram;
    }
    public void setAmountConsumed(String amountConsumed) {
        this.amountConsumed = amountConsumed;
    }
    public void setCalorieConsumed(String calorieConsumed) {
        this.calorieConsumed = calorieConsumed;
    }

    //----------GETTERS----------//
    public int get_id() {
        return _id;
    }
    public String getFoodName() {
        return foodName;
    }
    public String getCaloriePerHndrdGram() {
        return caloriePerHndrdGram;
    }
    public String getAmountConsumed() {
        return amountConsumed;
    }
    public String getCalorieConsumed() {
        return calorieConsumed;
    }

}
