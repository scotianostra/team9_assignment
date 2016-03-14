package android.team9.com.timetabling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ModuleAttendanceGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_attendance_graph);

        // To make vertical bar chart, initialize graph id this way
        BarChart barChart = (BarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4, 0));
        entries.add(new BarEntry(8, 1));
        entries.add(new BarEntry(6, 2));
        entries.add(new BarEntry(12, 3));

        BarDataSet dataset = new BarDataSet(entries, "# of students (60 enrolled)");

        // creating labels
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Tutorial");
        labels.add("Lecture");
        labels.add("Lecture");
        labels.add("Lab");

        BarData data = new BarData(labels, dataset);
        barChart.setData(data); // set the data and list of lables into chart

        barChart.setDescription("Week 1 - Introduction to Java");  // set the description
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(2000);
    }


}
