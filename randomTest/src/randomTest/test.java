package randomTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class test {
	try{
	private File origFile = new File("C:\\Users\\Jake\\Documents\\GitHub\\new_coursework\\Test files\\origDocTest.txt");
	String file2 = origFile.toString();
	BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
	public static void main(String[] args){
		String s = "1.1";
		String[] sSplit = s.split("\\.");
		System.out.println(Arrays.toString(sSplit));
	}} catch(IOException e){}