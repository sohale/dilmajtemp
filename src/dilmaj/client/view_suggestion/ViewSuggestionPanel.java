package dilmaj.client.view_suggestion;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.DilmajConstants;
import dilmaj.client.MyPanel;
import dilmaj.shared.CommentComposite;
import dilmaj.shared.InteractionComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;
import dilmaj.shared.UseCaseComposite;

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

	FlexTable commentsTable=new FlexTable();
	TextArea commentArea=new TextArea();
	Button commentButton=new Button("Create Comment");
	int commentsCounter=0;

	FlexTable samplesTable=new FlexTable();
	TextArea sampleArea=new TextArea();
	Button sampleButton=new Button("Create Sample");
	int samplesCounter=0;
	
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
		commentButton.addClickHandler(controller);
		sampleButton.addClickHandler(controller);
		
		suggestorLabel=new Label(constants.suggestor()+": "+suggestion.getUser());
		
		Iterator<InteractionComposite> icIterator=termSuggestion.getInteractions().iterator();
		while (icIterator.hasNext()) {
			InteractionComposite ic=icIterator.next();
			try {
				LikeComposite likeComposite=(LikeComposite)ic;
				votersString=votersString.concat(ic.getUser()+", ");
			}
			catch (ClassCastException cce0) {
				try {
					CommentComposite commentComposite=(CommentComposite)ic;
					commentsTable.setText(commentsCounter, 1, commentComposite.getUser()+constants.said());
					commentsTable.setText(commentsCounter++, 0, commentComposite.getFeedback());
				}
				catch (ClassCastException cce1) {
					try {
						UseCaseComposite sampleComposite=(UseCaseComposite)ic;
						samplesTable.setText(samplesCounter, 1, sampleComposite.getUser()+constants.said());
						samplesTable.setText(samplesCounter++, 0, sampleComposite.getFeedback());
					}
					catch (ClassCastException cce2) {
						
					}
				}
			}
		}
		votersLabel=new Label(votersString);

		add(commentsTable);
		add(commentArea);
		add(commentButton);

		add(samplesTable);
		add(sampleArea);
		add(sampleButton);
		
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

		votersString="";
		commentsCounter=0;
		samplesCounter=0;
		commentsTable.clear();
		samplesTable.clear();
		Iterator<InteractionComposite> icIterator=termSuggestion.getInteractions().iterator();
		while (icIterator.hasNext()) {
			InteractionComposite ic=icIterator.next();
			try {
				LikeComposite likeComposite=(LikeComposite)ic;
				votersString=votersString.concat(ic.getUser()+", ");
			}
			catch (ClassCastException cce0) {
				try {
					CommentComposite commentComposite=(CommentComposite)ic;
					commentsTable.setText(commentsCounter, 1, commentComposite.getUser()+constants.said());
					commentsTable.setText(commentsCounter++, 0, commentComposite.getFeedback());
				}
				catch (ClassCastException cce1) {
					try {
						UseCaseComposite sampleComposite=(UseCaseComposite)ic;
						samplesTable.setText(samplesCounter, 1, sampleComposite.getUser()+constants.said());
						samplesTable.setText(samplesCounter++, 0, sampleComposite.getFeedback());
					}
					catch (ClassCastException cce2) {
						
					}
				}
			}
		}
		votersLabel.setText(votersString);
	}

	public void addComment(CommentComposite commentComposite) {
		commentsTable.setText(commentsCounter, 1, commentComposite.getUser()+constants.said());
		commentsTable.setText(commentsCounter++, 0, commentComposite.getFeedback());
	}
	
	public void addUseCase(UseCaseComposite sampleComposite) {
		samplesTable.setText(samplesCounter, 1, sampleComposite.getUser()+constants.said());
		samplesTable.setText(samplesCounter++, 0, sampleComposite.getFeedback());
	}
	
	public String getComment() {
		return commentArea.getText();
	}
	
	public String getUseCase() {
		return sampleArea.getText();
	}

	public void reset() {
		commentArea.setText("");
		sampleArea.setText("");
	}
}
