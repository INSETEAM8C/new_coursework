import java.util.ArrayList;

public class rectTester {
	public static void main (String[] args){
		ArrayList<String> s = new ArrayList<String>();
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
		rectTest r = new rectTest();
		rectTest.drawRect rec = r.new drawRect();
		for(int c=0; c<10; c++){
		int x = c*10;
		int y = c*10;
		int z = c*10;
		String temp = s.get(c);
		rec.addRectangle(x, y, z, temp);
	}}}