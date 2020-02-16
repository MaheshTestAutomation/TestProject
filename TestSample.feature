Feature: Validate values in application pages
Description: Validate dynamic values 
@Test
Scenario: User navigates to application site and validates tags
Given As User I need to tags in the application
When I navigate to zero page "application url"
Then I validate list of properties in page zero with data in excel at "TestData.xlsx;1"
@Test1
Scenario: User navigates to application site and validates tags
Then i select country
Then i validate list of dynamic properties in page one with data in exel at "TestData.xlsx;2"
