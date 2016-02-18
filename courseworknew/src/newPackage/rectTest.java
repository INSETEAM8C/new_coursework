import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Panel;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.JScrollPane;

public class rectTest{

	private JFrame frame;
	private rectTester r;
	private int xTemp, yTemp, zTemp;
	private String iTemp;
	ArrayList<String> ID = new ArrayList<String>();
	ArrayList<Integer> coord = new ArrayList<Integer>();
	ArrayList<String> desc = new ArrayList<String>();
	private int size = ID.size();
	
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
		drawRect panel = new drawRect();
		panel.setBounds(0, 0, 450, 300);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		addStuff();
	}
	
	public void addStuff(){
		ID.add("1");
		ID.add("1.1");
		ID.add("1.2");
		ID.add("1.3");
		coord.add(50);
		coord.add(175);
		coord.add(300);
		coord.add(425);
		desc.add("Put bread in");
		desc.add("Get butter out");
		desc.add("Find your dignity");
		desc.add("Do something else");
	}
	
	class drawRect extends JPanel
	{
		public drawRect()
		{
			setSize(500, 500);
			setVisible(true);
		}
		
	public void paint(Graphics g)
	{
		for(int i=0; i<size; i++){
		int x = coord.get(i);
		String info = ID.get(i) + " " + desc.get(i);
		System.out.println(info);
		g.drawRect(x, x, 100, 100);
		g.setColor(Color.black);
		g.drawString(info, (x+35), (x+35));
	}
	}
	}
	}