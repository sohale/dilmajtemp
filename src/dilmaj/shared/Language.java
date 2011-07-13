package dilmaj.shared;

public enum Language {
	UNKNOWN("Unknown") {
		public int indexOf() {
			return 0;
		}
	},
	PERSIAN("Persian") {
		public int indexOf() {
			return 1;
		}
	},
	ENGLISH("English") {
		public int indexOf() {
			return 2;
		}
	},
	FRENCH("French") {
		public int indexOf() {
			return 3;
		}
	},
	ARABIC("Arabic") {
		public int indexOf() {
			return 4;
		}
	};
	
	private final String languageName;
	Language(String name) {
		languageName=name.toUpperCase();
	}
	
	@Override
	public String toString() {
		return languageName;
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
