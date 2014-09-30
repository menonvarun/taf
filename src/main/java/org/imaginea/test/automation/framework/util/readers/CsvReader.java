package org.imaginea.test.automation.framework.util.readers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Util class to read csv files.
 * @author  Varun Menon
 *
 */
public class CsvReader {


	    CSVReader csvReader = null;
	    List<List<String>> rows=null;

	    /**
	     * Creates the CsvReader class object
	     * @param filePath - File path of the csv file to be read
	     * @param separator - Data separator to be used. Default value is ','
	     * @param quotechar - Quote character for the data. Default value is '"'
	     * @throws IOException
	     */
	    public CsvReader(String filePath, char separator, char quotechar) throws IOException{
	        this(new FileReader(filePath),separator,quotechar);
	    }

	    /**
	     * Creates the CsvReader class object
	     * @param inpFile - Input csv file to be read
	     * @param separator - Data separator to be used. Default value is ','
	     * @param quotechar - Quote character for the data. Default value is '"'
	     * @throws IOException
	     */
	    public CsvReader(File inpFile , char separator, char quotechar) throws IOException{
	        this(new FileReader(inpFile),separator,quotechar);
	    }
	    
	    public CsvReader(File inpFile , String separator, String quotechar) throws IOException{
	        this(new FileReader(inpFile),separator.charAt(0),quotechar.charAt(0));
	    }
	    
	    public CsvReader(String filePath) throws IOException{
	        this(new FileReader(filePath),',','"');
	    }
	    
	    public CsvReader(File inpFile) throws IOException{
	        this(new FileReader(inpFile),',','"');
	    }

	    /**
	     * Creates the CsvReader class object
	     * @param reader - Reader object of the file to be read
	     * @param separator - Data separator to be used. Default value is ','
	     * @param quotechar - Quote character for the data. Default value is '"'
	     * @throws IOException
	     */
	    public CsvReader(Reader reader , char separator, char quotechar) throws IOException {
	        csvReader = new CSVReader(reader,separator,quotechar);
	        List<String[]> rowsTemp = csvReader.readAll();
	        rows = new ArrayList<List<String>>();
	        Iterator<String[]> rowsIterator = rowsTemp.iterator();
	        // handling empty lines from csv by adding only non-empty lines to rows
	        while (rowsIterator.hasNext()) {
	            List<String> tempList = Arrays.asList(rowsIterator.next());
	            if (!(tempList.size() == 1 && tempList.get(0).equals(""))) {
	                rows.add(tempList);
	            }
	        }
	    }

	    /**
	     * Gets the data from csv file based on column and row number
	     * @param column Column number
	     * @param row Row number
	     * @return The data at the said position. If the said row or column don't exists then return an empty string
	     */
	    public String getData(int row, int column){
	        String data=null;
	        if(row < 0 && ((row + 1) > rows.size())){
	            return "";
	        }
	        List<String> rowData= rows.get(row);
	        if(column < 0 || (column >= rowData.size())){
	            return "";
	        }

	        data=rowData.get(column);

	        return data;
	    }

	    /**
	     * Get the number of rows in the said csv file
	     * @return Return no of rows present in the said csv file
	     */
	    public int getNoOfRows(){
	        return rows.size();
	    }

	    /**
	     * Return the number of column in the first row of the csv file
	     * @return return the no. of column in the first row of the csv file
	     */
	    public int getNoOfColumn(){
	        return rows.get(0).size();
	    }

	    /**
	     * Returns the number of the column of the said row number passed
	     * @param rowNo - row number for which the number columns needs to be returned.
	     * @return return the no. of column in the specified row of the csv file
	     */
	    public int getNoOfColumn(int rowNo){
	        return rows.get(rowNo).size();
	    }
	


}
