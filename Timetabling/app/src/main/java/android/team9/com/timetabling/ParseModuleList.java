package android.team9.com.timetabling;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseModuleList {
    public static String[] moduleCode;
    public static String[] moduleTitle;
    public static String[] moduleId;

    public static final String KEY_MODULE_CODE = "module_code";
    public static final String KEY_MODULE_TITLE = "module_title";
    public static final String KEY_MODULE_ID = "moduleid";

    private String json;

    public ParseModuleList(String json){ this.json = json; }

    public void parseJSON(){
        try {

            JSONArray jsonArray = new JSONArray(json);
            Log.v("JSON", jsonArray.toString());

            moduleCode = new String[jsonArray.length()];
            moduleTitle = new String[jsonArray.length()];
            moduleId = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                moduleCode[i] = jsonobject.getString(KEY_MODULE_CODE);
                moduleTitle[i] = jsonobject.getString(KEY_MODULE_TITLE);
                moduleId[i] = jsonobject.getString(KEY_MODULE_ID);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}