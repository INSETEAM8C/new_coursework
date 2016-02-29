import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class remove {

	public boolean checkForRemove(String ID, String file1, String file2) throws IOException{
		Scanner checkNumber = new Scanner(new FileReader(file1));
		String toTest;
		boolean trueOrNo = false;
		while(checkNumber.hasNext()){
			toTest = checkNumber.next();     
			String s = toTest.replaceAll("[^\\d.]", "");
			if(s.matches(ID)){     
	       	trueOrNo = true;
	       	removeFromFile(ID, file1, file2);
	       	break;
	        }}
			return trueOrNo;
	}
	
	public void removeFromFile(String ID, String file1, String file2) throws IOException{
		BufferedReader b1 = new BufferedReader(new FileReader(file1));
		BufferedReader b2 = new BufferedReader(new FileReader(file2));
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<String> info = new ArrayList<String>();
		String keyLine;
		String infoLine;
		while((keyLine = b1.readLine()) != null){
			key.add(keyLine);
		}
		while((infoLine = b2.readLine()) != null){
			info.add(infoLine);
		}
		
		for(int i=0; i<key.size(); i++){
			String t = key.get(i);
			String tester = t.replaceAll("[^\\d.]", "");
			if(tester.matches(ID)){
				key.remove(t);
				info.remove(i);
			}
		}
	
			try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(file2));
			for(int add=0; add<key.size(); add++){
				writer.write(key.get(add) + "\r\n");
				writer2.write(info.get(add) + "\r\n");
			}
			
			writer.close();
			writer2.close();
			
			} catch(IOException ex){}
}}