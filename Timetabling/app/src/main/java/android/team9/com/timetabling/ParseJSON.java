package android.team9.com.timetabling;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON {
    public static String[] moduleCode;
    public static String[] moduleTitle;

    public static final String KEY_MODULE_CODE = "module_code";
    public static final String KEY_MODULE_TITLE = "module_title";

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        try {

            JSONArray jsonArray = new JSONArray(json);
            Log.v("JSON", jsonArray.toString());

            moduleCode = new String[jsonArray.length()];
            moduleTitle = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                moduleCode[i] = jsonobject.getString(KEY_MODULE_CODE);
                moduleTitle[i] = jsonobject.getString(KEY_MODULE_TITLE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}