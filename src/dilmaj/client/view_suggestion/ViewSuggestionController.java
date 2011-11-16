package dilmaj.client.view_suggestion;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;

import dilmaj.client.InteractionService;
import dilmaj.client.InteractionServiceAsync;
import dilmaj.client.TermService;
import dilmaj.client.TermServiceAsync;
import dilmaj.client.TermSuggestionService;
import dilmaj.client.TermSuggestionServiceAsync;
import dilmaj.client.termPanel.TermButton;
import dilmaj.client.view_term.ViewTermCallback;
import dilmaj.shared.CommentComposite;
import dilmaj.shared.Controller;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.InteractionComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.TermSuggestionComposite;
import dilmaj.shared.UseCaseComposite;

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
		
		if (source.getClass().equals(Image.class)) {
			Image pushButton=(Image)source;
			
			LikeComposite likeVO=new LikeComposite();
			likeVO.setTermSuggestion(tsVO);
			interactionSvc.create(likeVO, new CreateLikeCallback(suggestionPanel));
		} else if (source.getClass().equals(Label.class)) {
			Label sourceButton=(Label)source;
			String buttonCaption=sourceButton.getText();
			if (buttonCaption.equals("+")) {
				tsVO.increaseRank();
			} else if (buttonCaption.equals("-")) {
				tsVO.decreaseRank();
			} else if (buttonCaption.equals("Clear Comment")) {
				suggestionPanel.clearCommentArea();
			} else if (buttonCaption.equals("Create Comment")) {
				CommentComposite commentVO=new CommentComposite();
				commentVO.setFeedback(suggestionPanel.getComment());
				commentVO.setTermSuggestion(tsVO);
				interactionSvc.create(commentVO, new CreateCommentCallback(suggestionPanel));
			} else if (buttonCaption.equals("Create Sample")) {
				UseCaseComposite sampleVO=new UseCaseComposite();
				sampleVO.setFeedback(suggestionPanel.getUseCase());
				sampleVO.setTermSuggestion(tsVO);
				interactionSvc.create(sampleVO, new CreateUseCaseCallback(suggestionPanel));
			} else if (buttonCaption.equals(GlobalSettings.constants.more()+">>")) {
				suggestionPanel.more();
			} else if (buttonCaption.equals(GlobalSettings.constants.less()+"<<")) {
				suggestionPanel.less();
			}
			
			if (!buttonCaption.equals("x"))
				tsSvc.update(tsVO, new UpdateTermSuggestionCallback(suggestionPanel));
		} else if (source.getClass().equals(TextArea.class)) {
			TextArea textArea=(TextArea)source;		
			textArea.setText("");
		}
	}
}
