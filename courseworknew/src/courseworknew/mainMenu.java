import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class mainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu window = new mainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 775, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblWelcomeToc = new JLabel("Welcome to 8C Charts");
		lblWelcomeToc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeToc.setBounds(239, 42, 242, 35);
		panel.add(lblWelcomeToc);
		
		JButton btnWorkBreakdownTree = new JButton("Work Breakdown Tree");
		btnWorkBreakdownTree.setBounds(26, 119, 187, 45);
		panel.add(btnWorkBreakdownTree);
		
		JButton btnNewButton = new JButton("PERT Chart");
		btnNewButton.setBounds(26, 188, 187, 45);
		panel.add(btnNewButton);
		
		JButton btnGanttChart = new JButton("GANTT Chart");
		btnGanttChart.setBounds(26, 259, 187, 45);
		panel.add(btnGanttChart);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(239, 88, 510, 309);
		panel.add(textArea);
		textArea.setText("Welcome to 8C Charts\r\n\r\n8C Charts can create the perfect charts for all you project planning or schedueling \r\nneeds.\r\n\r\nThis application will allow you to create the following charts:\r\n\r\nWork Breakdown Tree (WBT):\r\n\t\r\nThis diagram is to shows the main tasks that needs to be done and the sub task of \r\neach of the main tasks. It will order the tasks in priority of what needs to be done \r\nbefore another.\r\n\r\nPERT Chart:\r\n\r\nThis chart will have a diagram with all the different task that needs to be done. \r\nNext to the task it will also have the start and end dates for which the task \r\nneeds to be completed. It will also show you what tasks can be done at the same\r\ntime as other tasks and what has to be done before certain task can be started.\r\n\r\nGANTT Chart:\r\n\r\nThis chart shows a time line length for different tasks and how long each task will\r\ntake. It will also have all the information that the PERT Chart has but includes lines\r\nshowing the lastest start and finish time of each task.\r\n\r\n");
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(10, 414, 89, 23);
		panel.add(btnExit);
		
		JButton btnDonate = new JButton("Donate");
		btnDonate.setBounds(660, 414, 89, 23);
		panel.add(btnDonate);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		menuBar.setBounds(0, 0, 759, 21);
		panel.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnExit = new JMenu("Exit");
		mnFile.add(mnExit);
		
		JMenu mnWbt = new JMenu("WBT");
		menuBar.add(mnWbt);
		
		JMenu mnCreateNewFile = new JMenu("Create New File");
		mnWbt.add(mnCreateNewFile);
		
		JMenu mnLoadExistingFile = new JMenu("Load Existing File");
		mnWbt.add(mnLoadExistingFile);
		
		JMenu mnPert = new JMenu("PERT");
		menuBar.add(mnPert);
		
		JMenu mnCreateNewFile_1 = new JMenu("Create New File");
		mnPert.add(mnCreateNewFile_1);
		
		JMenu mnLoadExistingFile_1 = new JMenu("Load Existing File");
		mnPert.add(mnLoadExistingFile_1);
		
		JMenu mnGantt = new JMenu("GANTT");
		menuBar.add(mnGantt);
		
		JMenu mnCreateNewFile_2 = new JMenu("Create New File");
		mnGantt.add(mnCreateNewFile_2);
		
		JMenu mnLoadExistingFile_2 = new JMenu("Load Existing File");
		mnGantt.add(mnLoadExistingFile_2);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenu mnDonate = new JMenu("Donate");
		mnHelp.add(mnDonate);
		
		JMenu mnUserGuide = new JMenu("User Guide");
		mnHelp.add(mnUserGuide);
	}
}
