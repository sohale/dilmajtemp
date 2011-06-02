package dilmaj.client.register;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.shared.Controller;
import dilmaj.shared.EmailComposite;
import dilmaj.shared.MemberComposite;
import dilmaj.shared.MessageComposite;

public class RegisterController extends Controller implements ClickHandler {
	private RegisterPanel panel;
	private DilmajUserServiceAsync userSvc = GWT.create(DilmajUserService.class);
	//private 
	
	public RegisterController(RegisterPanel panel) {
		this.panel=panel;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		String username=panel.getUsername();
		String password=panel.getPassword();
		String adminPin=panel.getAdminPin();
		String email=panel.getEmail();
		String re_password=panel.getRePassword();
		String re_adminPin=panel.getReAdminPin();
		String re_email=panel.getReEmail();
		String name=panel.getNewName();
		
		if (password.compareTo(re_password)!=0) {
			panel.setMessage(new MessageComposite("Passwords do not match!"));
		} else if (email.compareTo(re_email)!=0) {
			panel.setMessage(new MessageComposite("Emails do not match!"));
		} else if (adminPin.compareTo(re_adminPin)!=0) {
			panel.setMessage(new MessageComposite("Admin pins do not match!"));
		} else {
			MemberComposite userVO=new MemberComposite(username, password, adminPin, email, name);
			
			userSvc.create(userVO, new CreateUserCallback(panel));
		}
	}

	public void sendConfirmationEmail(MemberComposite userVO) {
		EmailComposite emailVO=new EmailComposite("Dilmaj Registeration Confirmation", userVO.getEmail(), "Click on the following link to finish your registeration http://dilmajtest.appspot.com/?activator="+userVO.getActivator()+"&username="+userVO.getUsername());
		userSvc.sendMail(emailVO, new SendConfirmationEmailCallback(panel));
	}
	
	public void cofirmRegisteration(String username, String activation) {
		
	}
}
