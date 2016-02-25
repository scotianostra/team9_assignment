package android.team9.com.timetabling;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

/**
 * Created by jamie on 22/02/16.
 */

public class ParseJSONTest{

    private ParseJSON parseJSON;
    private String jsonArray;
    private String jsonStudents;
    private String jsonStudentObject;
    private String jsonStaffObject;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

        jsonArray = "[{\"moduleid\": 10012, \"module_code\": \"AC30001\", \"module_title\": \"Introduction to Java\"}, " +
                "{\"moduleid\": 10013, \"module_code\": \"AC30002\", \"module_title\": \"Data Visualisation\"}]";

        jsonStudents = "[{\"matric_number\": 101010, \"email\": \"bil@test.com\", \"first_name\": \"Bill\", \"last_name\": \"Kill\"}, " +
                "{\"matric_number\": 101011, \"email\": \"john@test\", \"first_name\": \"John\", \"last_name\": \"Wick\"}]";

        jsonStudentObject = "{\"matric_number\": 234242, \"hash\": \"AD3435SF\"}";
        jsonStaffObject = "{\"staffid\": 1000, \"hash\": \"AJ232ASFA\"}";

    }

    @Test
    public void testParseJSONModuleList() throws JSONException {

        parseJSON = new ParseJSON(jsonArray);
        parseJSON.parseJSONModuleList();

        assertEquals("AC30001", ParseJSON.moduleCode[0]);
        assertEquals("AC30002", ParseJSON.moduleCode[1]);
        assertEquals("10012", ParseJSON.moduleId[0]);
        assertEquals("10013", ParseJSON.moduleId[1]);
        assertEquals("Introduction to Java", ParseJSON.moduleTitle[0]);
        assertEquals("Data Visualisation", ParseJSON.moduleTitle[1]);
    }

    @Test
    public void testParseJSONEnrolledStudents() throws JSONException {

        parseJSON = new ParseJSON(jsonStudents);
        parseJSON.parseJSONEnrolledStudents();

        assertEquals("101010", ParseJSON.matricNumber[0]);
        assertEquals("101011", ParseJSON.matricNumber[1]);
        assertEquals("bil@test.com", ParseJSON.email[0]);
        assertEquals("john@test", ParseJSON.email[1]);
        assertEquals("Bill", ParseJSON.fName[0]);
        assertEquals("John", ParseJSON.fName[1]);
        assertEquals("Kill", ParseJSON.lName[0]);
        assertEquals("Wick", ParseJSON.lName[1]);
    }

    @Test
    public void testParseJSONLoginStudent() throws JSONException {

        parseJSON = new ParseJSON(jsonStudentObject);
        parseJSON.parseJSONLogin();
        assertEquals(234242, ParseJSON.user_id);
        assertEquals("AD3435SF", ParseJSON.role);

    }

    @Test
    public void testParseJSONLoginStaff() throws JSONException {

        parseJSON = new ParseJSON(jsonStaffObject);
        parseJSON.parseJSONLogin();
        assertEquals(1000, ParseJSON.user_id);
        assertEquals("AJ232ASFA", ParseJSON.role);
    }

    @Test
    public void testParseJSONLoginThrowsJSONException() throws JSONException {
        parseJSON = new ParseJSON("not valid json");
        thrown.expect(JSONException.class);
        parseJSON.parseJSONLogin();
    }

    @Test
    public void testParseJSONModuleListThrowsJSONException() throws JSONException {
        parseJSON = new ParseJSON("not valid json");
        thrown.expect(JSONException.class);
        parseJSON.parseJSONModuleList();
    }

    @Test
    public void testParseJSONStudentListThrowsJSONException() throws JSONException {
        parseJSON = new ParseJSON("not valid json");
        thrown.expect(JSONException.class);
        parseJSON.parseJSONEnrolledStudents();
    }

    @After
    public void tearDown() {
        parseJSON = null;
        jsonStaffObject = null;
        jsonStudentObject = null;
        jsonArray = null;
    }
}
