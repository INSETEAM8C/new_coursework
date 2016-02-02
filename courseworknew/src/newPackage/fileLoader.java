package newPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.JLabel;
import java.awt.Font;

public class fileLoader {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileLoader window = new fileLoader();
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
	public fileLoader() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		final JFileChooser fc = new JFileChooser("C:\\Users\\Callum\\Documents\\8C_ChartsProgramFolder");
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == btnYes) {
			        int returnVal = fc.showOpenDialog(btnYes);
				
				}
			}
		});
		btnYes.setBounds(71, 95, 97, 25);
		panel.add(btnYes);
		
		JLabel lblLoadFile = new JLabel("Do you want to load an existing file?");
		lblLoadFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoadFile.setBounds(32, 13, 354, 69);
		panel.add(lblLoadFile);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == btnNo) {
			        int returnVal = fc.showSaveDialog(btnYes);
				
				}
				
			}
		});
		btnNo.setBounds(245, 95, 97, 25);
		panel.add(btnNo);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
//		        new mainMenu.setVisible(true);
				
			}
		});
		btnHome.setBounds(12, 215, 97, 25);
		panel.add(btnHome);
		
		
	}
}
