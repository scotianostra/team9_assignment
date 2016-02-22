package android.team9.com.timetabling;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
/**
 * Created by frank on 22/02/16.
 */
public class ResponseFrank implements Response.Listener {
    private Context context;

    public ResponseFrank(Context con){
      context=con;
    }

    @Override
    public void onResponse(Object response) {
        // Result handling
        System.out.println(response.toString().substring(0,100));
        Toast.makeText(context, "Successfully logged in", Toast.LENGTH_SHORT).show();
    }
}
