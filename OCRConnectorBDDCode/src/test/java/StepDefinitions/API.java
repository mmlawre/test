package StepDefinitions;

import Authentication.TokenGeneration;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class API {

    public static int size;
    public static String reportID;
    public static String policyID;
    public static String name;
    public static String ownerLoginID;
    public static String ownerName;
    public static String approvalStatusName;
    public static String paymentStatusName;
    public static String paymentStatusCode;

    @And("User gets the same data through API call for {string}")
    public void getReportDigestForSingleUser(String user) throws IOException, URISyntaxException {

        RequestSpecification request = RestAssured.given();
        request
                .queryParam("user",user )
                .header("Authorization", "Bearer "+ TokenGeneration.get_AccessToken())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
        Response response = request.get("https://us2.api.concursolutions.com/api/v3.0/expense/reports");
        Assert.assertEquals(response.getStatusCode(),200, "Report Digest for single user and single report gives status code 200");
        String reponseJsonString = response.getBody().asString();
        JsonPath j = new JsonPath(reponseJsonString);
    }

    @And("User gets the same data through API call for single user {string} through single report {string}")
    public void getReportDigestForSingleUserSingleReport(final String user,final String reportid) throws IOException, URISyntaxException {
        RequestSpecification request = RestAssured.given();
        request
                .pathParam("id", reportid)
                .queryParam("user", user)
                .header("Authorization", "Bearer "+ TokenGeneration.get_AccessToken())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
        Response response = request.get("https://us2.api.concursolutions.com/api/v3.0/expense/reports");
        Assert.assertEquals(response.getStatusCode(),200 , "Report Digest for single user and single report gives status code 200");
        String reponseJsonString = response.getBody().asString();
        JsonPath j = new JsonPath(reponseJsonString);
        reportID = j.getString("ID");
        policyID = j.getString("PolicyID");
        name = j.getString("Name");
        ownerLoginID = j.getString("OwnerLoginID");
        ownerName = j.getString("OwnerName");
        approvalStatusName = j.getString("ApprovalStatusName");
        paymentStatusName = j.getString("PaymentStatusName");
        paymentStatusCode = j.getString("PaymentStatusCode");
       }
}
