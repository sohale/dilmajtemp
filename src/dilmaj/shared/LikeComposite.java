package dilmaj.shared;

import dilmaj.client.domain.Interaction;

public class LikeComposite extends InteractionComposite {
	public LikeComposite() {
		super();
	}
	
	public LikeComposite(Interaction like) {
		//if (like.getTimestamp()!=null)
			if (like.getId()!=null)
			//	if (like.getUsername()!=null)
				{
					this.timeStamp=like.getTimestamp();
					this.id=like.getId();
					this.username=like.getUsername();
				}
	}
}
