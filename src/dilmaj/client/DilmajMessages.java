package dilmaj.client;

import com.google.gwt.i18n.client.Messages;

public interface DilmajMessages extends Messages {
  @DefaultMessage("Dilmaj is a free, web-based, collaborative, multilingual project about language and language development supported by a non-profit group of developers. Persian language is challenged in modern times. Dilmaj is a central place that users contribute in development of Persian language, and its relation to other languages through online and open collaboration by virtually all people who speak the language.")
  String describeProject(String username);
}