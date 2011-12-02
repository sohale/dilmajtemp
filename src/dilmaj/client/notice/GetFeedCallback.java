package dilmaj.client.notice;

import javax.xml.transform.stream.StreamResult;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

import dilmaj.client.TestGWT;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

public class GetFeedCallback implements AsyncCallback<String> {
	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onSuccess(String result) {
		// TODO Auto-generated method stub
		if (result!=null) {
			Document xmlDocument=XMLParser.parse(result);
			NoticePanel.getInstance().setNewFeed(xmlDocument);
		}
	}
}
