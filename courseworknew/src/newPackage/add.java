package newPackage;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class add {
	
	public boolean dateCheck(String s, String e, String d){
		String[] date1Test = s.split("/");
		String[] date2Test = s.split("/");
		if (date1Test[2].equals(date2Test[2])){
			int monTest1 = Integer.parseInt(date1Test[1]);
			int monTest2 = Integer.parseInt(date2Test[1]);
			if(monTest2 >= monTest1){
				int dayTest1 = Integer.parseInt(date1Test[0]);
				int dayTest2 = Integer.parseInt(date2Test[0]);
				if(dayTest1 >= dayTest2){
					return true;
				} else {
					return false;
				}
			}
		}
		if(!date1Test[2].equals(date2Test[2])){
			
		}
		return false;}
	
	public void add1(float key, String value){ try{
		BufferedReader addWrite1 = new BufferedReader(new FileReader("C:\\Users\\Jake\\Documents\\GitHub\\new_coursework\\Test files\\origDocTest.txt"));
	
	}catch(Exception a1){}}
	
	public void add2(int dateS, int dateF, int dur){ try{
		BufferedReader addWrite2 = new BufferedReader(new FileReader("C:\\Users\\Jake\\Documents\\GitHub\\new_coursework\\Test files\\secondaryInfo.txt"));
	} catch(Exception a2){}
}}