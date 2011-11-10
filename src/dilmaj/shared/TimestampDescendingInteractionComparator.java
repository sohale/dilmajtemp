package dilmaj.shared;

import java.util.Comparator;
import java.util.Date;

public class TimestampDescendingInteractionComparator implements
		Comparator<Date> {

	@Override
	public int compare(Date arg0, Date arg1) {
		// TODO Auto-generated method stub
		if (arg1.before(arg0))
			return -1;
		
		if (arg1.after(arg0))
			return 1;
		
		return 0;
	}

}
