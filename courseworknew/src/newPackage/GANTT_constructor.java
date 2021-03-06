/** This class is responsible for creating the GANTT 
 * @ Author: 
 * @ Version: 1.0 - button functionality.
 * 
 * Changes to be made for version 1.1:
 * - Calendar to be added
 * - GANTT/PERT/GANTT to modelled based on common data
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
import org.jfree.ui.RefineryUtilities;

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

// Create PERT functionality
// Update refresh cosmetics
// Work GANTT bug (with predecessors & dates) out
// Sort out WBT task alignment
// Put in feature to be able to add single-digit tasks

public class GANTT_constructor{

	JFrame frame;
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
	private File origFile = fileLoader.getFirstFilePath();
	private File infoFile = fileLoader.getSecondFilePath();
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
	private JTextField PreField;
	private JTextField txtPredecessor;
	
	/**
	 * Set up the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GANTT_constructor window = new GANTT_constructor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}});}

	/**
	 * Launch the application.
	 */
	public GANTT_constructor() {
		initialize();
	}

	/**
	 * Initialises the contents of the frame, to the dimensions of the user's window and the variables responsible for correct data manipulation
	 * @return opens "GANTT creator"
	 */
	
	private void initialize() {	
		/**
		 * Initialises variables and formatted text variables
		 */		
		frame = new JFrame("GANTT creator");
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		frame.getContentPane().setBackground(new Color(153, 180, 209));
		frame.setBounds(0, 0, 1366, 393);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Creates the text pane responsible for providing information on all processes hosted in host files. 
		 */		
		TextArea GANTTTaskTextArea = new TextArea("Press refresh to get all info from your files.");
		GANTTTaskTextArea.setBounds(10, 47, 1330, 162);
		GANTTTaskTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane();
		scroll.setLocation(0, -312);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
		frame.getContentPane().add(GANTTTaskTextArea);
		
		/**
		 * Initiates values for combo boxes and other vital data
		 */
		String[] availableMetric = {"Hours", "Days", "Weeks", "Months" };
		JComboBox<Object> dur_metric = new JComboBox<Object>(availableMetric);
		dur_metric.setBounds(1001, 270, 70, 20);
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
			} if(PreField.getText().equals(ID)){
				JOptionPane.showMessageDialog(frame, "Your process cannot be dependent on itself");
				return;
			}
			if(a.checkForCorrectness(start, end, duration) == false){
				JOptionPane.showMessageDialog(frame, "there's something wrong with your dates, please try again");
			} else {
				try {
					String infoString = startDate.getText() + ", " + endDate.getText() + ", " + dur.getText() + " " + dur_metric.getSelectedItem() + ", " + PreField.getText();
					if(a.placeCheck(ID, DescTemp, infoString, file1, file2) == false){
						JOptionPane.showMessageDialog(frame, "That already exists. Please use the 'edit' button if you wish to edit. ");
					} else {
						JOptionPane.showMessageDialog(frame, "Great. That's been added. ");
					}
				} catch (FileNotFoundException e1) {}
	 catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}}});
		
		btnAdd.setBounds(14, 311, 114, 23);
		frame.getContentPane().add(btnAdd);{}
	
	/**
	 * Shortcut to take the user home
	 * @returns returns home screen
	 */
	btnHome = new JButton("Home");
	btnHome.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
		}
	});
	btnHome.setBounds(10, 13, 70, 23);
	frame.getContentPane().add(btnHome);
	
	/**
	 * Creates the chart for the page the user is currently on
	 * @version version 1 - with bugs
	 */
	btnCreate = new JButton("Create chart");
	btnCreate.addMouseListener(new MouseAdapter() {
		@SuppressWarnings("resource")
		@Override
		public void mouseClicked(MouseEvent e) {
			
			/**
			 * Setup for counting the number of full stops in each occurrence of the variable, and updating the array with all numbers that don't have any successing full stops. 
			 * Test for validation
			 */
				try {
				    final GanttDemo1 demo = new GanttDemo1("Gantt Chart Demo 1");
				    demo.pack();
				    RefineryUtilities.centerFrameOnScreen(demo);
				    demo.setVisible(true);
					} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
			}
	});
			
	btnCreate.setBounds(1226, 13, 114, 23);
	frame.getContentPane().add(btnCreate);
	
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
		GANTTTaskTextArea.setText(null);
		GANTTTaskTextArea.setText("ID:      Task Desc:                                                                 Start date:                                    End date:                                    Duration:            Predecessors:");
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
				GANTTTaskTextArea.append("\n" + k);
				GANTTTaskTextArea.append(i);
		}}catch (Exception RefreshProblem){}
	}});
	btnRefresh_1.setBounds(1226, 220, 114, 23);
	frame.getContentPane().add(btnRefresh_1);
		
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
	btnRemove.setBounds(138, 311, 114, 23);
	frame.getContentPane().add(btnRemove);
	
	/** 
	 * Initiates text fields and descriptions for the purposes of aesthetics.
	 */
	txtID = new JTextField();
	txtID.setBounds(10, 270, 86, 20);
	frame.getContentPane().add(txtID);
	txtID.setColumns(10);
		
	txtDesc = new JTextField();
	txtDesc.setColumns(10);
	txtDesc.setBounds(116, 270, 425, 20);
	frame.getContentPane().add(txtDesc);
		
	dur = new JTextField();
	dur.setColumns(10);
	dur.setBounds(969, 269, 32, 21);
	frame.getContentPane().add(dur);
		
	txtTaskId = new JTextField();
	txtTaskId.setText("Task ID");
	txtTaskId.setEditable(false);
	txtTaskId.setBorder(null);
	txtTaskId.setBackground(new Color(0,0,0,0));
	txtTaskId.setBounds(10, 254, 86, 20);
	frame.getContentPane().add(txtTaskId);
	txtTaskId.setColumns(10);
		
	txtDescription = new JTextField();
	txtDescription.setText("Task description");
	txtDescription.setEditable(false);
	txtDescription.setBorder(null);
	txtDescription.setBackground(new Color(0,0,0,0));
	txtDescription.setColumns(10);
	txtDescription.setBounds(116, 254, 94, 20);
	frame.getContentPane().add(txtDescription);
		
	txtStartDate = new JTextField();
	txtStartDate.setText("Start date");
	txtStartDate.setEditable(false);
	txtStartDate.setBorder(null);
	txtStartDate.setBackground(new Color(0,0,0,0));
	txtStartDate.setColumns(10);
	txtStartDate.setBounds(551, 246, 86, 36);
	frame.getContentPane().add(txtStartDate);
		
	txtEndDate = new JTextField();
	txtEndDate.setText("End date");
	txtEndDate.setEditable(false);
	txtEndDate.setBorder(null);
	txtEndDate.setBackground(new Color(0,0,0,0));
	txtEndDate.setColumns(10);
	txtEndDate.setBounds(759, 246, 86, 36);
	frame.getContentPane().add(txtEndDate);
		
	txtDuration = new JTextField();
	txtDuration.setText("Duration");
	txtDuration.setEditable(false);
	txtDuration.setBorder(null);
	txtDuration.setBackground(new Color(0,0,0,0));
	txtDuration.setColumns(10);
	txtDuration.setBounds(969, 246, 86, 36);
	frame.getContentPane().add(txtDuration);
		
	endDate = new JTextField();
	endDate.setColumns(10);
	endDate.setBounds(759, 269, 200, 21);
	frame.getContentPane().add(endDate);			
		
	startDate = new JTextField();
	startDate.setColumns(10);
	startDate.setBounds(551, 269, 200, 21);
	frame.getContentPane().add(startDate);
	
	PreField = new JTextField();
	PreField.setColumns(10);
	PreField.setBounds(1097, 270, 70, 21);
	frame.getContentPane().add(PreField);
	
	txtPredecessor = new JTextField();
	txtPredecessor.setText("Predecessor");
	txtPredecessor.setEditable(false);
	txtPredecessor.setColumns(10);
	txtPredecessor.setBorder(null);
	txtPredecessor.setBackground(new Color(0, 0, 0, 0));
	txtPredecessor.setBounds(1098, 246, 86, 36);
	frame.getContentPane().add(txtPredecessor);
	
	}
	
	public File returnOrig(){
		return origFile;
	}
	
	public File returnInfo(){
		return infoFile;
	}
{}}