package com.example.androidactivities.caloriecount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieChartNetCalories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_net_calories);

        //--------INFORMATION ENTRY---------//
        SharedPreferences pieChartInformation = getSharedPreferences("pieChartInformation", Context.MODE_PRIVATE);
        String percentageCalorieConsumed = pieChartInformation.getString("percentageCalorieConsumed", "0");
        String percentageCalorieLeft = pieChartInformation.getString("percentageCalorieLeft", "0");

        //--------PIE CHART DATA ENTRY---------//
        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(Float.valueOf(percentageCalorieConsumed), "Calorie Consumed"));
        entries.add(new PieEntry(Float.valueOf(percentageCalorieLeft), "Calorie Left"));

        //-----------DESIGN OF THE PIE CHART--------//
        PieDataSet set = new PieDataSet(entries, "");
        PieData data = new PieData(set);
        pieChart.setDescription(null);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(254,0,0));
        colors.add(Color.rgb(0,254,0));
        set.setColors(colors);
        set.setSliceSpace(2f);
        set.setSelectionShift(10f);
        pieChart.setDrawSliceText(false);
        pieChart.setHoleRadius(20f);
        Legend legend = pieChart.getLegend();
        legend.setTextSize(20f);
        data.setValueTextSize(40f);

        //-----DISPLAYS DATA-----//
        pieChart.setData(data);
        pieChart.invalidate(); // refresh


    }

    public void onClickBack(View view) {
        Intent transitionTodayOneInterface= new Intent(this,dayOneInterface.class);
        startActivity(transitionTodayOneInterface);
    }
}
