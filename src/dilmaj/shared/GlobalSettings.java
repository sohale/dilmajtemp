package dilmaj.shared;

import com.google.gwt.core.client.GWT;

import dilmaj.client.DilmajConstants;

public class GlobalSettings {
	public static DilmajConstants constants = GWT.create(DilmajConstants.class);
}
