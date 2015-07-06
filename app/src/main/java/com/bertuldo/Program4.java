package com.bertuldo;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import com.bertuldo.ValueModel;
import com.bertuldo.Processor;
 
public class Program4 {
    public static void main(String[] args) throws IOException{
        String         filePath = "data.csv";
        Program4       mavenEx1 = new Program4();
	Processor      processor= new Processor();
        Scanner        scanner  = new Scanner(System.in);
	boolean        run      = true;
	int            choice   = 0;
        List<String[]> rows     = processor.readCsv(filePath);
	List<String>   searchedList;
	ArrayList<ArrayList<ValueModel>> modeledValues;

	do {
            mavenEx1.display(rows);
            System.out.print("Press and enter \"1\" Search, \"2\" Show Ascending, \"3\" Show Descending, \"4\" Edit, \"5\" Exit : ");
            choice = processor.integerChecker(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter a string : ");
                    String stringToSearch = scanner.nextLine();
                    searchedList = processor.searchString(rows, stringToSearch);
		    mavenEx1.searchDisplay(searchedList);
                    break;
                case 2:
                    modeledValues = processor.sort(rows, true);
		    mavenEx1.displaySort(modeledValues);
                    break;
                case 3:
                    modeledValues = processor.sort(rows, false);
		    mavenEx1.displaySort(modeledValues);
                    break;
                case 4:
                    mavenEx1.editInput(scanner, rows, filePath, processor);
                    break;
		case 5:
		    run=false;
		    break;
		default:
		    System.out.println("Please choose 1 to 5 :");
		    break;
            }
        } while (run);
    }

    private void display(List<String[]> rows) {
        for(String[] column : rows){
	    for(int i = 0; i < column.length; i+=2) {
		if(column.length==(i+1)){
		     System.out.print("(" + column[column.length-1] +")");
		}else{
		 System.out.print("(" + column[i] + ","+ column[i+1] + ")");
		}
	    }
	    System.out.println();
         }
    }

    public void searchDisplay(List<String> searchedList){
	for(String searched : searchedList){
	    System.out.println(searched);
	}
    }

    public void editInput(Scanner scanner, List<String[]> rows,  String filePath, Processor processor) throws IOException{
	int setRow      = 0;
        int setColumn   = 0;
        int valueNumber = 0;
        String newValue = "";

	System.out.println("Enter row number: ");
        setRow = processor.integerChecker(scanner);
        System.out.println("Enter column number: ");
        setColumn = processor.integerChecker(scanner);
        do {
            System.out.println("Enter 1 or 2 :");
            valueNumber = processor.integerChecker(scanner);
        } while (valueNumber < 1 || valueNumber > 2);
        scanner.nextLine();
        System.out.println("Enter the new value :");
        newValue = scanner.nextLine();
	processor.editValue(rows, filePath, setRow, setColumn, valueNumber, newValue);
    }

    public void displaySort(ArrayList<ArrayList<ValueModel>> modeledValues){
	for (ArrayList<ValueModel> values : modeledValues) {
            for (ValueModel value : values) {
                if (value != values.get(values.size() - 1)) {
                    System.out.print("(" + value.getValue1() + ""+ value.getValue2() + "),");
                } else {
                    System.out.print("(" + value.getValue1() + ""+ value.getValue2() + ")");
                }
            }
            System.out.println();
        }
	System.out.println();
    }
}
