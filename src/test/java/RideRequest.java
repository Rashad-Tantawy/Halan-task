import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constant;
import utils.Extract;


public class RideRequest {

    private String driverCredential = "{\"password\": \"mainmethod\",\n" + "  \"phoneNumber\": \"8890151\"}";
    private String userCredential="{\"password\": \"mainmethod\",\n"     + "  \"phoneNumber\": \"79701999921\"}";
    private String bodyRequestRide="{\"latitude\": \"30.06348\",\n"      + "  \"longitude\": \"31.215784\",\n "     + " \"vehicleType\": \"5ca0f914b9a8b02dab2f734d\"}";
    private String bodyAcceptRide="{\"latitude\": \"30.06348\",\n"       + "  \"longitude\": \"31.215784\",\n "     + " \"tripRequestId\": \"1112\",\n" + "\"language\": \"ar\"}";
    private Response response = null;




    @Test
    public void driverLoginFlow() throws Exception {
        RestAssured.baseURI="https://api-staging.halan.io";
        loginRequest("/api/v1/driver/login",driverCredential);
        getExtractedValue(driverCredential,"/api/v1/driver/login","data.access_token");
       /* Extract.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
        Extract.setCellData(,1,1);*/

    }
    @Test
    public void userLoginFlow() throws Exception {
        RestAssured.baseURI="https://api-staging.halan.io";
        loginRequest("/api/v1/driver/login",userCredential);
       // String ExtractUserToken =  getExtractedValue(userCredential,"/api/v1/user/login","data.access_token");
      /*  Extract.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
        Extract.setCellData(ExtractUserToken,0,1);
*/


    }
    @Test
    public void requestRideFlow(){
        RestAssured.baseURI="https://api-staging.halan.io";
        requestRide("/api/v1/driver/login",bodyRequestRide);
        //getExtracted(,"","data.tripRequestId");

    }

    @Test
    public void acceptRideFlow(){
        RestAssured.baseURI="https://api-staging.halan.io";
        acceptRide("/api/v1/driver/login",bodyAcceptRide);
        //getExtracted(,"","data.tripRequestId");



    }

   String getExtractedValue(String credential,String endPoint,String target) {
        String token = RestAssured.given().contentType(ContentType.JSON)
                .body(credential)
                .post(endPoint).then().extract().path(target).toString();
        System.out.println(token);
       return token;
    }
    void loginRequest(String EndPoint,String credential) {

    response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(credential)
            .post(EndPoint);
    response.prettyPrint();

    int statusCode = response.getStatusCode();
    System.out.println("Status code is: " + statusCode);

    // Assert the correct status code is returned.
    Assert.assertEquals(statusCode, 200, "تم");



    }
    void requestRide(String EndPoint,String bodyRequestRide){

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .basic("79701999921", "mainmethod")
                .body(bodyRequestRide)
                .post(EndPoint);
        response.prettyPrint();

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);

        // Assert the correct status code is returned.
        Assert.assertEquals(statusCode, 200, "تم");

    }
    void acceptRide(String EndPoint,String bodyAcceptRide){

        response = RestAssured.given()
                 .contentType(ContentType.JSON)
                .auth()
                .basic("8890151", "mainmethod")
                .body(bodyAcceptRide)
                .post(EndPoint);
        response.prettyPrint();


        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);

        // Assert the correct status code is returned.
        Assert.assertEquals(statusCode, 200, "تم");
    }

}