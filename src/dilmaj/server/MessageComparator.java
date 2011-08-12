package dilmaj.server;

import java.util.Comparator;

import dilmaj.shared.MessageComposite;

public class MessageComparator implements Comparator<MessageComposite> {

	@Override
	public int compare(MessageComposite arg0, MessageComposite arg1) {
		// TODO Auto-generated method stub
		if (arg0.getSequence()<arg1.getSequence())
			return -1;
		if (arg0.getSequence()>arg1.getSequence())
			return 1;
		return 0;
	}

}
