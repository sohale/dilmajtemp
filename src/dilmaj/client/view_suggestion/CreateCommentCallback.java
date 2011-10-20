package dilmaj.client.view_suggestion;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dilmaj.client.TermSuggestionService;
import dilmaj.client.TermSuggestionServiceAsync;
import dilmaj.client.sidePanel.AllTermsPanel;
import dilmaj.shared.CommentComposite;
import dilmaj.shared.Controller;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.Repository;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class CreateCommentCallback implements AsyncCallback<CommentComposite> {
	private TermSuggestionServiceAsync tsSvc = GWT.create(TermSuggestionService.class);
	ViewSuggestionPanel panel;
	
	public CreateCommentCallback(ViewSuggestionPanel panel) {
		this.panel=panel;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		panel.setMessage(new MessageComposite("You must login to like a suggestion"));
	}

	@Override
	public void onSuccess(CommentComposite result) {
		// TODO Auto-generated method stub
		//panel.setMessage(new MessageComposite(result.getId()+""));
		//panel.updateTermSuggestionRank(result);
		if (result==null)
			panel.setMessage(new MessageComposite("You either already liked it or not logged in!"));
		else {
			TermSuggestionComposite tsVO=panel.getTermSuggestionComposite();
			tsVO.addInteraction(result);
			tsSvc.update(tsVO, new UpdateTermSuggestionCallback(panel));
			panel.reset();
		}
	}
}
