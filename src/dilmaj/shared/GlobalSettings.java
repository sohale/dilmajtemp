package dilmaj.shared;

import com.google.gwt.core.client.GWT;

import dilmaj.client.DilmajConstants;

public class GlobalSettings {
	public static DilmajConstants constants = GWT.create(DilmajConstants.class);
	
	public static native int getScreenWidth() /*-{ 
    	return $wnd.screen.width;
 	}-*/;
	
	public static native int getScreenHeight() /*-{ 
    	return $wnd.screen.height;
 	}-*/;
	
	public static int getBrowserWidth() {
		return com.google.gwt.user.client.Window.getClientWidth();
	}
	
	public static int getBrowserHeight() {
		return com.google.gwt.user.client.Window.getClientHeight();
	}
	
	public static double getSideMenuRatio() {
		return 1.0/12.0;
	}
	
	public static double getTermsPanelRatio() {
		return 1.0/4.0;
	}
	
	public static double getTermsPanelHeightRatio() {
		return 4.0/5.0;
	}
	
	public static double getTermsPanelRowHeightRatio() {
		return 4.0/125.0;
	}
	
	public static int getFixedRowHeight() {
		return com.google.gwt.user.client.Window.getClientHeight()*4/125;
	}
	
	public static int getTermsPerPage() {
		return 25;
	}
	
	public static int getTermsPerLoad() {
		return 100;
	}
}
