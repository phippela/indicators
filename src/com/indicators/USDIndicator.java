package com.indicators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
 * USD 5.3 million 
 */
public class USDIndicator implements Indicator {

	public static final String MILLION = "million";
	public static final String BILLION = "billion";
	public static final String TRILLION = "trillion";
	public static final String MONEY = "money";
	
	private String className = "";
	private String tag = "";
	
	private Set<String> millionPostNames = new HashSet<String>();
	private Set<String> billionPostNames = new HashSet<String>();
	private Set<String> trillionPostNames = new HashSet<String>();
	
	
	// These cases are for forward matches
			/*
			public static final String USDF = "$66.1 billion";
			public static final String USDD = "US$100 billion";
			public static final String USDI = "$3.5-$4.5 billion";
			public static final String USDJ = "$7-$12 billion";
			*/
	private Set<String> preMatches = new HashSet<String>();
	
	private List<String> okCharacters = new ArrayList<String>();
	
	public USDIndicator(String classNameIN,String tagIN) {
		this.className = classNameIN;
		this.tag = tagIN;
		
		millionPostNames.add(" million dollars");
		millionPostNames.add(" million u.s. dollars");
		millionPostNames.add(" million us dollars");
		
		billionPostNames.add(" billion dollars");
		billionPostNames.add(" billion u.s. dollars");
		billionPostNames.add(" billion us dollars");
		
		trillionPostNames.add(" trillion dollars");
		trillionPostNames.add(" trillion u.s. dollars");
		trillionPostNames.add(" trillion us dollars");
		
		preMatches.add("US$");
		preMatches.add("$");
		
		
		// These are characters that we allow as 'numbers', so these are digits
		// plus . and , comma being silent.
		
		okCharacters.add("0");
		okCharacters.add("1");
		okCharacters.add("2");
		okCharacters.add("3");
		okCharacters.add("4");
		okCharacters.add("5");
		okCharacters.add("6");
		okCharacters.add("7");
		okCharacters.add("8");
		okCharacters.add("9");
		okCharacters.add("0");
		okCharacters.add(",");
		okCharacters.add(".");
		
	}
	
	@Override
	public Collection<Annotation> identify(String text, int offset) {
		Collection<Annotation> matches = new HashSet<Annotation>();
		
		if(text == null || "".equals(text.trim()))
				return matches;
		
		// in order not to mess with the white spaces, we should not trim the actual string
		String lowerText = text.toLowerCase();
		
		// millions
		findPostMatches(lowerText, offset, this.MILLION, millionPostNames,matches);
		
		// billions
		findPostMatches(lowerText, offset,this.BILLION, billionPostNames,matches);
		
		// trillions
		findPostMatches(lowerText, offset,this.TRILLION, trillionPostNames,matches);
		
		// AND THEN the same for PRE matches
		
		
		
		return matches;
	}

	public void findPostMatches(String text, int offset, String matchType, Set<String>postNames, 
			Collection<Annotation> matches) {
		int currentIndex = 0;
		Iterator<String> iterator = postNames.iterator();
		
		// here we loop through all possible post matches
		while(iterator.hasNext()) {
			String postString = iterator.next();
			currentIndex = 0;
		
			// as long we find possible matches...
			int matchIndex = text.indexOf(postString,currentIndex);
			while(matchIndex > 0 ) {
				
				System.out.println("postString="+postString + " currentIndex="+currentIndex);
				// We have a possible match
				isPostMatch(text, offset, matchIndex,postString,matchType, matches);
				
				// lets go forward to the last match + 1
				currentIndex = matchIndex+1;
				matchIndex = text.indexOf(postString,currentIndex);	
			}
			
		}
		
	}
	
	// Potential match, lets determine do we have a value
	// We need to look backwards in post match determination
	public void isPostMatch(String text, int offset, int matchIndex, String postString, String matchType,
			Collection<Annotation> matches) {
		
		System.out.println("We are at isPostMatch:"+text+" offset:"+offset+ " matchIndex="+matchIndex+ " postString="+postString+
				" matches.size()="+matches.size());
		int numbersInFront = findNumbersInFront(text,matchIndex);
		System.out.println("numbers in front="+numbersInFront);
		if(numbersInFront <= 0)
			return;
		
		String valueString = text.substring(matchIndex-numbersInFront, matchIndex);
		
		String fullString = valueString+postString;
		
		// checking for "US " in front
		int usCorrection = 0;
		if((matchIndex-numbersInFront-3) >= 0)
			if("us ".equals(text.substring(matchIndex-numbersInFront-3, matchIndex-numbersInFront))) {
					fullString="us "+fullString;
					usCorrection = -3;
				}	
				
		// TODO DEBUG
		System.out.println("fullString="+fullString);
		System.out.println("valueString="+valueString);
		// This is for making sure that 1,000 is seen as 1000
		String valueWithoutCommas = valueString.replaceAll(",",""); 
		System.out.println("valueWithoutCommas="+valueWithoutCommas);
		System.out.println("");
		
		Annotation annotation = new Annotation();
		annotation.className = this.className+postString; // We assume that we can just post append
		annotation.value = valueWithoutCommas;
		annotation.start = offset + usCorrection + (matchIndex-numbersInFront);
		annotation.stop = offset + (matchIndex+postString.length());
		annotation.tags.add(matchType);
		annotation.tags.add(MONEY);
		annotation.fullString = fullString;
		matches.add(annotation);
		
		/*
		public static final String USDA = "9,504 billion dollars"; // Note this is 9540 billion in contrast to 9.504 trillion
		public static final String USDB = "13.45 billion U.S. dollars";
		public static final String USDC = "200 billion U.S. dollars";
		public static final String USDE = "3 billion dollars";
		public static final String USDH = "25.5 Billion US Dollars";
		public static final String USDG = "US 1.9 billion dollars"; // note this we are not checking... the US TODO...
		 */
		
	}
	
	public int findNumbersInFront(String text, int matchIndex)  {
		int matches = 0;
		
		int counter = -1;
		// Let's not go negative ever...
		boolean match = true;
		while(matchIndex +counter >= 0 && match) {
			String character = text.substring(matchIndex+counter, matchIndex+counter+1); 
			System.out.println("Testing character=("+character+")");
			// lets check do we have a hit
			match = false;
			for(int i = 0 ; i < okCharacters.size() && !match; i ++) {
				if(okCharacters.get(i).equals(character))
					match = true;
			}
			
			// only in case we have previous matching character... we increase the matching
			if(match)  {
				counter-=1;
				matches++;
			}
		}
		
		return matches;
		
	}
	
	
	@Override
	public Collection<Annotation> identify(String text, int offset, Indicator condition) {
		Collection<Annotation> matches = condition.identify(text,offset);
		
		if(matches.size()>0)
				matches = this.identify(text,offset);
		
		return matches;
	}

}
