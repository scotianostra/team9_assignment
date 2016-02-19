package android.team9.com.timetabling;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ModuleEnrollementList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_enrollement_list);


        ArrayList<String> enroList = new ArrayList<>();
        enroList.add("Agile Engineering");
        enroList.add("Human Computer Interaction");
        enroList.add("Software Engineering");
        enroList.add("Game Programming");
        ArrayAdapter<String> enroListAdapter = new ArrayAdapter<>(this, R.layout.text_view, enroList);

        ListView listView1 = (ListView) findViewById(R.id.listView);
        listView1.setAdapter(enroListAdapter);
        Log.i("List :", enroListAdapter.toString());
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
