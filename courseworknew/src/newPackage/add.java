package newPackage;

import java.awt.Frame;
import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.Scanner;
import org.apache.commons.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class add {
	
	/** 
	 * Checks to see if the year is the same. If it is not, check to see if the finish date is greater than the start. If not, fail.
	 * Check to see if the month on end is the same as on start. If it isn't, make sure it is greater than the start. Else, fail.
	 * Check to see if the day on end is the same as on start. If it isn't, make sure it is greater. Else, fail.
	 * @param takes values from text boxes to test
	 * @return boolean true or false, which determines if the program should continue
	 * Version 2
	 **/
	public boolean checkForCorrectness(String s, String e, String d){
		
		/**
		 * initialises variables for testing
		 */
		boolean cont = false;
		String[] date1Test = s.split("/");
		String[] date2Test = e.split("/");
		int year1 = Integer.parseInt(date1Test[2]);
		int year2 = Integer.parseInt(date2Test[2]);
		int mon1 = Integer.parseInt(date1Test[1]);
		int mon2 = Integer.parseInt(date2Test[1]);
		int day1 = Integer.parseInt(date1Test[0]);
		int day2 = Integer.parseInt(date2Test[0]);
		/**
		 * Tests inputted fields
		 */
		if (year2 < year1){
			return false; }
		if (year2 > year1){
			return true;
			// Version 2 - checks to see if the duration meets the dates
		}
		if (year2 == year1){
			if(mon2 < mon1){
				return false;}
			else if(mon2 == mon1){
				if(day2 >= day1){
					return true;
				} else if(day2 < day1){
					return false;
			}}else if(mon2 > mon1){
				return true;
				}}
		return false;
	}
	
	public int countNumFullStops(String keyVal){
		int numStops = StringUtils.countMatches(keyVal, ".");
		return numStops;}
	
	public boolean placeCheck(String ID, String desc, String file1, String file2) throws IOException{
		Scanner checkNumber = new Scanner(new FileReader(file1));
		
		String toTest;
		boolean trueOrNo = true;
		while(checkNumber.hasNext()){
	        toTest = checkNumber.next();
	        if(toTest.equals(ID)){
	       	trueOrNo = false;
	       	break;
	        } else { 
	       	addInfo(ID, desc, file1, file2);
	      }
	}
		return trueOrNo;
	}
	
	public void addInfo (String ID, String desc, String file1, String file2) throws IOException{
    int filePosCounter = 0;
	Scanner checkNumber = new Scanner(new FileReader(file1));
	ArrayList<String> key = new ArrayList<String>();
	ArrayList<String> info = new ArrayList<String>();
	String sentInt = "";
	
	/**
	 * Checks to see if the data data already exists in the file.
	 */
	while(checkNumber.hasNext()){
        String testPlaceHolder = checkNumber.next();
        String[] splitter = testPlaceHolder.split(",");
		String toTest = splitter[0];
        int numStops = countNumFullStops(toTest);
        /**
         * Break if duplicate entries are found
         */
        if(ID.equals(toTest)){
        	break;
        }
	}
	
		/**
		 * Initialises variables once checking has completed
		 */
			BufferedReader b1 = new BufferedReader(new FileReader(file1));
			BufferedReader b2 = new BufferedReader(new FileReader(file2));
			String keyLine;
			String infoLine;
			while((keyLine = b1.readLine()) != null){
				key.add(keyLine);
			}
			while((infoLine = b2.readLine()) != null){
				info.add(infoLine);
			}
			
			filePosCounter += 1;
			String[] identifier = ID.split("\\.");
			String numToTest = identifier[identifier.length-1];
			int numStop = countNumFullStops(ID);
			int Sentsize = identifier.length;
			switch(numStop){
			case 0: sentInt = identifier[Sentsize];
					break;
			case 1: sentInt = identifier[Sentsize-1];
					break;
			case 2: sentInt = identifier[Sentsize-2] + "." + identifier[Sentsize-1];
					break;
			case 3: sentInt = identifier[Sentsize-3] + "." + identifier[Sentsize-2] + "." + identifier[Sentsize-1];
					break;
			case 4: sentInt = identifier[Sentsize-4] + "." + identifier[Sentsize-3] + "." + identifier[Sentsize-2] + "." + identifier[Sentsize-1];
					break;
			case 5: sentInt = identifier[Sentsize-5] + "." + identifier[Sentsize-4] + "." + identifier[Sentsize-3] + "." + identifier[Sentsize-2] + "." + identifier[Sentsize-1];
					break;
			}
			
			if(numToTest.equals(1)){
				for(int add=0; add<key.size(); add++){
					filePosCounter += 1;
					String lineToTest = key.get(add);
					String testerInt = null;
					String[] tester = lineToTest.split(",", 2);
					String identifierTemp = tester[0];
					String[] identifierTest = identifierTemp.split(".");
					int size = identifier.length;
					int digitToTest = Integer.parseInt(identifier[size-1]);
					int stopsToTest = countNumFullStops(lineToTest);
					switch(stopsToTest){
					case 0: testerInt = identifier[size];
							break;
					case 1: testerInt = identifier[size-1];
							break;
					case 2: testerInt = identifier[size-2] + "." + identifier[size-1];
							break;
					case 3: testerInt = identifier[size-3] + "." + identifier[size-2] + "." + identifier[size-1];
							break;
					case 4: testerInt = identifier[size-4] + "." + identifier[size-3] + "." + identifier[size-2] + "." + identifier[size-1];
							break;
					case 5: testerInt = identifier[size-5] + "." + identifier[size-4] + "." + identifier[size-3] + "." + identifier[size-2] + "." + identifier[size-1];
							break;
					}
					if(testerInt.equals(sentInt)){
						key.add(filePosCounter+1, testerInt);
						info.add(filePosCounter+1, desc);
						// add rest of info
						
					}
					identifier = null;
					tester = null;
				}
			}
			
			try{
				BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
			for(int add=0; add<key.size(); add++){
				writer.write(key.get(add) + ", " + info.get(add) + "\n");
			}
			writer.close();
			} catch(IOException ex){}
			
			// DO THIS
			// FIGURE OUT WHY IT'S NOT WRITING TO A FILE
			if(!numToTest.equals(1)){
					for(int add=0; add<key.size(); add++){
					String lineToTest = key.get(add);
					int testerInt;
					String[] tester = lineToTest.split(",", 2);
					String identifierTemp = tester[0];
					String[] identifierTest = identifierTemp.split("\\.");
					int size = identifier.length;
					int digitToTest = Integer.parseInt(identifier[size-1]);
					int stopsToTest = countNumFullStops(lineToTest);
					switch(stopsToTest){
					case 0: testerInt = Integer.parseInt(identifier[size]);
							break;
					case 1: testerInt = Integer.parseInt(identifier[size-1]);
							break;
					case 2: testerInt = Integer.parseInt(identifier[size-2] + "." + identifier[size-1]);
							break;
					case 3: testerInt = Integer.parseInt(identifier[size-3] + "." + identifier[size-2] + "." + identifier[size-1]);
							break;
					case 4: testerInt = Integer.parseInt(identifier[size-4] + "." + identifier[size-3] + "." + identifier[size-2] + "." + identifier[size-1]);
							break;
					case 5: testerInt = Integer.parseInt(identifier[size-5] + "." + identifier[size-4] + "." + identifier[size-3] + "." + identifier[size-2] + "." + identifier[size-1]);
							break;
					}
					identifier = null;
					tester = null;
				}}try{
					BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
					for(int add=0; add<key.size(); add++){
						writer.write(key.get(add) + ", " + info.get(add) + "\n");
					}
					writer.close();
					} catch(IOException ex){}}}