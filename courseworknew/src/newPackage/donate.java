import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.TextField;
import java.awt.Button;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class donate {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					donate window = new donate();
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
	public donate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel thankYouLabel = new JLabel("<html>Thank you for using our software 8C Charts.<br> If you would like to help the ongoing development of the software please feel free to donate below.</html>");
		thankYouLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		thankYouLabel.setBounds(10, 11, 439, 78);
		panel.add(thankYouLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.control, SystemColor.controlHighlight, SystemColor.controlHighlight, SystemColor.controlHighlight));
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(20, 100, 411, 231);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNameOnCard = new JLabel("Name on Card:");
		lblNameOnCard.setBounds(10, 11, 97, 14);
		panel_1.add(lblNameOnCard);
		
		JLabel lblCardNumber = new JLabel("Card Number:");
		lblCardNumber.setBounds(10, 36, 97, 14);
		panel_1.add(lblCardNumber);
		
		JLabel lblSecurityDigits = new JLabel("Security Digits:");
		lblSecurityDigits.setBounds(10, 61, 97, 14);
		panel_1.add(lblSecurityDigits);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date:");
		lblExpiryDate.setBounds(10, 89, 87, 14);
		panel_1.add(lblExpiryDate);
		
		JLabel lblDonationAmount = new JLabel("Donation Amount:");
		lblDonationAmount.setBounds(10, 115, 97, 14);
		panel_1.add(lblDonationAmount);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(106, 5, 198, 22);
		panel_1.add(textField_3);
		
		Button button = new Button("Donate");
		button.setBounds(20, 139, 367, 66);
		panel_1.add(button);
		
		TextField textField = new TextField();
		textField.setBounds(110, 110, 198, 22);
		panel_1.add(textField);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(106, 30, 198, 22);
		panel_1.add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(106, 60, 43, 22);
		panel_1.add(textField_2);
		
		String[] months = {"<html> 1<br> 2<br> 3<br> 4<br> 5<br> 6<br> 7<br> 8<br> 9<br> 10<br> 11<br> 12 </html>"};
		
		JLabel lblMonth = new JLabel("Month:");
		lblMonth.setBounds(110, 89, 50, 14);
		panel_1.add(lblMonth);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(222, 89, 35, 14);
		panel_1.add(lblYear);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBox_1.setBounds(257, 86, 65, 20);
		panel_1.add(comboBox_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox.setBounds(170, 86, 42, 20);
		panel_1.add(comboBox);
		frame.setBounds(100, 100, 475, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
