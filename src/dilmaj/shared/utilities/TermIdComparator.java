package dilmaj.shared.utilities;

import java.util.Comparator;

import dilmaj.shared.TermComposite;

public class TermIdComparator implements Comparator<TermComposite> {

	@Override
	public int compare(TermComposite arg0, TermComposite arg1) {
		// TODO Auto-generated method stub
		return arg0.getId().compareTo(arg1.getId());
	}

}
