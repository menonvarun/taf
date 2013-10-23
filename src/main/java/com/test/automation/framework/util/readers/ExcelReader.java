package com.test.automation.framework.util.readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private Cell openCell;
	private Row openRow;
	private Sheet openSheet;
	private Workbook openWorkbook;
	public static int noOfSheet = 0;

	Map<String, List<String>> ht = new LinkedHashMap<String, List<String>>();

	public ExcelReader(String filePath) throws FileNotFoundException,
			IOException {
		this(new File(filePath));
	}

	public ExcelReader(File file) throws IOException, FileNotFoundException {
		this.openFile(file, 0);
	}
	
	public ExcelReader(String filePath, int sheetNo) throws IOException, FileNotFoundException {
		this.openFile(filePath, sheetNo);
	}
	
	public ExcelReader(String filePath, String sheetName) throws IOException, FileNotFoundException {
		this.openFile(filePath, sheetName);
	}
	
	public ExcelReader(File file, int sheetNo) throws IOException, FileNotFoundException {
		this.openFile(file, sheetNo);
	}
	
	public ExcelReader(File file, String sheetName) throws IOException, FileNotFoundException {
		this.openFile(file, sheetName);
	}
	
	public ExcelReader() {
		
	}

	public void openFile(File file, int sheetNo) throws IOException,
			FileNotFoundException {

		this.openWorkbook(file);
		openSheet = openWorkbook.getSheetAt(sheetNo);

	}

	public void openFile(String filePath, int sheetNo)
			throws FileNotFoundException, IOException {
		this.openFile(new File(filePath), sheetNo);
	}

	public void openFile(File file, String sheetName)
			throws FileNotFoundException, IOException {
		this.openWorkbook(file);
		openSheet = openWorkbook.getSheet(sheetName);
	}

	public void openFile(String filePath, String sheetName)
			throws FileNotFoundException, IOException {
		this.openWorkbook(filePath);
		openSheet = openWorkbook.getSheet(sheetName);
	}

	private void openWorkbook(String filePath) throws FileNotFoundException,
			IOException {
		this.openWorkbook(new File(filePath));
	}

	private void openWorkbook(File file) throws FileNotFoundException,
			IOException {
		InputStream inp;
		try {
			inp = new FileInputStream(file);

			if (isXlsx(file))
				openWorkbook = new XSSFWorkbook(inp);
			else
				openWorkbook = new HSSFWorkbook(inp);

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
	
	public void openSheet(int sheetNo){
		openSheet = openWorkbook.getSheetAt(sheetNo);
	}
	
	public void openSheet(String sheetName){
		openSheet = openWorkbook.getSheet(sheetName);
	}
	
	public Workbook getOpenWorkbook(){
		return openWorkbook;				
	}

	public String getData(int row, int column) {
		String data = "";
		try {

			openRow = openSheet.getRow(row);
			openCell = openRow.getCell(column);
			int cellType = openCell.getCellType();
			switch (cellType) {
			case 0:
				if (DateUtil.isCellDateFormatted(openCell)) {
					Date dt = openCell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"dd MM yyyy HH:mm:ss");
					data = sdf.format(dt);
				} else
					data = Long.toString(Math.round(openCell
							.getNumericCellValue()));
				break;
			case 1:
				data = openCell.getRichStringCellValue().getString();
				break;
			case 2:
				data = openCell.getCellFormula();
				break;
			case 3:
				data = openCell.getRichStringCellValue().getString();
				break;
			case 4:
				data = Boolean.toString(openCell.getBooleanCellValue());
				break;
			case 5:
				data = Byte.toString(openCell.getErrorCellValue());
				break;
			default:
				data = openCell.getRichStringCellValue().getString();
			}

			if (data == null) {
				data = "";
			}
			return data;
		} catch (Exception e) {
			if (openRow == null || openCell == null || data == null) {
				data = "";
				return data;
			} else {
				System.out.println(e);
				return "";
			}
		}

	}

	public int getNoOfRows() {
		return openSheet.getPhysicalNumberOfRows();
	}

	public int getNoOfColumn() {
		Row rw = openSheet.getRow(0);
		return rw.getPhysicalNumberOfCells();
	}

	public int getNoOfColumn(int rowNo) {
		Row rw = openSheet.getRow(rowNo);
		return rw.getPhysicalNumberOfCells();
	}

	public void storeData() {
		Row rw;
		int rowCount = openSheet.getPhysicalNumberOfRows();
		ht.clear();
		for (int i = 1; i < rowCount; i++) {
			rw = openSheet.getRow(i);
			String key = this.getData(0, i);

			List<String> valueList = new ArrayList<String>();
			ht.put(key, valueList);

			for (int j = 1; j <= rw.getPhysicalNumberOfCells(); j++) {
				String data = this.getData(j, i);
				valueList.add(data);
			}
		}

	}

	public Map<String, List<String>> getStoredData() {
		return ht;
	}

	private boolean isXlsx(File fl) {
		String fileName = fl.getName();
		if (fileName.endsWith("xlsx"))
			return true;
		return false;
	}

}
