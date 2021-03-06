package dilmaj.shared;

import java.util.Date;

import dilmaj.client.domain.Interaction;


// Sample in data model
public class UseCaseComposite extends InteractionComposite {
	private String feedback;
	
	public UseCaseComposite() {
		super();
	}
	
	public UseCaseComposite(Interaction useCase) {
		//if (like.getTimestamp()!=null)
			if (useCase.getId()!=null)
			//	if (like.getUsername()!=null)
				{
					if (useCase.getTimestamp()==null)
						timeStamp=new Date();
					else
						this.timeStamp=useCase.getTimestamp();
					this.id=useCase.getId();
					this.username=useCase.getUsername();
					this.feedback=useCase.getFeedback();
				}
	}

	public String getFeedback() {
		// TODO Auto-generated method stub
		return feedback;
	}

	public void setFeedback(String comment) {
		this.feedback=comment;
	}
}
