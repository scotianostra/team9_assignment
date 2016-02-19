package android.team9.com.timetabling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ModuleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_list);

        //hard code: A list of modules allocated to the user(staff)
        ArrayList<String> ml = new ArrayList<>();
        ml.add("Agile Engineering");
        ml.add("Human Computer Interaction");
        ml.add("Software Engineering");
        ml.add("Game Programming");

        //Display the module list
        ArrayAdapter<String> mlAdapter = new ArrayAdapter<>(this, R.layout.text_view, ml);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(mlAdapter);

        //Click an item in the list and pass its value to 'EnrolledStudents' activity
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                Log.i("value ", value);
                String combined = value.replace(" ", "_");
                Log.i("combined ", combined);
                Bundle b = new Bundle();
                b.putString("module", combined);
                Intent pass = new Intent(ModuleList.this, EnrolledStudents.class);
                pass.putExtras(b);
                startActivity(pass);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_module_enrollement_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
