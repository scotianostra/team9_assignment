package android.team9.com.timetabling;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModuleAttendanceGraphActivity extends AppCompatActivity {

    private static final String MODULE_ATTENDANCE_URL = "http://127.0.0.1:8000/moduleAttendanceList/";
    public static final String KEY_MODULE_ID = "module_id";
    public static final String KEY_WEEK = "week";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_attendance_graph);

        getModuleAttendance();

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

    private void getModuleAttendance() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MODULE_ATTENDANCE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ParseJSON prsJson = new ParseJSON(response);
                        //try {
                            Log.v("ATTENDANCE", response.toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        Toast.makeText(ModuleAttendanceGraphActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_MODULE_ID, "10014");
                params.put(KEY_WEEK, "1");
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
