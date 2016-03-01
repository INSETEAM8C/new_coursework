import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import java.io.File;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;

@SuppressWarnings("unused")
public class fileLoader {
//	static private final String newline = "\n";
	static File file;
	private String file2Str;
	private String file2Str1;
	static File file2;
	private JFrame frmLoadExistingFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileLoader window = new fileLoader();
					window.frmLoadExistingFile.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public fileLoader() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		final JFileChooser fc = new JFileChooser("C://Users");
		frmLoadExistingFile = new JFrame();
		frmLoadExistingFile.setTitle("Load");
		frmLoadExistingFile.setBounds(100, 100, 450, 186);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		frmLoadExistingFile.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == btnYes) {
					
					JOptionPane.showMessageDialog(frmLoadExistingFile, "Please choose your first file");
					
			        int returnVal = fc.showOpenDialog(btnYes);
				
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
		                file = fc.getSelectedFile();
		                
		                JOptionPane.showMessageDialog(frmLoadExistingFile, "Please choose your second file");
		                
		                int returnVal2 = fc.showOpenDialog(btnYes);
		                
		                if (returnVal2 == JFileChooser.APPROVE_OPTION) {
			                file2 = fc.getSelectedFile();
		                
			                if (mainMenu.getChartType() == "WBT") {
			                	WBT_constructor.main(null);
			                	frmLoadExistingFile.hide();
			                } else if (mainMenu.getChartType() == "PERT") {
			                	frmLoadExistingFile.hide();
			                } else if (mainMenu.getChartType() == "GANTT") {
			                	GANTT_constructor.main(null);
			                	frmLoadExistingFile.hide();
			                }
		                }
		                }
			        }
				}});
		
		btnYes.setBounds(73, 93, 97, 25);
		panel.add(btnYes);
		
		JLabel lblLoadFile = new JLabel("Do you want to load an existing file?");
		lblLoadFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoadFile.setBounds(32, 13, 354, 69);
		panel.add(lblLoadFile);
		frmLoadExistingFile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == btnNo) {
			        int returnVal = fc.showSaveDialog(btnYes);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			        	try {
			        	    File aFile = new File("C:\\Users\\8CChartsFiles\\createdFile.txt");
			        	    File bFile = new File("C:\\Users\\8CChartsFiles\\secondaryCreatedFile.txt");
			        		System.out.println(aFile.createNewFile());
			        		System.out.println(bFile.createNewFile());
			        	    } catch (IOException e1) {
			        	      e1.printStackTrace();
			        }
				}
				
			}
		}});
		btnNo.setBounds(247, 93, 97, 25);
		panel.add(btnNo);
			
		
	
	}
		public static File getFirstFilePath(){
			File filePath = file;
			return filePath;
		}
		
		public static File getSecondFilePath(){
			File filePath2 = file2;
			return filePath2;
		}

}		
