package android.team9.com.timetabling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // To make vertical bar chart, initialize graph id this way
        BarChart barChart = (BarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4, 0));
        entries.add(new BarEntry(8, 1));
        entries.add(new BarEntry(6, 2));
        entries.add(new BarEntry(12, 3));
        entries.add(new BarEntry(18, 4));

        BarDataSet dataset = new BarDataSet(entries, "# of students");

        // creating labels
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Lecture 1");
        labels.add("Lab");
        labels.add("Lecture 2");
        labels.add("Tutorial 1");
        labels.add("Lecture 3");

        BarData data = new BarData(labels, dataset);
        barChart.setData(data); // set the data and list of lables into chart
        barChart.setDescription("Student attendance for week 1");  // set the description

    }
}
