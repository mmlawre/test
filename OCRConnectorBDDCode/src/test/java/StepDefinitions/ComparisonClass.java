package StepDefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;


public class ComparisonClass{
    @Then("User validates the data")
    public void validateTheData() throws SQLException, IOException, URISyntaxException {
//        gc.getReportDigestForSingleUserSingleReport("julia@oversightsystems-1.com","43B5A5D400ED4E3BA05C");
//        List<String> xmlContent = FileReader.readXMLFile("C:/Users/mrudul.bhalerao/Downloads/reportdigest.xml","Report");
        int commandVal = DBDataRetrieval.count;
        int sizeVal = API.size;
        Assert.assertEquals(commandVal, sizeVal, "Count is not Equal");

        Assert.assertEquals(DBDataRetrieval.reportID, API.reportID, "reportID matches in DB and fetched from API");
        Assert.assertEquals(DBDataRetrieval.policyID, API.policyID, "policyID matches in DB and fetched from API");
        Assert.assertEquals(DBDataRetrieval.name, API.name, "name matches in DB and fetched from API");
        Assert.assertEquals(DBDataRetrieval.ownerLoginID, API.ownerLoginID, "ownerLoginID matches in DB and fetched from API");
        Assert.assertEquals(DBDataRetrieval.ownerName, API.ownerName, "ownerName matches in DB and fetched from API");
        Assert.assertEquals(DBDataRetrieval.approvalStatusName, API.approvalStatusName, "approvalStatusName matches in DB and fetched from API");
        Assert.assertEquals(DBDataRetrieval.paymentStatusName, API.paymentStatusName, "paymentStatusName matches in DB and fetched from API");
        Assert.assertEquals(DBDataRetrieval.paymentStatusCode, API.paymentStatusCode, "paymentStatusCode matches in DB and fetched from API");
    }

    @Then("User validates the data for number of records")
    public void userValidatesTheDataForNumberOfRecords() {
        int commandVal = DBDataRetrieval.count;
        int sizeVal = API.size;
        Assert.assertEquals(commandVal, sizeVal, "Count is not Equal");
    }
}
