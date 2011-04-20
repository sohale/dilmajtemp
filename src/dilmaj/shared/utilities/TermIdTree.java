package dilmaj.shared.utilities;

import java.util.List;

import dilmaj.shared.TermComposite;

public class TermIdTree {
	TermIdNode root=null;
	
	public TermIdTree(TermComposite rootValue) {
		root=new TermIdNode(rootValue);
	}
	
	public TermIdTree(List<TermComposite> sortedList) {
		int lBound=0;
		int uBound=sortedList.size()-1;
		int index=(lBound+uBound)/2;
		root=new TermIdNode(sortedList.get(index));
		setLeft(sortedList, lBound, index-1, root);
		setRite(sortedList, index+1, uBound, root);
	}
	
	private void setLeft(List<TermComposite> aList, int lBound, int uBound, TermIdNode aNode) {
		if (uBound>=lBound) {
			int index=(lBound+uBound)/2;
			
			aNode.left=new TermIdNode(aList.get(index));
			
			setLeft(aList, lBound, index-1, aNode);
			setRite(aList, index+1, uBound, aNode);
		}
	}
	
	private void setRite(List<TermComposite> aList, int lBound, int uBound, TermIdNode aNode) {
		if (uBound>=lBound) {
			int index=(lBound+uBound)/2;
			
			aNode.rite=new TermIdNode(aList.get(index));
			
			setLeft(aList, lBound, index-1, aNode);
			setRite(aList, index+1, uBound, aNode);
		}
	}
}
