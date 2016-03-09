package android.team9.com.timetabling;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StaffModuleAttendanceActivity extends AppCompatActivity {
    private String moduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_module_attendance);
        init();
    }

    public void init() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableMain);

        tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String matricNumber=;
                //Bundle b = new Bundle();

                // b.putString("matricNumber", matricNumber);
                //Intent pass = new Intent(StaffLandingActivity.this, StaffFirstSelectionActivity.class);
                // pass.putExtras(b);
                // startActivity(pass);
            }
        });
        TableRow tableRow = new TableRow(this);
        tableRow.setPadding(25, 25, 25, 25);
        TextView tv0 = new TextView(this);
        tv0.setText("    Matriculation Nr. ");
        tv0.setTextColor(Color.WHITE);
        tableRow.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Forename ");
        tv1.setTextColor(Color.WHITE);
        tableRow.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Surname ");
        tv2.setTextColor(Color.WHITE);
        tableRow.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Attendance % ");
        tv3.setTextColor(Color.WHITE);
        tableRow.addView(tv3);
        tableLayout.addView(tableRow);
        for (int i = 0; i < 25; i++) {
            TableRow tableRowInside = new TableRow(this);
            tableRowInside.setPadding(25, 0, 25, 0);
            TextView t1v = new TextView(this);
            t1v.setText(" " + i + 100000);
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tableRowInside.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("Aistis " + i);
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tableRowInside.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("Taraskevicius" + i);
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tableRowInside.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("" + i * 15 / 32 * 10 + "%");
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tableRowInside.addView(t4v);
            tableLayout.addView(tableRowInside);
        }
    }
}