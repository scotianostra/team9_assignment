package android.team9.com.timetabling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;


public class StaffUIActivity extends AppCompatActivity {

    private String JSON_URL = "http://api.ouanixi.com/staffModuleList/";
    private ListView listView;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_ui);

        listView = (ListView) findViewById(R.id.listView);

        Bundle extra = getIntent().getExtras();
        user_id = extra.getInt(LoginActivity.EXTRA_USERID);

        sendRequest();
    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL + user_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            showJSON(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StaffUIActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) throws JSONException {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSONModuleList();
        CustomModuleList cl = new CustomModuleList(this, ParseJSON.moduleCode, ParseJSON.moduleTitle, ParseJSON.moduleId);
        listView.setAdapter(cl);

    }

}