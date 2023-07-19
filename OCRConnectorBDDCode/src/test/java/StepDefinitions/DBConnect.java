package StepDefinitions;

import Utils.PropertyReader;
import io.cucumber.java.en.Given;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnect {
    public static Connection dbConn = null;

    @Given("The user tries to connect to the DB.")
    public void theUserTryToConnectWithTheDB() throws IOException, URISyntaxException {
        String dbURL = "jdbc:oracle:thin:" + PropertyReader.getProperty("jdbcUsername") + "/" + PropertyReader.getProperty("jdbcPassword") + "@" + PropertyReader.getProperty("jdbcURL");
        try {
            dbConn = DriverManager.getConnection(dbURL);
        } catch (Exception e){
        System.err.println(e.getLocalizedMessage());
    }
    }

    @Given("The user is connected to the DB successfully")
    public void theUserIsConnectedToTheDBSuccessfully() {
            if (dbConn != null) {
                System.out.println("Connected to Database");
            }else{
                System.out.println("Failed to connect to the Database");
            }
    }
}
