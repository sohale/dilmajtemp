package dilmaj.shared.utilities;

import java.util.List;

import dilmaj.shared.TermComposite;

public class TermIdNode {
	TermComposite value;
	//TermIdComparator comparator=new TermIdComparator();
	
	TermIdNode left=null;
	TermIdNode rite=null;
	
	public TermIdNode(TermComposite value) {
		this.value=value;
	}
	
	public TermIdNode addChild(TermComposite newValue) {
		if (TermIdComparator.TERM_ID_COMPARATOR.compare(value, newValue)<0) {
			if (left==null) {
				left=new TermIdNode(newValue);
				return left;
			}
			else {
				return left.addChild(newValue);
			}
		}
		
		if (TermIdComparator.TERM_ID_COMPARATOR.compare(value, newValue)>=0) {
			if (rite==null) {
				rite=new TermIdNode(newValue);
				return rite;
			}
			else {
				return rite.addChild(newValue);
			}
		}
		
		return this;
	}
}
