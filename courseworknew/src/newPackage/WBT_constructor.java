/** This class is responsible for creating the WBT 
 * @ Author: 
 * @ Version: 1.0 - button functionality.
 * 
 * Changes to be made for version 1.1:
 * - Calendar to be added
 * - WBT/PERT/GANTT to modelled based on common data
 * 
 * */
// Things to do:
// - Fix add button formatting 
// - Fix add button, so that '.2' and null point activities can be modelled
// - Fix refresh button, so that it displays new addition entries
// - Fix remove button, so that dates and durations are removed (by passing new info)

/**
 * A list of all imports necessary
 */
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;

//import org.apache.commons.lang.StringUtils;

public class WBT_constructor{

	private JFrame frame;
	private JTextField txtID;
	private JTextField txtDesc;
	private JTextField startDate;
	private JTextField endDate;
	private JTextField dur;
	private ArrayList<String> WithoutPrecInfo = new ArrayList<String>();
	private ArrayList<String> withFullStopsInfo = new ArrayList<String>();
	private ArrayList<String> numWithoutPrec = new ArrayList<String>();
	private ArrayList<String> afterFullStops = new ArrayList<String>();
	private ArrayList<Integer> coordinates = new ArrayList<Integer>();
	private JTextField txtTaskId;
	private JTextField txtDescription;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private JTextField txtDuration;
	private JButton btnAdd;
	private File origFile = new File("C:\\Users\\Jake\\Documents\\GitHub\\new_coursework\\Test files\\origDocTest.txt");
	private File infoFile = new File("C:\\Users\\Jake\\Documents\\GitHub\\new_coursework\\Test files\\secondaryInfo.txt");
	private String file1 = origFile.toString();
	private String file2 = infoFile.toString();
	private formatMethod FM = new formatMethod();
	private mainMenu main = new mainMenu();
	private JPanel panel_1;
	private JButton btnHome;
	private JButton btnCreate;
	private add a = new add();
	private remove r = new remove();
	private Graphics g;
	
	/**
	 * Set up the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WBT_constructor window = new WBT_constructor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}});}

	/**
	 * Launch the application.
	 */
	public WBT_constructor() {
		initialize();
	}

	/**
	 * Initialises the contents of the frame, to the dimensions of the user's window and the variables responsible for correct data manipulation
	 * @return opens "WBT creator"
	 */
	
	private void initialize() {	
		/**
		 * Initialises variables and formatted text variables
		 */		
		//JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame = new JFrame("WBT creator");
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		//frame.setContentPane(pane);
		frame.getContentPane().setBackground(new Color(153, 180, 209));
		frame.setBounds(0, 0, size.width, size.height-40);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	/**
	 * Creates the JPanel responsible for showing the user the graph
	 */		
		//rectCreate WBTpanel = new rectCreate();
		//WBTpanel.setBounds(10, 11, 1330, 304);
		//frame.getContentPane().add(WBTpanel);
		//WBTpanel.setAutoscrolls(true);
		
		/**
		 * Creates the text pane responsible for providing information on all processes hosted in host files. 
		 */		
		TextArea WBTTaskTextArea = new TextArea("Press refresh to get all info from your files.");
		WBTTaskTextArea.setBounds(10, 359, 1330, 162);
		WBTTaskTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(WBTTaskTextArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
		frame.getContentPane().add(WBTTaskTextArea);
		
		/**
		 * Initiates values for combo boxes and other vital data
		 */
		String[] availableMetric = { "Minutes", "Hours", "Days", "Weeks", "Months" };
		JComboBox<Object> dur_metric = new JComboBox<Object>(availableMetric);
		dur_metric.setBounds(1001, 582, 70, 20);
		frame.getContentPane().add(dur_metric);
		
	/**
	 * Creates buttons for allowable actions.
	 */
		
	/**
	 * Add button checks the file for correctness and duplicity, and then passes 
	 * @Return returns added data to the files
	 */
	btnAdd = new JButton("Add task");
	btnAdd.addActionListener(new ActionListener() {
		// checkForCorrectness();
		public void actionPerformed(ActionEvent e) {
			String ID = txtID.getText();
			String DescTemp = txtDesc.getText();
			String start = startDate.getText();
			String end = endDate.getText();
			String duration = dur.getText() + " " + dur_metric.getSelectedItem().toString();
			// compare the split against the other result to check if the date is correct
			
			/**
			 * Performs preliminary checks on the data, and assigns variables for manipulation if:
			 * 		- No fields are left blank
			 * 		- No fields contain any illegal characters
			 */
			
			if(ID.equals("")){
				JOptionPane.showMessageDialog(frame, "Your ID value is empty. Please enter an ID, and try again");
				return;
			} else { ID = txtID.getText();
			if(ID.matches("[a-zA-Z]")){
				JOptionPane.showMessageDialog(frame, "Your ID value contains letters. Please try again");
				return;
			}}
		if(txtDesc.getText().equals("")){
				JOptionPane.showMessageDialog(frame, "Your description field is empty. Please try again");
				return;
			}
		if(start.equals("")){
				JOptionPane.showMessageDialog(frame, "Your start date field is empty. Please try again");
				return;
		} if (start.matches("[a-zA-Z]")){
				JOptionPane.showMessageDialog(frame, "Your start date field contains letters. Please try again");
				return;
		}
		if(end.equals("")){
				JOptionPane.showMessageDialog(frame, "Your end date field is empty. Please try again");
				return;	
		} if(end.matches("[a-zA-Z]")){
			JOptionPane.showMessageDialog(frame, "Your end date field contains letters. Please try again");
			return;
		}
		if(duration.equals("")){
				JOptionPane.showMessageDialog(frame, "Your duration field is empty. Please try again");
				return;
		} 
		if(a.checkForCorrectness(start, end, duration) == false){
			JOptionPane.showMessageDialog(frame, "there's something wrong with your dates, please try again");
		} else {
			try {
				String infoString = startDate.getText() + ", " + endDate.getText() + ", " +  " " + dur.getText() + " " + dur_metric.getSelectedItem();
				if(a.placeCheck(ID, DescTemp, infoString, file1, file2) == false){
					JOptionPane.showMessageDialog(frame, "That already exists. Please use the 'edit' button if you wish to edit. ");
				} else {
					JOptionPane.showMessageDialog(frame, "Great. That's been added. ");
				}
			} catch (FileNotFoundException e1) {}
			//a.add1(ID, DescTemp);
			//a.add2(start, end, duration);
 catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}}});
	
		// - add1(StringIDTemp, DescTemp)
		// - check the rest of the dates, and check for correctness
		// - find the index of the file
		// - add to the file at the correct index
		// - to do this (^^^), count the number of times a reader has to read over file1, then do a loop for file2 to add the rest of the
		//   information. Look at the link on how to find a specific location in the file.s
	btnAdd.setBounds(10, 532, 114, 23);
	frame.getContentPane().add(btnAdd);{}

	/**
	 * Responsible for saving an image of the created chart from the Jframe once it has been initialised
	 *@returns returns an image, saved to the user's hard disk.
	 */
	JButton btnSave = new JButton("Save image");
	btnSave.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");
		}
	});
	
	/**
	 * Shortcut to take the user home
	 * @returns returns home screen
	 */
	btnHome = new JButton("Home");
	btnHome.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	});
	btnHome.setBounds(10, 325, 70, 23);
	frame.getContentPane().add(btnHome);
	
	/**
	 * Creates the chart for the page the user is currently on
	 * @version version 1 - with bugs
	 */
	btnCreate = new JButton("Create chart");
	btnCreate.addMouseListener(new MouseAdapter() {
		@SuppressWarnings("resource")
		@Override
		public void mouseClicked(MouseEvent e){
			
			/**
			 * Initialises variables responsible for creation
			 */
			int oneCount = 0, twoCount = 0, threeCount = 0, fourCount = 0, fiveCount = 0, sixCount = 0, sevenCount = 0, eightCount = 0, nineCount = 0, size = 0, spaceFromEdge = 0;
		//	float keyInput;
			
			/**
			 * Setup for counting the number of full stops in each occurance of the variable, and updating the array with all numbers that don't have any successing full stops. 
			 * Test for validation
			 */

			try{
				WithoutPrecInfo.clear();
				withFullStopsInfo.clear();
				numWithoutPrec.clear();
				afterFullStops.clear();
				coordinates.clear();
				String currentLine;
				BufferedReader WBTscan1 = new BufferedReader(new FileReader(origFile));
				BufferedReader WBTscan2 = new BufferedReader(new FileReader(infoFile));
				while((currentLine = WBTscan1.readLine()) != null){
					String[] numWithoutFullArr = currentLine.split(",", 2);
					String keyVal = numWithoutFullArr[0];
					if(a.countNumFullStops(keyVal) == 0){
					//keyInput = Float.parseFloat(keyVal);
						numWithoutPrec.add(keyVal);
					//numWithoutPrecFloat.add(keyVal);
						WithoutPrecInfo.add(numWithoutFullArr[1]);
					} else if(a.countNumFullStops(keyVal) >= 1){
					if(a.countNumFullStops(keyVal) <= 5){
					//keyInput = Float.parseFloat(keyVal);
					afterFullStops.add(keyVal);
					//afterFullStopsFloat.add(keyVal);
					withFullStopsInfo.add(numWithoutFullArr[1]);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "You have exceeded the maximum number of full stops in one of your tasks" + " " + keyVal + " Please correct this and retry.");
						}
			}} catch(FileNotFoundException fe){} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int tempx = 0;
			float xToAdd = 0.0f;
			size = numWithoutPrec.size();
			spaceFromEdge = ((1200 - (size * 100) - (size - 1) * 25)) / 2;
			for(int coords=0; coords<size; coords++){
				if(coords == 0){
					xToAdd = (coords * 100) + spaceFromEdge;
					int rounded = Math.round(xToAdd);
					coordinates.add(rounded);
					} else {
						xToAdd = (coords * 25 + ((coords * 100) + spaceFromEdge));
						int rounded = Math.round(xToAdd);
						coordinates.add(rounded);
						}};
			
//			for(int pl1=0; pl1<afterFullStops.size(); pl1++){
//				String prePlot = afterFullStops.get(pl1);
//				String infoPlot = withFullStopsInfo.get(pl1);
//				char preTest = prePlot.charAt(0);
//				int globalOne;
//				switch (preTest) {
//					case 1: oneCount += 1;
//							globalOne = oneCount;
//					case 2: twoCount += 1;
//							globalOne = twoCount;
//					case 3: threeCount += 1;
//							globalOne = threeCount;
//					case 4: fourCount += 1;
//							globalOne = fourCount;
//					case 5: fiveCount += 1;
//							globalOne = fiveCount;
//					case 6: sixCount += 1;
//							globalOne = sixCount;
//					case 7: sevenCount += 1;
//							globalOne = sevenCount;
//					case 8: eightCount += 1;
//							globalOne = eightCount;
//					case 9: nineCount += 1;
//							globalOne = nineCount;
//				break;
//				}
//				
//				switch (a.countNumFullStops(prePlot)) {
//					case 1: tempx += 0;
//					case 2: tempx += 5;
//					case 3: tempx += 10;
//					case 4: tempx += 15;
//					case 5: tempx += 20;
//				break;
//				}
//				
//				//WBTpanel.paint(g, 10, 10, "jidsj");
//				//plot(coordinates[preTest] + tempx, globalOne * 100 + 25, coordinates[preTest] + tempx + 100, globalOne * 100 + 125)
//				//add text to rectangle
//				//show
//				globalOne = 0;
//			}
		//JFrame chartCreate = new JFrame();
		rectCreate newPanel = new rectCreate();
		newPanel.setBounds(0, 0, 1200, 1200);
		//chartCreate.setBounds(0, 0, 1210, 1210);
		//chartCreate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//chartCreate.getContentPane().setLayout(null);
		frame.getContentPane().add(newPanel);
		frame.setVisible(true);
		frame.repaint();
	}});
			
	btnCreate.setBounds(1226, 325, 114, 23);
	frame.getContentPane().add(btnCreate);
	
	btnSave.setBounds(90, 325, 114, 23);
	frame.getContentPane().add(btnSave);

	/**
	 * Button which, when pressed, gains information stored in both the key and the info files, puts them together and displays them in a well-formatted manner
	 * 
	 * To be split into different methods in version 2
	 * 
	 * @return returns a populated text area.
	 */
	JButton btnRefresh_1 = new JButton("Refresh");
	btnRefresh_1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		WBTTaskTextArea.setText(null);
		WBTTaskTextArea.setText("ID:      Task Desc:                                                                 Start date:                                    End date:                                    Duration:            ");
		try{
			ArrayList<String> key = new ArrayList<String>();
			ArrayList<String> info = new ArrayList<String>();
			BufferedReader b1 = new BufferedReader(new FileReader(origFile));
			BufferedReader b2 = new BufferedReader(new FileReader(infoFile));
			String keyLine;
			String infoLine;
			while((keyLine = b1.readLine()) != null){
				key.add(keyLine);
			}
			while((infoLine = b2.readLine()) != null){
				info.add(infoLine);
			}
			int keyLen = key.size();
			for(int toLoop = 0; toLoop <= keyLen; toLoop++){
				String k = key.get(toLoop);
				k = FM.format1(k);
				String i = info.get(toLoop);
				i = FM.format2(i);
				WBTTaskTextArea.append("\n" + k);
				WBTTaskTextArea.append(i);
		}}catch (Exception RefreshProblem){}
	}});
	btnRefresh_1.setBounds(1226, 532, 114, 23);
	frame.getContentPane().add(btnRefresh_1);
	
	JButton btnEdit = new JButton("Edit task");
	btnEdit.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");
		}
	});
	btnEdit.setBounds(10, 623, 114, 23);
	frame.getContentPane().add(btnEdit);
		
	/**
	 * Finds the location of a certain line in a file, and removes all information about it.
	 */
	JButton btnRemove = new JButton("Remove task");
	btnRemove.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			String ID = txtID.getText();
			
			/**
			 * Performs preliminary checks on the data, and assigns variables for manipulation if:
			 * 		- No fields are left blank
			 * 		- No fields contain any illegal characters
			 */
			if(ID.equals("")){
				JOptionPane.showMessageDialog(frame, "You need to supply an ID to remove a line of text.");
				return;
			} else { ID = txtID.getText();
			try {
				if(r.checkForRemove(ID, file1, file2) == false){
					JOptionPane.showMessageDialog(frame, "Sorry, but that entity does not exist. Please try again (Hint: Press Refresh for all available tasks)");
				} else {
					JOptionPane.showMessageDialog(frame, "Great. That's been removed.");
				};
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}});
	btnRemove.setBounds(138, 623, 114, 23);
	frame.getContentPane().add(btnRemove);
	
	/** 
	 * Initiates text fields and descriptions for the purposes of aesthetics.
	 */
	txtID = new JTextField();
	txtID.setBounds(10, 582, 86, 20);
	frame.getContentPane().add(txtID);
	txtID.setColumns(10);
		
	txtDesc = new JTextField();
	txtDesc.setColumns(10);
	txtDesc.setBounds(116, 582, 425, 20);
	frame.getContentPane().add(txtDesc);
		
	dur = new JTextField();
	dur.setColumns(10);
	dur.setBounds(969, 581, 32, 21);
	frame.getContentPane().add(dur);
		
	txtTaskId = new JTextField();
	txtTaskId.setText("Task ID");
	txtTaskId.setEditable(false);
	txtTaskId.setBorder(null);
	txtTaskId.setBackground(new Color(0,0,0,0));
	txtTaskId.setBounds(10, 566, 86, 20);
	frame.getContentPane().add(txtTaskId);
	txtTaskId.setColumns(10);
		
	txtDescription = new JTextField();
	txtDescription.setText("Task description");
	txtDescription.setEditable(false);
	txtDescription.setBorder(null);
	txtDescription.setBackground(new Color(0,0,0,0));
	txtDescription.setColumns(10);
	txtDescription.setBounds(116, 566, 94, 20);
	frame.getContentPane().add(txtDescription);
		
	txtStartDate = new JTextField();
	txtStartDate.setText("Start date");
	txtStartDate.setEditable(false);
	txtStartDate.setBorder(null);
	txtStartDate.setBackground(new Color(0,0,0,0));
	txtStartDate.setColumns(10);
	txtStartDate.setBounds(551, 558, 86, 36);
	frame.getContentPane().add(txtStartDate);
		
	txtEndDate = new JTextField();
	txtEndDate.setText("End date");
	txtEndDate.setEditable(false);
	txtEndDate.setBorder(null);
	txtEndDate.setBackground(new Color(0,0,0,0));
	txtEndDate.setColumns(10);
	txtEndDate.setBounds(759, 558, 86, 36);
	frame.getContentPane().add(txtEndDate);
		
	txtDuration = new JTextField();
	txtDuration.setText("Duration");
	txtDuration.setEditable(false);
	txtDuration.setBorder(null);
	txtDuration.setBackground(new Color(0,0,0,0));
	txtDuration.setColumns(10);
	txtDuration.setBounds(969, 558, 86, 36);
	frame.getContentPane().add(txtDuration);
		
	endDate = new JTextField();
	endDate.setColumns(10);
	endDate.setBounds(759, 581, 200, 21);
	frame.getContentPane().add(endDate);			
		
	startDate = new JTextField();
	startDate.setColumns(10);
	startDate.setBounds(551, 581, 200, 21);
	frame.getContentPane().add(startDate);}

class rectCreate extends JPanel
{
	public rectCreate()
	{
		setSize(1000, 1000);
		setVisible(true);
	}
	
	/**
	 * Paints information from the file to a JFrame
	 * 
	 * @Version 1.0
	 */
	@SuppressWarnings({ "null", "null" })
	public void paint(Graphics g)
	{
		String info, prePlot, infoPlot, previous;
		Integer coords=0, oneCount=0, twoCount=0, threeCount=0, fourCount=0, fiveCount=0, 
		sixCount=0, sevenCount=0, eightCount=0, nineCount=0, tempx=0, globalOne=0, y=0, coordConstant,
		count=0;
		int size = numWithoutPrec.size();
		System.out.println(size);
		int size1 = afterFullStops.size();
		for(int i=0; i<size; i++){
			info = numWithoutPrec.get(i) + " " + WithoutPrecInfo.get(i);
			String[] words = info.split(" ");
			String tempTest = null;
			StringBuilder editedS = new StringBuilder();
			int infoSize = words.length;
			if(infoSize >= 3){
				for(int form=0; form<infoSize; form++){
				if(form%3 != 0){
				tempTest = words[form] + " ";
				editedS.append(tempTest);
				} else {
				tempTest = words[form] + "\n";
				editedS.append(tempTest);
				}}}
			for(int split=0; split<words.length; split++){
				String toSplitOrNotToSplit = words[split];
				if(toSplitOrNotToSplit.length() > 14){
					String one = toSplitOrNotToSplit.substring(0, 14);
					String two = toSplitOrNotToSplit.substring(14, toSplitOrNotToSplit.length());
					editedS.delete(0, editedS.length());
					editedS.append(words[0] + "\n");
					editedS.append(one + "\n");
					editedS.append(two);
				}}
			info = " ";
			info = editedS.toString();
			coords = coordinates.get(i);
			g.setColor(Color.black);
			g.drawRect(coords, 10, 100, 60);
			g.setColor(Color.red);
			g.fillRect(coords+1, 11, 99, 59);
			g.setColor(Color.black);
			for(String line : info.split("\n")){
				count += 1;
				if(count == 1){
				g.setFont(new Font("default", Font.BOLD, 13));
				g.drawString(line, coords+5, 30);
				} else if(count == 2){
				g.setFont(new Font("default", Font.PLAIN, 11));					
				g.drawString(line, coords+5, 50);
				} else if(count == 3){
				g.drawString(line, coords+5, 65);
				} else if(count == 4){
				g.drawString(line, coords+5, 80);
				}
			} count = 0;
		} for(int i2=0; i2<size1; i2++){
			if(i2 == 0){
			prePlot = afterFullStops.get(i2);
			infoPlot = withFullStopsInfo.get(i2);
			String[] testerNum = prePlot.split("\\.");
			String tester1 = testerNum[0];
			coordConstant = Integer.parseInt(tester1);
			} else { 
			prePlot = afterFullStops.get(i2);
			infoPlot = withFullStopsInfo.get(i2);
			previous = afterFullStops.get(i2-1);
			String[] previousTest1 = previous.split("\\.");
			String testerPre = previousTest1[0];
			String[] previousTest2 = prePlot.split("\\.");
			String testerPre2 = previousTest2[0];
			coordConstant = Integer.parseInt(testerPre2);
			if(!testerPre2.equals(testerPre)){
				globalOne = 0;
			}
			switch (coordConstant) {
				case 1: oneCount += 1;
						globalOne = oneCount;
						break;
				case 2: twoCount += 1;
						globalOne = twoCount;
						break;
				case 3: threeCount += 1;
						globalOne = threeCount;
						break;
				case 4: fourCount += 1;
						globalOne = fourCount;
						break;
				case 5: fiveCount += 1;
						globalOne = fiveCount;
						break;
				case 6: sixCount += 1;
						globalOne = sixCount;
						break;
				case 7: sevenCount += 1;
						globalOne = sevenCount;
						break;
				case 8: eightCount += 1;
						globalOne = eightCount;
						break;
				case 9: nineCount += 1;
						globalOne = nineCount;
						break;
			}
			switch (a.countNumFullStops(prePlot)) {
				case 1: tempx += 0;
						break;
				case 2: tempx += 5;
						break;
				case 3: tempx += 10;
						break;
				case 4: tempx += 15;
						break;
				case 5: tempx += 20;
						break;
			}
			info = afterFullStops.get(i2) + " " + withFullStopsInfo.get(i2);
			
			String[] words = info.split(" ");
			String tempTest = null;
			StringBuilder editedS = new StringBuilder();
			int infoSize = words.length;
			if(infoSize >= 3){
				for(int form=0; form<infoSize; form++){
				if(form%3 != 0){
				tempTest = words[form] + " ";
				editedS.append(tempTest);
				} else {
				tempTest = words[form] + "\n";
				editedS.append(tempTest);
				}}}
			for(int split=0; split<words.length; split++){
				String toSplitOrNotToSplit = words[split];
				if(toSplitOrNotToSplit.length() > 14){
					String one = toSplitOrNotToSplit.substring(0, 14);
					String two = toSplitOrNotToSplit.substring(14, toSplitOrNotToSplit.length());
					editedS.delete(0, editedS.length());
					editedS.append(words[0] + "\n");
					editedS.append(one + "\n");
					editedS.append(two);
				}
			}
			info = " ";
			info = editedS.toString();
			System.out.println(info);
			//} else if(words[1].length() >= 14){
				//String one = tempTest.substring(0, 14);
			//}
			y = (globalOne * 80);
			coords = coordinates.get(coordConstant-1);
			g.setColor(Color.black);
			g.drawRect(coords + tempx, y, 100, 60);
			g.setColor(Color.red);
			g.fillRect(coords + tempx+1, y+1, 99, 59);
			g.setColor(Color.black);
			for(String line : info.split("\n")){
			count += 1;
			if(count == 1){	
			g.setFont(new Font("default", Font.BOLD, 13));
			g.drawString(line, coords+5, y+20);
			} else if(count == 2){
			g.setFont(new Font("default", Font.PLAIN, 11));
			g.drawString(line, coords+5, y+40);
			} else if(count == 3){
			g.drawString(line, coords+5, y+55);
			} else if(count == 4){
			g.drawString(line, coords+5, y+70);
			}
		} count = 0;}}}}}