package dilmaj.client.view_suggestion;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;

import dilmaj.client.InteractionService;
import dilmaj.client.InteractionServiceAsync;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.TermSuggestionService;
import dilmaj.client.TermSuggestionServiceAsync;
import dilmaj.client.view_term.ViewTermCallback;
import dilmaj.shared.Controller;
import dilmaj.shared.InteractionComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.TermSuggestionComposite;

public class ViewSuggestionController extends Controller {
	private TermSuggestionServiceAsync tsSvc = GWT.create(TermSuggestionService.class);
	private InteractionServiceAsync interactionSvc = GWT.create(InteractionService.class);
	ViewSuggestionPanel suggestionPanel;
	
	public ViewSuggestionController(ViewSuggestionPanel suggestionPanel) {
		this.suggestionPanel=suggestionPanel;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		TermSuggestionComposite tsVO=suggestionPanel.getTermSuggestionComposite();
		Object source=event.getSource();
		
		try {
			PushButton pushButton=(PushButton)source;
			
			LikeComposite likeVO=new LikeComposite();
			likeVO.setTermSuggestion(tsVO);
			interactionSvc.create(likeVO, new CreateLikeCallback(suggestionPanel));
		} catch (ClassCastException cce0) {
			Button sourceButton=(Button)source;
			String buttonCaption=sourceButton.getText();
			if (buttonCaption.equals("+"))
				tsVO.increaseRank();
			else if (buttonCaption.equals("-"))
				tsVO.decreaseRank();
			else
				((PopupPanel)suggestionPanel.getParent()).hide();
			
			if (!buttonCaption.equals("x"))
				tsSvc.update(tsVO, new UpdateTermSuggestionCallback(suggestionPanel));
		}
	}
}
