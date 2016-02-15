package newPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;

public class rectTest{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rectTest window = new rectTest();
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
	public rectTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		drawRect panel = new drawRect("Hello");
		panel.setBounds(0, 0, 450, 300);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	class drawRect extends JPanel
	{
		public String myMessage = "";
		public drawRect(String s)
		{
			setSize(500, 500);
			setVisible(true);
			myMessage = s;
		}
	
	public void paint(Graphics g, int x, int y, String info)
	{
		super.paint(g);
		for(int i=0; i<10; i++){
		g.drawRect(i * 100,i*10,300,100);
		g.setColor(Color.black);
		g.drawString(myMessage, i*100, i*10);
	}
	}
	}
	}