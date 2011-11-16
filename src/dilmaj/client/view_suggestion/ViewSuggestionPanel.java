package dilmaj.client.view_suggestion;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import dilmaj.client.DilmajConstants;
import dilmaj.client.MyPanel;
import dilmaj.client.termPanel.TermButton;
import dilmaj.shared.CommentComposite;
import dilmaj.shared.GlobalSettings;
import dilmaj.shared.InteractionComposite;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.MessageComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;
import dilmaj.shared.TimestampDescendingInteractionComparator;
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
		
	FlexTable suggestionTableHeader= new FlexTable();
	
	VerticalPanel suggestionPanel=new VerticalPanel();
	//VerticalPanel descriptionPanel=new VerticalPanel();
	//HorizontalPanel interactionPanel=new HorizontalPanel();
	
	Image likeButton=new Image("images/like.jpg");
	Label likesLabel=new Label();
	String votersString=constants.supporters()+" ";
	String lessVoters=constants.supporters()+" ";
	ViewSuggestionController controller=new ViewSuggestionController(this);

	FlexTable commentsTable=new FlexTable();
	TextArea commentArea=new TextArea();
	Label commentButton=new Label("Create Comment");
	Label clearCommentButton=new Label("Clear Comment");
	int commentsCounter=0;

	FlexTable samplesTable=new FlexTable();
	TextArea sampleArea=new TextArea();
	Button sampleButton=new Button("Create Sample");
	
	Label moreButton=new Label(GlobalSettings.constants.more()+">>");
	Label lessButton=new Label(GlobalSettings.constants.less()+"<<");
	
	VerticalPanel morePanel=new VerticalPanel();
	FlexTable suggestionTable=new FlexTable();
	
	int samplesCounter=0;
	int beginRow;
	int nComment=0;
	
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
		
		tsLabel=new Label(suggestion.getCaption());
		
		add(suggestionTable);
		
		likeButton.addClickHandler(controller);
		likeButton.setStyleName("likeButton");
		commentButton.addClickHandler(controller);
		commentButton.setStyleName("termButton");
		sampleButton.addClickHandler(controller);
		//sampleButton.setStyleName("termButton");
		
		suggestorLabel=new Label(constants.suggestor()+": "+suggestion.getUser());
		
		SortedMap<Date, InteractionComposite> sortedInteractions=new TreeMap<Date, InteractionComposite>(new TimestampDescendingInteractionComparator());
		Iterator<InteractionComposite> iterator=termSuggestion.getInteractions().iterator();
		while (iterator.hasNext()) {
			InteractionComposite anInteraction=iterator.next();
			Date timeStamp=anInteraction.getTimeStamp();
			if (timeStamp!=null)
				sortedInteractions.put(anInteraction.getTimeStamp(), anInteraction);
		}
		
		Iterator<InteractionComposite> icIterator=sortedInteractions.values().iterator();
		while (icIterator.hasNext()) {
			InteractionComposite ic=icIterator.next();
			Object icClass=ic.getClass();
			if (icClass.equals(CommentComposite.class))
				++nComment;
		}
		
		icIterator=sortedInteractions.values().iterator();
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
		likesLabel.setText(l+"");
		
		FlexTable likeTable=new FlexTable();
		likeTable.setWidget(0, 0, likesLabel);
		likeTable.setWidget(0, 1, likeButton);
		likeTable.getCellFormatter().setHorizontalAlignment(0, 0, ALIGN_LEFT);
		likeTable.getCellFormatter().setVerticalAlignment(0, 0, ALIGN_BOTTOM);
		likeTable.getCellFormatter().setHorizontalAlignment(0, 1, ALIGN_LEFT);
		likeTable.getCellFormatter().setVerticalAlignment(0, 1, ALIGN_BOTTOM);
		
		//FlexTable suggestionTableBody=new FlexTable();
		
		suggestionTable.setWidget(0, 0, suggestionTableHeader);
		suggestionTableHeader.setWidget(0, 0, likeTable);
		suggestionTableHeader.setWidget(0, 1, tsLabel);
		//suggestionTable.setWidget(1, 0, likeTable);
		suggestionTableHeader.setWidget(0, 2, suggestorLabel);
		suggestionTableHeader.setWidget(0, 3, votersLabel);
		suggestionTableHeader.getCellFormatter().setHorizontalAlignment(0, 1, ALIGN_RIGHT);
		suggestionTableHeader.getCellFormatter().setVerticalAlignment(0, 1, ALIGN_TOP);
		suggestionTableHeader.getCellFormatter().setHorizontalAlignment(0, 2, ALIGN_RIGHT);
		suggestionTableHeader.getCellFormatter().setHorizontalAlignment(0, 3, ALIGN_RIGHT);
		suggestionTableHeader.setWidth("100%");
		suggestionTable.setWidth("100%");
		//suggestionTableHeader.getCellFormatter().setStyleName(0, 0, "suggestionCellOfSuggestionPanel");
		suggestionTableHeader.getCellFormatter().setStyleName(0, 1, "suggestionCellOfSuggestionPanel");
		suggestionTableHeader.getCellFormatter().setStyleName(0, 2, "suggestionCellOfSuggestionPanel");
		//suggestionTableHeader.getCellFormatter().setStyleName(0, 3, "suggestionCellOfSuggestionPanel");
		suggestionTableHeader.getCellFormatter().setWidth(0, 0, ""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.05+"px");
		suggestionTableHeader.getCellFormatter().setWidth(0, 1, ""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.174+"px");
		suggestionTableHeader.getCellFormatter().setWidth(0, 2, ""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.242+"px");
		suggestionTableHeader.getCellFormatter().setWidth(0, 3, ""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.37+"px");
		//suggestionTableHeader.getCellFormatter().setWidth(0, 4, ""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.1+"px");
		
		moreButton.setStyleName("termButton");
		moreButton.addClickHandler(controller);
		lessButton.setStyleName("termButton");
		lessButton.addClickHandler(controller);
		
		suggestionTableHeader.setWidget(0, 4, moreButton);
		suggestionTableHeader.getCellFormatter().setHorizontalAlignment(0, 4, ALIGN_LEFT);
		
		//suggestionTable.getRowFormatter().addStyleName(0, "FlexTable-Header");
		//beginRow=suggestionTable.getRowCount();
		suggestionTable.setWidget(1, 0, commentsTable);
		
		for (int i=1;i<commentsTable.getRowCount();++i) {
			commentsTable.getRowFormatter().setVisible(i, false);
		}
		
		//suggestionTable.setWidget(2, 0, samplesTable);
		
		FlexTable addCommentTable=new FlexTable();
		//addCommentTable.setText(0, 0, "Your Comment:");
		addCommentTable.setWidget(0, 0, commentArea);
		commentArea.setText(GlobalSettings.constants.yourComment());
		commentArea.addClickHandler(controller);
		commentArea.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.85+"px");
		commentButton.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.15+"px");
		clearCommentButton.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.15+"px");
		clearCommentButton.addClickHandler(controller);
		clearCommentButton.setStyleName("termButton");
		FlexTable buttonsTable=new FlexTable();
		buttonsTable.setWidget(0, 0, commentButton);
		buttonsTable.setWidget(1, 0, clearCommentButton);
		addCommentTable.setWidget(0, 1, buttonsTable);
		buttonsTable.getCellFormatter().setVerticalAlignment(0,0,ALIGN_TOP);
		buttonsTable.getCellFormatter().setVerticalAlignment(1,0,ALIGN_TOP);
		//addCommentTable.getColumnFormatter().setWidth(1, ""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.15+"px");
		commentButton.setWidth(""+GlobalSettings.getBrowserWidth()*GlobalSettings.getTermDetailsPanelRatio()*.15+"px");
		suggestionTable.setWidget(2, 0, addCommentTable);
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
		for (int i=1;i<commentsTable.getRowCount();++i) {
			commentsTable.getRowFormatter().setVisible(i, true);
		}
		suggestionTableHeader.clearCell(0, 4);
		suggestionTableHeader.setWidget(0, 4, lessButton);
	}
	
	public void less() {
		for (int i=1;i<commentsTable.getRowCount();++i) {
			commentsTable.getRowFormatter().setVisible(i, false);
		}
		suggestionTableHeader.clearCell(0, 4);
		suggestionTableHeader.setWidget(0, 4, moreButton);
	}
	
	public int getCommentsCount() {
		return commentsCounter;
	}
	
	public int getSamplesCount() {
		return samplesCounter;
	}

	public void clearCommentArea() {
		commentArea.setText(GlobalSettings.constants.yourComment());
	}
}
