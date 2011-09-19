package dilmaj.client.view_suggestion;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.DilmajConstants;
import dilmaj.client.MyPanel;
import dilmaj.shared.CommentComposite;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.InteractionComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;
import dilmaj.shared.UseCaseComposite;

public class ViewSuggestionPanel extends HorizontalPanel implements MyPanel {
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
	
	VerticalPanel suggestionPanel=new VerticalPanel();
	VerticalPanel descriptionPanel=new VerticalPanel();
	HorizontalPanel interactionPanel=new HorizontalPanel();
	
	
	PushButton likeButton=new PushButton(new Image("images/like.jpg"));
	Label likesLabel=new Label();
	String votersString=constants.supporters()+" ";
	String lessVoters=constants.supporters()+" ";
	ViewSuggestionController controller=new ViewSuggestionController(this);

	FlexTable commentsTable=new FlexTable();
	TextArea commentArea=new TextArea();
	Button commentButton=new Button("Create Comment");
	int commentsCounter=0;

	FlexTable samplesTable=new FlexTable();
	TextArea sampleArea=new TextArea();
	Button sampleButton=new Button("Create Sample");
	
	Button moreButton=new Button(GlobalSettings.constants.more()+">>");
	Button lessButton=new Button(GlobalSettings.constants.less()+"<<");
	
	VerticalPanel morePanel=new VerticalPanel();
	
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
		
		tsLabel=new Label(suggestion.getCaption()/*+"="+term.getCaption()*/);
				
		add(suggestionPanel);
		suggestionPanel.add(tsLabel);
		suggestionPanel.add(likeButton);
		
		add(descriptionPanel);
		descriptionPanel.add(interactionPanel);
		
		//interactionPanel.add(likesLabel);
		//likesLabel.setText(termSuggestion.getLikes()+" "+GlobalSettings.constants.people());

		closeButton.addClickHandler(controller);
		likeButton.addClickHandler(controller);
		commentButton.addClickHandler(controller);
		sampleButton.addClickHandler(controller);
		
		suggestorLabel=new Label(constants.suggestor()+": "+suggestion.getUser());
		
		Iterator<InteractionComposite> icIterator=termSuggestion.getInteractions().iterator();
		
		StringBuilder votersBuilder=new StringBuilder(GlobalSettings.constants.supporters()+" : ");
		StringBuilder lessBuilder=new StringBuilder(GlobalSettings.constants.supporters()+" : ");
		int less=0;
		while (icIterator.hasNext()) {
			InteractionComposite ic=icIterator.next();
			
			Object icClass=ic.getClass();
			if (icClass.equals(LikeComposite.class)) {
				LikeComposite likeComposite=(LikeComposite)ic;
				votersBuilder.append(ic.getUser());
				votersBuilder.append(", ");
				
				if (less<2) {
					lessBuilder.append(ic.getUser());
					lessBuilder.append(", ");
					less++;
				}
			} else if (icClass.equals(CommentComposite.class)) {
				CommentComposite commentComposite=(CommentComposite)ic;
				commentsTable.setText(commentsCounter, 1, commentComposite.getUser()+constants.said());
				commentsTable.setText(commentsCounter++, 0, commentComposite.getFeedback());
			} else if (icClass.equals(UseCaseComposite.class)) {
				UseCaseComposite sampleComposite=(UseCaseComposite)ic;
				samplesTable.setText(samplesCounter, 1, sampleComposite.getUser()+constants.said());
				samplesTable.setText(samplesCounter++, 0, sampleComposite.getFeedback());
			}
		}
		
		votersString=votersBuilder.toString();
		int l=tsVO.getLikes();
		if (l>2) {
			lessBuilder.append(GlobalSettings.constants.and());
			lessBuilder.append(l-less);
			lessBuilder.append(GlobalSettings.constants.others());
		}
		lessVoters=lessBuilder.toString();
		votersLabel=new Label(lessVoters);
		descriptionPanel.add(votersLabel);
		
		add(suggestorLabel);
		add(closeButton);
		morePanel.add(moreButton);
		
		descriptionPanel.add(morePanel);
		
		moreButton.addClickHandler(controller);
		lessButton.addClickHandler(controller);
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

		votersString=GlobalSettings.constants.supporters()+" : ";
		commentsCounter=0;
		samplesCounter=0;
		commentsTable.clear();
		samplesTable.clear();
		Iterator<InteractionComposite> icIterator=termSuggestion.getInteractions().iterator();
		while (icIterator.hasNext()) {
			InteractionComposite ic=icIterator.next();

			Object icClass=ic.getClass();
			if (icClass.equals(LikeComposite.class)) {
				LikeComposite likeComposite=(LikeComposite)ic;
				votersString=votersString.concat(ic.getUser()+", ");
			} else if (icClass.equals(CommentComposite.class)) {
				CommentComposite commentComposite=(CommentComposite)ic;
				commentsTable.setText(commentsCounter, 1, commentComposite.getUser()+constants.said());
				commentsTable.setText(commentsCounter++, 0, commentComposite.getFeedback());
			} else if (icClass.equals(UseCaseComposite.class)) {
				UseCaseComposite sampleComposite=(UseCaseComposite)ic;
				samplesTable.setText(samplesCounter, 1, sampleComposite.getUser()+constants.said());
				samplesTable.setText(samplesCounter++, 0, sampleComposite.getFeedback());
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
	
	public void more() {
		morePanel.add(commentArea);
		morePanel.add(commentButton);
		morePanel.add(commentsTable);

		morePanel.add(sampleArea);
		morePanel.add(sampleButton);
		morePanel.add(samplesTable);

		morePanel.add(lessButton);
		morePanel.remove(moreButton);
		
		votersLabel.setText(votersString);
	}
	
	public void less() {
		morePanel.remove(commentArea);
		morePanel.remove(commentButton);
		morePanel.remove(commentsTable);

		morePanel.remove(sampleArea);
		morePanel.remove(sampleButton);
		morePanel.remove(samplesTable);
	
		morePanel.add(moreButton);
		morePanel.remove(lessButton);
		
		votersLabel.setText(lessVoters);
	}
}
