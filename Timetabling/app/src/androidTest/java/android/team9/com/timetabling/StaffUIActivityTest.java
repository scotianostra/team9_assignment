package android.team9.com.timetabling;

import android.team9.com.timetabling.StaffUIActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

/**
 * Created by jamie on 22/02/16.
 */
public class StaffUIActivityTest extends ActivityInstrumentationTestCase2<StaffUIActivity> {


    public StaffUIActivityTest(Class<StaffUIActivity> activityClass) {
        super(activityClass);
    }

    public void testActivityExists() {
        StaffUIActivity activity = getActivity();
        assertNotNull(activity);
    }



}
