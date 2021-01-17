package com.ronakSharma.calorieHunter.caloriecount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LineChartCalories extends AppCompatActivity {


    //------DISPLAYS THE GRAPH AND CALCULATES THE AVERAGE CALORIES CONSUMED FOR THE WHOLE WEEK----//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart_calories);

        SharedPreferences netCalorieDaily = getSharedPreferences("totalCalorieConsumed", Context.MODE_PRIVATE);
        String netCalorieDay1 = netCalorieDaily.getString("TotalCalorieDay1", "0");
        String netCalorieDay2 = netCalorieDaily.getString("TotalCalorieDay2", "0");
        String netCalorieDay3 = netCalorieDaily.getString("TotalCalorieDay3", "0");
        String netCalorieDay4 = netCalorieDaily.getString("TotalCalorieDay4", "0");
        String netCalorieDay5 = netCalorieDaily.getString("TotalCalorieDay5", "0");
        String netCalorieDay6 = netCalorieDaily.getString("TotalCalorieDay6", "0");
        String netCalorieDay7 = netCalorieDaily.getString("TotalCalorieDay7", "0");


        LineChart chart = (LineChart) findViewById(R.id.lineChart);
        List<Entry> valsComp1 = new ArrayList<Entry>();

        Entry c1e1 = new Entry(1f, Float.valueOf(netCalorieDay1)); // 0 == quarter 1
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(2f, Float.valueOf(netCalorieDay2)); // 1 == quarter 2 ...
        valsComp1.add(c1e2);
        Entry c1e3 = new Entry(3f, Float.valueOf(netCalorieDay3)); // 1 == quarter 2 ...
        valsComp1.add(c1e3);
        Entry c1e4 = new Entry(4f, Float.valueOf(netCalorieDay4)); // 1 == quarter 2 ...
        valsComp1.add(c1e4);
        Entry c1e5 = new Entry(5f, Float.valueOf(netCalorieDay5)); // 1 == quarter 2 ...
        valsComp1.add(c1e5);
        Entry c1e6 = new Entry(6f, Float.valueOf(netCalorieDay6)); // 1 == quarter 2 ...
        valsComp1.add(c1e6);
        Entry c1e7 = new Entry(7f, Float.valueOf(netCalorieDay7)); // 1 == quarter 2 ...
        valsComp1.add(c1e7);

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);

        LineData data = new LineData(dataSets);

        chart.setData(data);
        data.setValueTextSize(15f);

        setComp1.setLineWidth(5f);
        setComp1.setColor(Color.GREEN);
        setComp1.setDrawCircles(true);
        setComp1.setCircleRadius(4f);
        setComp1.setCircleColor(Color.BLUE);

        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDoubleTapToZoomEnabled(true);

        //Y-AXIS Settings
        YAxis left = chart.getAxisLeft();
        left.setDrawZeroLine(false); // draw a zero line
        left.setDrawLabels(true); // no axis labels
        left.setTextSize(15f);
        chart.getAxisRight().setEnabled(false); // no right axis
        chart.setDescription(null);



        //X- AXIS Settings
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(15f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);

        chart.getLegend().setEnabled(false);
        chart.setDrawBorders(true);
        int dayNumber=1;

        if(Float.valueOf(netCalorieDay7)==0.0){
            dayNumber=6;
        }
        else{
            dayNumber=7;
        }

        if(Float.valueOf(netCalorieDay6)==0.0){
            dayNumber=5;
        }
        if(Float.valueOf(netCalorieDay5)==0.0){
            dayNumber=4;
        }
        if(Float.valueOf(netCalorieDay4)==0.0){
            dayNumber=3;
        }
        if(Float.valueOf(netCalorieDay3)==0.0){
            dayNumber=2;
        }
        if(Float.valueOf(netCalorieDay2)==0.0){
            dayNumber=1;
        }

        //--------CALCULATING THE AVERAGE CALORIE CONSUMED IN WEEK----------//
        Float weeklyAverageCalorie = (Float.valueOf(netCalorieDay1)+Float.valueOf(netCalorieDay2)+Float.valueOf(netCalorieDay3)+Float.valueOf(netCalorieDay4)+Float.valueOf(netCalorieDay5)+Float.valueOf(netCalorieDay6)+Float.valueOf(netCalorieDay7))/dayNumber;
        SharedPreferences averageCalorieConsumed = getSharedPreferences("AverageCalorieConsumed", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorCalorie = averageCalorieConsumed.edit();
        editorCalorie.putString("weeklyAverageCalorie", String.valueOf(weeklyAverageCalorie));
        editorCalorie.apply();



    }

    public void onClickBack(View view) {
        Intent transitionToGraphNetCalorie= new Intent(this,GraphNetCalorie.class);
        startActivity(transitionToGraphNetCalorie);
    }


    public void onClickCaloriePredict(View view) {
        Intent transitionToCalorieBMICalculator= new Intent(this,CalorieBMICalculator.class);
        startActivity(transitionToCalorieBMICalculator);
    }
}
