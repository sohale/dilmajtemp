package dilmaj.shared;

/* This class is used to calculate the distance of two strings based on the dynamic algorithm
 * found within the book Algorithm Design Manual
 */

public final class MyString {
	static int MATCH=0;
	static int INSERT=1;
	static int DELETE=2;

	public static MyString theInstance=null;
	
	class cell {
		int cost;
		int parent;
	}
	
	public MyString getInstance() {
		if (theInstance!=null)
			theInstance=new MyString();
		
		return theInstance;
	}
	
	cell[][] m;
	
	public int getDistance(String s, String t) {
		int[] opt=new int[3];
		
		int maxLength=s.length();
		if (t.length()>maxLength)
			maxLength=t.length();
		
		for (int i=0;i<maxLength;i++) {
			
		}
		
		return -1; // incomplete implementation
	}
}
