package com.example.androidactivities.caloriecount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphNetCalorie extends AppCompatActivity {


    // DISPLAYS A GRAPH SHOWING THE CALORIE CONSUMED EACH DAY//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_net_calorie);
        //----------GETS ALL THE VALUES OF CALORIES CONSUMED-----  -//
        SharedPreferences netCalorieDaily = getSharedPreferences("totalCalorieConsumed", Context.MODE_PRIVATE);
        String netCalorieDay1 = netCalorieDaily.getString("TotalCalorieDay1", "0");
        String netCalorieDay2 = netCalorieDaily.getString("TotalCalorieDay2", "0");
        String netCalorieDay3 = netCalorieDaily.getString("TotalCalorieDay3", "0");
        String netCalorieDay4 = netCalorieDaily.getString("TotalCalorieDay4", "0");
        String netCalorieDay5 = netCalorieDaily.getString("TotalCalorieDay5", "0");
        String netCalorieDay6 = netCalorieDaily.getString("TotalCalorieDay6", "0");
        String netCalorieDay7 = netCalorieDaily.getString("TotalCalorieDay7", "0");


        BarChart chart = (BarChart) findViewById(R.id.chart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1f, Float.valueOf(netCalorieDay1)));
        entries.add(new BarEntry(2f, Float.valueOf(netCalorieDay2)));
        entries.add(new BarEntry(3f, Float.valueOf(netCalorieDay3)));
        entries.add(new BarEntry(4f, Float.valueOf(netCalorieDay4)));
        entries.add(new BarEntry(5f, Float.valueOf(netCalorieDay5)));
        entries.add(new BarEntry(6f, Float.valueOf(netCalorieDay6)));
        entries.add(new BarEntry(7f, Float.valueOf(netCalorieDay7)));

        //-----------DESIGN------------//
        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData data = new BarData(set);
        data.setValueTextSize(15f);
        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDoubleTapToZoomEnabled(true);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);

        //X- AXIS Settings
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(15f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        //xAxis.setText("Date");
        //Y-AXIS Settings
        YAxis left = chart.getAxisLeft();
        //LimitLine ll = new LimitLine(20f, "Calories");

        left.setDrawZeroLine(false); // draw a zero line
        left.setDrawLabels(true); // no axis labels
        left.setTextSize(15f);

        chart.getAxisRight().setEnabled(false); // no right axis
        chart.setDescription(null);
        //left.addLimitLine(ll);
        set.setColors(colors);
        Legend legend = chart.getLegend();
        //legend.setTextSize(20f);
        chart.getLegend().setEnabled(false);
        chart.setDrawBorders(true);
        chart.invalidate(); // refresh

    }

    public void onClickBack(View view) {
        Intent transitionToMainActivity= new Intent(this,MainActivity.class);
        startActivity(transitionToMainActivity);
    }


    public void onClickLineChart(View view) {

        Intent transitionToLineChart=new Intent(this,LineChartCalories.class);
        startActivity(transitionToLineChart);


    }
}
