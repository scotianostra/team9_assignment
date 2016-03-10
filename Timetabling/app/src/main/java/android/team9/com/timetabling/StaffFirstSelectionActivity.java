package android.team9.com.timetabling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StaffFirstSelectionActivity extends AppCompatActivity {
        private String moduleId;
        private String moduleTitle;
        private String moduleCode;
        public final static String MODULEID = "android.team9.com.MODULEID";

    private Button buttonAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_selection);
        moduleId = getIntent().getStringExtra("moduleId");
        moduleTitle = getIntent().getStringExtra("moduleTitle");
        moduleCode = getIntent().getStringExtra("moduleCode");

        EditText idText = (EditText) findViewById(R.id.moduleId);
        EditText nameText = (EditText) findViewById(R.id.moduleName);
        idText.setText(moduleCode);
        nameText.setText(moduleTitle);

        buttonAttendance = (Button) findViewById(R.id.attendanceButton);

        buttonAttendance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == buttonAttendance) {
                    viewAttendance();
                }
            }
        });
    }

    //click on button 'VIEW ENROLLED STUDENTS'
    public void view_enrolled_students(View view){
        Bundle b = new Bundle();
        b.putString("module", moduleId);
        b.putString("moduleCode", moduleCode);
        Intent pass = new Intent(StaffFirstSelectionActivity.this, EnrolledStudents.class);
        pass.putExtras(b);
        startActivity(pass);
    }

    public void view_module_attendance(View view) {
        Bundle b = new Bundle();
        b.putString("moduleId", moduleId);
        Intent pass = new Intent(StaffFirstSelectionActivity.this, StaffModuleAttendanceActivity.class);
        pass.putExtras(b);
        startActivity(pass);
    }

    private void viewAttendance(){

        Intent intent = new Intent(this, ClassListActivity.class);
        intent.putExtra(MODULEID,moduleId);
        startActivity(intent);

    }
}
