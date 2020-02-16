package com.api.cucumber.transform;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.automation.custom.utilities.ExcelLibrary;
import com.automation.custom.utilities.ExcelReader;


import cucumber.api.DataTable;
import cucumber.api.Transformer;
import cucumber.runtime.ParameterInfo;
import cucumber.runtime.table.TableConverter;
import cucumber.runtime.xstream.LocalizedXStreams;
import gherkin.formatter.model.Comment;
import gherkin.formatter.model.DataTableRow;



public class ExcelDataToDataTable extends Transformer<DataTable> {
	

	
	
	@Override
	public DataTable transform(String filePath) {
		
		
		String file[] = filePath.split(";");
		
	    String path = getfilePath(file[0]);
		
		ExcelReader reader = new ExcelReader.ExcelReaderBuilder()
				
				.setFileLocation(path)
				.setSheet(file[1])
				.build();
		
		List<List<String>> excelData = getExcelData(reader);
		
		List<DataTableRow> dataTableRows = getDataTableRows(excelData);
		
		DataTable table = getDataTable(dataTableRows);
		
		return table;
	}

	
		
public String getfilePath(String path) {
	 File file = new File("OmnitureTestData.xlsx");
	 
return file.toString();
	
	}

	private DataTable getDataTable(List<DataTableRow> dataTableRows) {
		ParameterInfo parameterInfo = new ParameterInfo(null, null, null, null);
	    TableConverter tableConverter = new TableConverter(new LocalizedXStreams(Thread.currentThread().getContextClassLoader()).get(Locale.getDefault()), parameterInfo);
		
		DataTable table = new DataTable(dataTableRows, tableConverter);
		return table;
	}

	private List<DataTableRow> getDataTableRows(List<List<String>> excelData) {
		List<DataTableRow> dataTableRows = new LinkedList<>();
		int line = 1;
		
		for(List<String> list : excelData){
			Comment commnet = new Comment("", line);
			DataTableRow tableRow = new DataTableRow(Arrays.asList(commnet), list, line++);
			dataTableRows.add(tableRow);
		}
		return dataTableRows;
	}

	private List<List<String>> getExcelData(ExcelReader reader) {
		List<List<String>> excelData = new LinkedList<>();
		
		try {
			excelData = reader.getSheetDataAt();
		} catch (InvalidFormatException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return excelData;
	}

}
