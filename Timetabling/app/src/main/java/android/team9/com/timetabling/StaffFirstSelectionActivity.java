package android.team9.com.timetabling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StaffFirstSelectionActivity extends AppCompatActivity {
        private String moduleId;
        private String moduleTitle;
        private String moduleCode;
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
    }

    //click on button 'VIEW ENROLLED STUDENTS'
    public void view_enrolled_students(View view){
        Bundle b = new Bundle();
        b.putString("module", moduleId);

        Log.i("click on first button ", moduleId);

        Intent pass = new Intent(StaffFirstSelectionActivity.this, EnrolledStudents.class);
        pass.putExtras(b);
        startActivity(pass);
    }

}
