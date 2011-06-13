package dilmaj.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dilmaj.client.SettingsService;
import dilmaj.client.TermService;
import dilmaj.client.domain.Interaction;
import dilmaj.client.domain.Settings;
import dilmaj.client.domain.TermSuggestion;
import dilmaj.client.domain.Term;
import dilmaj.client.domain.User;
import dilmaj.shared.LikeComposite;
import dilmaj.shared.SettingsComposite;
import dilmaj.shared.TermComposite;
import dilmaj.shared.TermSuggestionComposite;

public class SettingsServiceImpl extends RemoteServiceServlet implements SettingsService {

	@Override
	public SettingsComposite update(SettingsComposite settingsVO) {
		// TODO Auto-generated method stub
        PersistenceManager pm = PMF.get().getPersistenceManager();
		Settings settings=new Settings(settingsVO);
		settings.setId(settingsVO.getId());
        try {
            pm.makePersistent(settings);
        } finally {
            pm.close();
        }
        return settingsVO;
	}

	@Override
	public SettingsComposite create(String username) {
		// TODO Auto-generated method stub
		Settings settings=new Settings();
		settings.setUsername(username);
		
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(settings);
        } finally {
            pm.close();
        }
		SettingsComposite settingsVO=new SettingsComposite(settings);

		return settingsVO;
	}
}
