package StepDefinitions;

import io.cucumber.java.en.And;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDataRetrieval {
    public static int count;
    public static String reportID;
    public static String policyID;
    public static String name;
    public static String ownerLoginID;
    public static String ownerName;
    public static String approvalStatusName;
    public static String paymentStatusName;
    public static String paymentStatusCode;

    @And("User gets the data loaded in DB and check for owner {string} and reportId {string}")
    public void dataRetrieval(String owner, String reportId) throws SQLException{
        try{
            //Total count of rows in the table
            Statement st = DBConnect.dbConn.createStatement();
            String sql_command = "SELECT count(*) AS rowcount FROM qa_pratap.report where ownerloginid= '"+owner+"'}'";
            ResultSet rs = st.executeQuery(sql_command);
            rs.next();
            count = rs.getInt("rowcount") ;
            rs.close() ;
            System.out.println("========================================");
            System.out.println("MyTable has " + count + " row(s).");
            //Fetching particular values for a record in the table
            Statement st1 = DBConnect.dbConn.createStatement();
            String sql_command1 = "select REPORTID, POLICYID, NAME, OWNERLOGINID, OWNERNAME, APPROVALSTATUSNAME, PAYMENTSTATUSNAME, PAYMENTSTATUSCODE from qa_pratap.report where reportid = '"+reportId+"'";
            System.out.println("========================================");
            System.out.println(sql_command1);
            ResultSet rs1 = st1.executeQuery(sql_command1);
            while(rs1.next()){
//             int runDate = rs1.getInt("RUNDATE");
                reportID = rs1.getString("REPORTID");
                policyID = rs1.getString("POLICYID");
                name = rs1.getString("NAME");
                ownerLoginID = rs1.getString("OWNERLOGINID");
                ownerName = rs1.getString("OWNERNAME");
                approvalStatusName = rs1.getString("APPROVALSTATUSNAME");
                paymentStatusName = rs1.getString("PAYMENTSTATUSNAME");
                paymentStatusCode = rs1.getString("PAYMENTSTATUSCODE");
                System.out.println(reportID + ", " + policyID + ", " + name +
                        ", " + ownerLoginID + ", " + ownerName +
                        ", " + approvalStatusName +
                        ", " + paymentStatusName + ", " + paymentStatusCode);
            }
        }
        catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }finally {
            DBConnect.dbConn.close();
        }
    }
}
