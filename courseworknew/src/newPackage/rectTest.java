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
	private int xTemp, yTemp, zTemp;
	private String iTemp;
	ArrayList<String> ID = new ArrayList<String>();
	ArrayList<String> s = new ArrayList<String>();
	ArrayList<Integer> coord = new ArrayList<Integer>();
	ArrayList<String> desc = new ArrayList<String>();
	
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
		this.add();
		drawRect panel = new drawRect();
		panel.setBounds(0, 0, 1200, 1200);
		frame.setBounds(10, 10, 1200, 1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	public void add(){
	s.add("Peter");
	s.add("Andre");
	s.add("Benedict");
	s.add("Cumberbatch");
	s.add("Sally");
	s.add("Gomez");
	s.add("Bruce");
	s.add("Willis");
	s.add("Arnold");
	s.add("Scwartz");
	ID.add("1");
	ID.add("1.1");
	ID.add("1.2");
	ID.add("1.3");
	ID.add("2");
	coord.add(50);
	coord.add(225);
	coord.add(400);
	coord.add(575);
	coord.add(750);
	desc.add("Put bread in");
	desc.add("Get butter out");
	desc.add("Find your dignity");
	desc.add("Do something else");
	desc.add("Do a backflip");
	}
	
	class drawRect extends JPanel
	{
		public drawRect()
		{
			setSize(1000, 1000);
			setVisible(true);
		}
	
	public void paint(Graphics g)
	{
		for(int i=0; i<5; i++){
		int x = coord.get(i);
		String info = ID.get(i) + " " + desc.get(i);
		System.out.println(info);
		g.drawRect(x, 100, 150, 75);
		g.setColor(Color.black);
		g.drawString(info, (x+25), (140));
	}}}}