package new_coursework;

public class formatMethod {

	/**
	 * First of two methods for formatting text
	 * @param String from WBT_constructor (taken from file)
	 * @return Returns a formatted string, to be entered into a text area.
	 */
	
	public String format1(String k){
		String numToSeperate = "";
		String[] splitKey = k.split(",", 2);
		// int math = getPhysicalSize(splitKey[1]);
		// int numSpaces = 79 - math;
		// for (int space = 0; space < numSpaces; space++){
		// numToSeperate = numToSeperate + " ";}
		// splitKey[1] = "        " + splitKey[1] + numToSeperate;
		int numSpaces = 79 - splitKey[1].length();
		for(int space = 0; space < numSpaces; space++){
		numToSeperate = numToSeperate + " ";}
		splitKey[1] = "        " + splitKey[1] + numToSeperate;
		k = splitKey[0] + splitKey[1];
		k = k.replaceAll(",", "");
		k = k.replaceAll("\"", "");
		return k;
	}

	/**
	 * Gets the physical size of a block of text so that formatting can be correctly plotted
	 * @param The second portion of the split.
	 * @return returns the physical size of the string
	 */
	// public int getPhysicalSize(String s){
		// Count the number of bytes taken up by a string
		// return this.
		// Graphics g = null;
		// int space = g.getFontMetrics().stringWidth(s);
		// System.out.println(space);
		// return space;

//	}
	
	
	/**
	 * Second of two methods for formatting text
	 * @param String from WBT_constructor (taken from file)
	 * @return Returns a formatted string, to be entered into a text area.
	 */
	public String format2(String i){
		String[] splitInfo = i.split(",", 3);
		splitInfo[0] = "   " + splitInfo[0] + "                               ";
		splitInfo[1] = " " + splitInfo[1] + "                                ";
		i = splitInfo[0] + splitInfo[1] + splitInfo[2];
		i = i.replaceAll(",", "");
		i = i.replaceAll("\"", "");
		return i;
	}
}
