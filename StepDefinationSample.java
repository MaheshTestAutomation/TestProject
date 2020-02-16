//1st sheet data
	@Then("^I validate list of properties in page zero with data in excel at \"([^\"]*)\"$")
	public void i_validate_list_of_dynamic_tag_properties_in_page_zero_with_data_in_excel_at(@Transform(ExcelDataToDataTable.class)  DataTable table) throws Throwable {
	System.out.println(table.toString());
    List<String> dataList=table.asList(String.class);

for(String str : dataList)
{
	System.out.println(str);
}
   
  
    //2nd sheet data
    
	@Then("^I validate list of properties in page one with data in excel at \"([^\"]*)\"$")
	public void i_validate_list_of_properties_in_page_one_with_data_in_excel_at(@Transform(ExcelDataToDataTable.class)  DataTable table) throws Throwable {
	System.out.println(table.toString());
    List<String> dataList=table.asList(String.class);

for(String str : dataList)
{
	System.out.println(str);
}
 
