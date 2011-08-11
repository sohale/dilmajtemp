package dilmaj.shared;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.core.client.GWT;

import dilmaj.client.DilmajConstants;

public enum Language implements Iterable<Language>, Iterator<Language> {
	UNKNOWN("Unknown") {
		public int indexOf() {
			return 0;
		}
		public String toString() {
			if (constants==null)
				constants=GWT.create(DilmajConstants.class);
			return constants.unknown();
		}
	},
	PERSIAN("Persian") {
		public int indexOf() {
			return 1;
		}
		public String toString() {
			if (constants==null)
				constants=GWT.create(DilmajConstants.class);
			return constants.persian();
		}
	},
	ENGLISH("English") {
		public int indexOf() {
			return 2;
		}
		public String toString() {
			if (constants==null)
				constants=GWT.create(DilmajConstants.class);
			return constants.english();
		}
	},
	FRENCH("French") {
		public int indexOf() {
			return 3;
		}
		public String toString() {
			if (constants==null)
				constants=GWT.create(DilmajConstants.class);
			return constants.french();
		}
	},
	ARABIC("Arabic") {
		public int indexOf() {
			return 4;
		}
		public String toString() {
			if (constants==null)
				constants=GWT.create(DilmajConstants.class);
			return constants.arabic();
		}
	};
	
	private static DilmajConstants constants=null;
	
	private final String languageName;
	private static int index=-1;
	
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
	
	public static Iterable<Language> getIterable() {
		return UNKNOWN;
	}
	
	@Override
	public Iterator<Language> iterator() {
		index=indexOf()-1;
		return this;
	}
	
	@Override
	public boolean hasNext() {
		if (index>=4)
			return false;
		return true;
	}
	
	@Override
	public Language next() {
		switch (index) {
		case -1:
			index=0;
			return UNKNOWN;
		case 0:
			index=1;
			return PERSIAN;
		case 1:
			index=2;
			return ENGLISH;
		case 2:
			index =3;
			return FRENCH;
		case 3:
			index=4;
			return ARABIC;
		default:
			index=-1;
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();		
	}
}