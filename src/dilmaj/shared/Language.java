package dilmaj.shared;

public final class Language {
	public static int UNKNOWN=0;
	public static int PERSIAN=1;
	public static int ENGLISH=2;
	public static int FRENCH=3;
	public static int ARABIC=4;
	
	public static String[] getAllLanguages() {
		String[] languages={"Unknown", "Persian", "English", "French", "Arabic"};
		return languages;
	}

	public static int getLanguageIndex(String string) {
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
}
