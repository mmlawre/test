package Authentication;

import Utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.net.URISyntaxException;

public class TokenGeneration {
    public static String  get_AccessToken() throws IOException, URISyntaxException {
        String Url = "https://us.api.concursolutions.com/oauth2/v0/token";
        RequestSpecification http_req = RestAssured.given().contentType("application/x-www-form-urlencoded;charset=utf-8").
                formParam("username", PropertyReader.getProperty("tokenUsername")).
                formParam("password", PropertyReader.getProperty("tokenPassword")).
                formParam("grant_type", "password").
                formParam("client_secret", "d00f07b1-eb1e-49af-bf30-4583d1fbd534").
                formParam("client_id", "24a3f705-1edc-46c8-9211-5b17618d36e2").
                formParam("credtype", "authtoken")
                .when().accept("application/json");
        Response http_resp = http_req.post(Url);
        String jsonString = http_resp.getBody().asString();
        final String access_token =JsonPath.from(jsonString).get("access_token");
        return access_token;
    }
}
