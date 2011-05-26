package dilmaj.client.view_suggestion;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.DilmajConstants;
import dilmaj.client.MyPanel;
import dilmaj.shared.InteractionComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class ViewSuggestionPanel extends VerticalPanel implements MyPanel {
	private DilmajConstants constants = GWT.create(DilmajConstants.class);

	private static HashMap<TermSuggestionComposite, ViewSuggestionPanel> allInstances=new HashMap<TermSuggestionComposite, ViewSuggestionPanel>();
	
	private MessageComposite message;
	TermComposite suggestion;
	TermComposite term;
	TermSuggestionComposite termSuggestion;
	
	Label tsLabel;
	Label termLabel;
	Label suggestorLabel;
	Label votersLabel;
	
	Button closeButton=new Button("x");
	
	HorizontalPanel interactionPanel=new HorizontalPanel();
	PushButton likeButton=new PushButton(new Image("images/like.jpg"));
	Label likesLabel=new Label();
	String votersString=constants.supporters()+": ";
	ViewSuggestionController controller=new ViewSuggestionController(this);

	public static ViewSuggestionPanel getInstance(TermSuggestionComposite tsVO) {
		ViewSuggestionPanel anInstance=allInstances.get(tsVO);
		if (anInstance==null) {
			anInstance=new ViewSuggestionPanel(tsVO);
			allInstances.put(tsVO, anInstance);
		}
		
		return anInstance;
	}
	
	private ViewSuggestionPanel(TermSuggestionComposite tsVO) {
		super();
		
		termSuggestion=tsVO;
		this.suggestion=tsVO.getSuggestion();
		this.term=tsVO.getTerm();
		
		tsLabel=new Label(suggestion.getCaption()+"="+term.getCaption());
		add(tsLabel);
		
		add(interactionPanel);
		interactionPanel.add(likeButton);
		interactionPanel.add(likesLabel);
		likesLabel.setText(termSuggestion.getLikes()+"");

		closeButton.addClickHandler(controller);
		likeButton.addClickHandler(controller);
		
		suggestorLabel=new Label(constants.suggestor()+": "+suggestion.getUser());
		
		Iterator<InteractionComposite> icIterator=termSuggestion.getInteractions().iterator();
		while (icIterator.hasNext()) {
			InteractionComposite ic=icIterator.next();
			votersString=votersString.concat(ic.getUser()+", ");
		}
		votersLabel=new Label(votersString);
		
		add(suggestorLabel);
		add(votersLabel);
		add(closeButton);
	}
	
	public TermSuggestionComposite getTermSuggestionComposite() {
		return termSuggestion;
	}

	@Override
	public void setMessage(MessageComposite messageVO) {
		// TODO Auto-generated method stub
		message=messageVO;
		Window.alert(message.toString());
	}
	
	public void updateTermSuggestionRank(TermSuggestionComposite tsVO) {
		termSuggestion=tsVO;
		//votesLabel.setText(termSuggestion.getRank()+"");
		likesLabel.setText(termSuggestion.getLikes()+"");
		votersString=votersString.concat(tsVO.getUser()+", ");
		votersLabel.setText(votersString);
	}
}
