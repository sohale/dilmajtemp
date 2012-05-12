package dilmaj.client.search;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.shared.SearchResult;
import dilmaj.shared.TermComposite;

public class GetSomeTermsCallback implements AsyncCallback<List<SearchResult>> {
	String captionFilter;
	
	public GetSomeTermsCallback(String filter) {
		captionFilter=filter;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		//panel.setMessage(new MessageComposite("Error!"));
	}

	@Override
	public void onSuccess(List<SearchResult> result) {
		// TODO Auto-generated method stub
		//panel.populateTable(result);
		ResultsPanel panel=null;
		
		/*if (result.size()>0) 
			panel=ResultsPanel.getInstance(result, "");
		else*/
		panel=ResultsPanel.getInstance(result, captionFilter);
		
		//panel.setSize("100px", "100px");
		
		//panel.setPopupPosition(left, top);
		panel.show();
	}

}
