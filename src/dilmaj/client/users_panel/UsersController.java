package dilmaj.client.users_panel;

import com.google.gwt.core.client.GWT;

import dilmaj.client.DilmajUserService;
import dilmaj.client.DilmajUserServiceAsync;
import dilmaj.shared.Controller;

public class UsersController extends Controller {
	private DilmajUserServiceAsync accountSvc = GWT.create(DilmajUserService.class);

	private UsersPanel panel;
	
	public UsersController(UsersPanel panel) {
		this.panel=panel;
	}
}
