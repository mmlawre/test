Feature: Test the D command
  Background:  DB is ready and connected successfully
    Given The user tries to connect to the DB.

  Scenario: Test the D command and validate the results for single user :- QA-4414
    Given The user is connected to the DB successfully
    When User runs the D command
    And User gets the data loaded in DB and check for owner "Approver1@oversightsystems-1.com" and reportId "43B5A5D400ED4E3BA05C"
    And User gets the same data through API call for "Approver1@oversightsystems-1.com"
    Then User validates the data for number of records

  Scenario: Test the D command and validate the results with single user and single report id :- QA-4414
    Given The user is connected to the DB successfully
    When User runs the D command
    And User gets the data loaded in DB and check for owner "Approver1@oversightsystems-1.com" and reportId "43B5A5D400ED4E3BA05C"
    And User gets the same data through API call for "Approver1@oversightsystems-1.com"
    Then User validates the data