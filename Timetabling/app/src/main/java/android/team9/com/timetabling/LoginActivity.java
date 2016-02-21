package android.team9.com.timetabling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.HttpResponseCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();
    public final static String EXTRA_USERID = "android.team9.com.USERID";

    private static final String LOGIN_URL = "http://api.ouanixi.com/login/";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email_address";

    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String role = sharedPref.getString("hash", "no_user");
        int id = sharedPref.getInt("matric_number", -1);
        redirectToActivity(id, role);
        setContentView(R.layout.activity_login);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonRegister = (Button) findViewById(R.id.buttonLogin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == buttonRegister) {
                    loginUser();
                }
            }
        });
    }

    private void loginUser() {
        final String password = editTextPassword.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ParseJSON prsJson = new ParseJSON(response);
                        prsJson.parseJSONObject();
                        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.clear();
                        editor.putInt("id", prsJson.user_id);
                        editor.putString("role", prsJson.role);
                        editor.commit();
                        //redirectToActivity(prsJson.user_id, prsJson.role);
                        Toast.makeText(LoginActivity.this, prsJson.role + " " +prsJson.user_id, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 406)
                            Toast.makeText(LoginActivity.this, "email or password incorrect", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_PASSWORD, password);
                params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void redirectToActivity(int id, String role){

        if (id > 0 ) {

            if (role == "staff") {
                Intent intent = new Intent(this, StaffUIActivity.class);
                intent.putExtra(EXTRA_USERID, id);
                startActivity(intent);
            }
//            else if (role == "student") {
//                Intent intent = new Intent(this, StudentUIActivity.class);
//                intent.putExtra(EXTRA_USERID, id);
//                startActivity(intent);
//            }
        }
    }

}