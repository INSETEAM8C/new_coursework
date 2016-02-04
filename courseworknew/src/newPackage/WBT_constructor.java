/** This class is responsible for creating the WBT 
 * @ Author: Jake Hedges
 * @ Version: 1.0 - button functionality.
 * 
 * Changes to be made for version 1.1:
 * - Calendar to be added
 * - WBT/PERT/GANTT to modelled based on common data
 * 
 * */
// Things to do:
// - Add text formatter to ID box
// - Add date input
// - implement rest of buttons
// - code buttons, basically
// - properly JDoc 
// - Create testing class
// - Upload to GitHub

package newPackage;

/**
 * A list of all imports necessary
 */
import java.awt.EventQueue;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;

public class WBT_constructor{

	private JFrame frame;
	private JTextField txtID;
	private JTextField txtDesc;
	private JTextField startDate;
	private JTextField endDate;
	private JTextField dur;
	private JTextField txtTaskId;
	private JTextField txtDescription;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private JTextField txtDuration;
	private JButton btnAdd;
	private File origFile = new File("C:\\Users\\Jake\\Documents\\GitHub\\coursework\\8C_Charts\\coursework\\Test files\\origDocTest.txt");
	private File infoFile = new File("C:\\Users\\Jake\\Documents\\GitHub\\coursework\\8C_Charts\\coursework\\Test files\\secondaryInfo.txt");
	private formatMethod FM = new formatMethod();
	private mainMenu main = new mainMenu();
	private JPanel panel_1;
	private JButton btnHome;
	private JButton btnCreate;
	private add a = new add();
	
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
			}
		});
	}

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
		frame = new JFrame("WBT creator");
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		frame.getContentPane().setBackground(new Color(153, 180, 209));
		frame.setBounds(0, 0, size.width, size.height-40);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	/**
	 * Creates the JPanel responsible for showing the user the graph
	 */		
		JPanel WBTpanel = new JPanel();
		WBTpanel.setBounds(10, 10, 1330, 304);
		WBTpanel.setBackground(Color.gray);
		frame.getContentPane().add(WBTpanel);
		WBTpanel.setAutoscrolls(true);
		
		/**
		 * Creates the text pane responsible for providing information on all processes hosted in host files. 
		 */		
		TextArea WBTTaskTextArea = new TextArea("Press refresh to get all info from your files.");
		WBTTaskTextArea.setBounds(10, 359, 1330, 162);
		WBTTaskTextArea.setEditable(false);
		frame.getContentPane().add(WBTTaskTextArea);
		
		/**
		 * Initiates values for combo boxes and other vital data
		 */
		String[] availableMetric = { "Minute(s)", "Hour(s)", "Day(s)", "Week(s)", "Month(s)" };
		JComboBox<?> dur_metric = new JComboBox<Object>(availableMetric);
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
			String IDTemp = txtID.getText();
			String DescTemp = txtDesc.getText();
			String start = startDate.getText();
			String end = endDate.getText();
			String duration = dur.getText() + " " + dur_metric.getSelectedItem().toString();
			float ID = 0.0f;
			JOptionPane.showMessageDialog(frame, duration.split(" "));
			// compare the split against the other result to check if the date is correct
			
			/**
			 * Performs preliminary checks on the data, and assigns variables for manipulation if:
			 * 		- No fields are left blank
			 * 		- No fields contain any illegal characters
			 */
			
			if(IDTemp.equals("")){
				JOptionPane.showMessageDialog(frame, "Your ID value is empty. Please enter an ID, and try again");
				return;
			} else { IDTemp = txtID.getText();
			if(IDTemp.matches("[a-zA-Z]")){
				JOptionPane.showMessageDialog(frame, "Your ID value contains letters. Please try again");
				return;
			} else {
				ID = Float.parseFloat(IDTemp);
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
		if(a.dateCheck(end, start, duration) == false){
			JOptionPane.showMessageDialog(frame, "there's something wrong with your dates, please try again");
		} else {
			int startAdd = Integer.parseInt(start);
			int endAdd = Integer.parseInt(end);
			int durAdd = Integer.parseInt(duration);
			a.add1(ID, DescTemp);
			a.add2(startAdd, endAdd, durAdd);
		}
		
		}});
	
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
	 */
	btnCreate = new JButton("Create chart");
	btnCreate.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e){
			
			/**
			 * Initialises variables responsible for creation
			 */
			ArrayList<Integer> numWithoutPrec = new ArrayList<Integer>();
			ArrayList<Float> coordinates = new ArrayList<Float>();
			int size = 0;
			int spaceFromEdge = 0;
			Scanner WBTscan1 = new Scanner("C:\\Users\\Jake\\Documents\\GitHub\\coursework\\8C_Charts\\coursework\\Test files\\origDocTest.txt");
			Scanner WBTscan2 = new Scanner("C:\\Users\\Jake\\Documents\\GitHub\\coursework\\8C_Charts\\coursework\\Test files\\secondaryInfo.txt");
			
			/**
			 * Setup for counting the number of full stops in each occurance of the variable, and updating the array with all numbers that don't have any successing full stops. 
			 */
			while(WBTscan1.hasNextLine()){
				String numWithoutFull = WBTscan1.nextLine();
				String[] numWithoutFullArr = numWithoutFull.split(",");
				String keyVal = numWithoutFullArr[0];
				if(countNumFullStops(keyVal) = "0"){
					int keyValue = Integer.parseInt(keyVal);
					numWithoutPrec.add(keyValue);
				} else {
					WBTscan1.nextLine();
				}}
			size = numWithoutPrec.size();
			spaceFromEdge = 1000 - (size * 100) + (size - 1 * 25) / 2;
			for(int coords=0; coords<size; coords++){
				if(coords == 0){
				float xToAdd = (coords * 100) + spaceFromEdge;
				coordinates.add(xToAdd);
				} else {
					float xToAdd = (coords * 100) + spaceFromEdge + 25;
					coordinates.add(xToAdd);
				}
				// plot();
				// Plot the first 8 processes
			}	
		}});
			// space from the edge is determined
			// model the first process, based on the number of items without full stops
			// plot the first three processes
			// set up a loop over the whole file (again)
			// plot each 'number without full stops' (based on a case statement), updating the X co-ordinate as you go
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
			BufferedReader b1 = new BufferedReader(new FileReader("C:\\Users\\Jake\\Documents\\GitHub\\new_coursework\\Test files\\origDocTest.txt"));
			BufferedReader b2 = new BufferedReader(new FileReader("C:\\Users\\Jake\\Documents\\GitHub\\new_coursework\\Test files\\secondaryInfo.txt"));
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
			JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");
		}
	});
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
	frame.getContentPane().add(startDate);
	
	public static int countNumFullStops(String keyVal){
		numStops = StringUtils.countMatches(keyVal, ".");
		return numStops;
	}
}
	}}
