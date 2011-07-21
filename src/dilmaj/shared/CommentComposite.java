package dilmaj.shared;

import dilmaj.client.domain.Interaction;

public class CommentComposite extends InteractionComposite {
	private String feedback;

	public CommentComposite() {
		super();
	}
	
	public CommentComposite(Interaction comment) {
		//if (like.getTimestamp()!=null)
			if (comment.getId()!=null)
			//	if (like.getUsername()!=null)
				{
					this.timeStamp=comment.getTimestamp();
					this.id=comment.getId();
					this.username=comment.getUsername();
					this.feedback=comment.getFeedback();
				}
	}

	public String getFeedback() {
		// TODO Auto-generated method stub
		return feedback;
	}
	
	public void setFeedback(String newFeedback) {
		feedback=newFeedback;
	}
}
