package android.team9.com.timetabling;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

/**
 * Created by frank on 23/02/16.
 */
public class QrCodeTest {

    private String userid;
    private String roomid;
    SimpleScannerActivity scanner;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

        roomid="qmbsmr";
        userid="101010";
        //scanner.setFields(userid,roomid);
    }
    @Test
    public void testSubmitValues(){
      //  scanner.submitValues();
    }


    @After
    public void tearDown() {

    }
}
