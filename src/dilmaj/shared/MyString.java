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
	
	public static MyString getInstance() {
		if (theInstance==null)
			theInstance=new MyString();
		
		return theInstance;
	}
	
	cell[][] m;
	
	public int getDistance(String s, String t) {
		int i, j=0, k;
		
		int[] opt=new int[3];
		
		int maxLength=s.length();
		if (t.length()>maxLength)
			maxLength=t.length();
		maxLength++;
		
		m=new cell[maxLength][maxLength];
		for (i=0;i<maxLength;i++)
			for (j=0;j<maxLength;j++)
				m[i][j]=new cell();
		
		for (i=0;i<maxLength;i++) {
			row_init(i);
			column_init(i);
		}
		
		for (i=1; i<=s.length();i++) {
			for (j=1;j<=t.length();j++) {
				opt[MATCH]=m[i-1][j-1].cost+match(s.charAt(i-1), t.charAt(j-1));
				opt[INSERT]=m[i][j-1].cost+indel(t.charAt(j-1));
				opt[DELETE]=m[i-1][j].cost+indel(s.charAt(i-1));
				
				m[i][j].cost=opt[MATCH];
				m[i][j].parent=MATCH;
				for (k=INSERT;k<=DELETE;k++)
					if (opt[k]<m[i][j].cost) {
						m[i][j].cost=opt[k];
						m[i][j].parent=k;
					}
			}
		}
		
		// obtaining goal cell
		i=s.length();
		j=t.length();
		
		return m[i][j].cost; // incomplete implementation
	}
	
	private void row_init(int i) {
		m[0][i].cost=0;
		if (i>0)
			m[0][i].parent=INSERT;
		else
			m[0][i].parent=-1;
	}
	
	private void column_init(int i) {
		m[i][0].cost=0;
		if (i>0)
			m[i][0].parent=DELETE;
		else
			m[i][0].parent=-1;
	}
	
	private int match(char c, char d) {
		if (c==d)
			return 0;
		return 1;
	}
	
	private int indel(char c) {
		return 1;
	}
	
	private int goal_cell(String s) {
		return s.length()-1;
	}
}
