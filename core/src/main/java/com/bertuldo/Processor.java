package com.bertuldo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.bertuldo.ValueModel;
import com.bertuldo.AscendingComparator;
import com.bertuldo.DescendingComparator;

public class Processor {
    public List<String[]> readCsv(String filePath) throws IOException {
	CSVReader reader = new CSVReader(new FileReader(filePath));
	return reader.readAll();
    }

    public int integerChecker(Scanner scanner) {
        int number = -1;
        do {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Must be a number");
                scanner.nextLine();
            }
        } while (number < 0);
        return number;
    }

    public List<String> searchString(List<String[]> rows , String stringToSearch) {
	List<String> searchedList = new ArrayList<String>();

	for(int j = 0; j < rows.size(); j++) {
            String[] column = rows.get(j);
            for(int i = 0; i < column.length; i+=2) {
		if(column.length==(i+1)){
        	    int counter1 = 0;
		    for (int lastIndex = 0; lastIndex != -1;) {
                        lastIndex = column[i].indexOf(stringToSearch, lastIndex);
                        if (lastIndex != -1) {
                            counter1++;
                            lastIndex += stringToSearch.length();
                        }
                    }
                    if (counter1 != 0) {
                        searchedList.add(j + ", "+ (i/2) + " - "+ counter1 + " instance of "+
                        stringToSearch+ " at first Value");
                    }
		}else{
		    int counter1 = 0;
                    int counter2 = 0;
                    for (int lastIndex = 0; lastIndex != -1;) {
                        lastIndex = column[i].indexOf(stringToSearch, lastIndex);
                        if (lastIndex != -1) {
                            counter1++;
                            lastIndex += stringToSearch.length();
                        }
                    }
                    for (int lastIndex = 0; lastIndex != -1;) {
                        lastIndex = column[i+1].indexOf(stringToSearch, lastIndex);
                        if (lastIndex != -1) {
                            counter2++;
                            lastIndex += stringToSearch.length();
                        }
                    }
                    if (counter1 != 0) {
                        searchedList.add(j + ", "+ (i/2) + " - "+ counter1 + " instance of "+
                        stringToSearch+ " at first Value");
                    }
                    if (counter2 != 0) {
                        searchedList.add(j + ", "+ (i/2) + " - "+ counter2 + " instance of " +
                        stringToSearch+ " at second Value");
                    }
		}
	    }
        }
	return searchedList;
    }
    
    public ArrayList<ArrayList<ValueModel>> sort(List<String[]> rows, boolean ascend) {
	ArrayList<ArrayList<ValueModel>> modeledValues = new ArrayList<ArrayList<ValueModel>>();

	for(String[] column : rows){
	    ArrayList<ValueModel> line = new ArrayList<ValueModel>();
	    for(int i = 0; i < column.length; i+=2) {
		ValueModel valueStorage = new ValueModel();
		if (column.length==(i + 1)){
		    valueStorage.setValue1(column[column.length - 1]);
		    valueStorage.setValue2("");
                    line.add(valueStorage);
		} else {
		    valueStorage.setValue1(column[i]);
		    valueStorage.setValue2(column[i + 1]);
                    line.add(valueStorage);
		}
	    }
	    if (ascend){
	        Collections.sort(line, new AscendingComparator());
	    } else {
		Collections.sort(line, new DescendingComparator());
	    }
	    modeledValues.add(line);
        }
	return modeledValues;
    } 

    public void editValue(List<String[]> rows, String filePath, int setRow, int setColumn,
                          int valueNumber, String newValue) throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        
	if (setRow < rows.size()){
	    String [] column = rows.get(setRow);
	    if ((setColumn*2) < column.length) {
		rows.get(setRow)[((setColumn*2)+valueNumber-1)]=newValue;
	    }
	}
        writer.writeAll(rows);
        writer.close();
    } 
}


