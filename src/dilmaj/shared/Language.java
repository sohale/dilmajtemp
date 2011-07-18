package dilmaj.shared;

import com.google.gwt.core.client.GWT;

import dilmaj.client.DilmajConstants;

public enum Language {
	UNKNOWN("Unknown") {
		public int indexOf() {
			return 0;
		}
		public String toString() {
			return constants.unknown();
		}
	},
	PERSIAN("Persian") {
		public int indexOf() {
			return 1;
		}
		public String toString() {
			return constants.persian();
		}
	},
	ENGLISH("English") {
		public int indexOf() {
			return 2;
		}
		public String toString() {
			return constants.english();
		}
	},
	FRENCH("French") {
		public int indexOf() {
			return 3;
		}
		public String toString() {
			return constants.french();
		}
	},
	ARABIC("Arabic") {
		public int indexOf() {
			return 4;
		}
		public String toString() {
			return constants.arabic();
		}
	};
	
	private static DilmajConstants constants = GWT.create(DilmajConstants.class);
	
	private final String languageName;
	Language(String name) {
		languageName=name.toUpperCase();
	}
	
	@Override
	public String toString() {
		return languageName;
	}
	
	public static Language getLanguage(int index) {
		Language[] languages=values();
		
		for (Language language:languages) {
			if (language.indexOf()==index)
				return language;
		}
		
		return UNKNOWN;
	}
	
	public abstract int indexOf();
}
	
	/*public static String[] getAllLanguages() {
		String[] languages={"Unknown", "Persian", "English", "French", "Arabic"};
		return languages;
	}*/

	/*public static int getLanguageIndex(String string) {
		// TODO Auto-generated method stub
		if (string.compareToIgnoreCase("Persian")==0)
			return 1;
		if (string.compareToIgnoreCase("English")==0)
			return 2;
		if (string.compareToIgnoreCase("French")==0)
			return 3;
		if (string.compareToIgnoreCase("Arabic")==0)
			return 4;
		return 0;
	}
}*/
