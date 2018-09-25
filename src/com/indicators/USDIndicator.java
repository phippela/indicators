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

	// PUT this true to enable sytem out debugs...
	public static final boolean DEBUG = true;

	public static final String MILLION = "million";
	public static final String BILLION = "billion";
	public static final String TRILLION = "trillion";
	public static final String MONEY = "money";

	private String className = "";
	private String tag = "";

	private Set<String> millionPostNames = new HashSet<String>();
	private Set<String> billionPostNames = new HashSet<String>();
	private Set<String> trillionPostNames = new HashSet<String>();


	// These cases are for forward matches $5 million style... so $ is the preMatch indicator
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

		if(DEBUG)
			System.out.println("before million");
		// millions
		findPostMatches(lowerText, offset, this.MILLION, millionPostNames,matches);

		if(DEBUG)
			System.out.println("before billion");
		// billions
		findPostMatches(lowerText, offset,this.BILLION, billionPostNames,matches);

		if(DEBUG)
			System.out.println("before trillion");
		// trillions
		findPostMatches(lowerText, offset,this.TRILLION, trillionPostNames,matches);

		if(DEBUG)
			System.out.println("before find pre matches");
		// AND THEN the same for PRE matches
		findPreMatches(lowerText,offset,preMatches, matches);

		return matches;
	}


	public void findPreMatches(String text, int offset, Set<String>postNames, 
			Collection<Annotation> matches) {
		int currentIndex = 0;
		Iterator<String> iterator = postNames.iterator();

		// here we loop through all possible post matches as of now its $ sign 
		while(iterator.hasNext()) {
			String postString = iterator.next();
			currentIndex = 0;

			// as long we find possible matches...
			int matchIndex = text.indexOf(postString,currentIndex);
			if(DEBUG)
				System.out.println("text="+text+" matchIndex="+matchIndex + " postString="+postString + " currentIndex="+currentIndex);
	
			while(matchIndex >= 0 ) {
				if(DEBUG)
					System.out.println("preString="+postString + " currentIndex="+currentIndex);
				// We have a possible match
				isPreMatch(text, offset, matchIndex,postString, matches);

				// lets go forward to the last match + 1
				currentIndex = matchIndex+1;
				matchIndex = text.indexOf(postString,currentIndex);	
			}

		}

	}

	public boolean matches(String text, int startIndex, String compareString) {
		boolean matches = false; 
		
		int length = compareString.length();
		if(text.length() > startIndex + length) {
			if(DEBUG)
				System.out.println("Extract is:("+text.substring(startIndex, startIndex+ length)+")");
			if(compareString.equals(text.substring(startIndex, startIndex+ length)))
				matches=true;
			
		}
		
		return matches;
	}
	
	// Potential match, lets determine do we have a value
	// We need to look backwards in post match determination
	public void isPreMatch(String text, int offset, int matchIndex, String preString, 
			Collection<Annotation> matches) {

		String matchType = ""; // This is determined after the number value like $5 billion... 
	
		if(DEBUG)
			System.out.println("We are at isPostMatch:"+text+" offset:"+offset+ " matchIndex="+matchIndex+ " preString="+preString+
					" matches.size()="+matches.size());
		int numbersAfter = findNumbersAfter(text,matchIndex);
		
		if(DEBUG)
			System.out.println("numbers in after="+numbersAfter);
		
		if(numbersAfter <= 0)
			return;

		
		String valueString = text.substring(matchIndex+preString.length(),matchIndex+preString.length()+numbersAfter);
		if(DEBUG)
			System.out.println("valueString="+valueString);
		// We want to have the ' '+million or ' '+ billion or ' ' + trillion
		boolean hit = false;
		int startIndex = matchIndex+preString.length()+numbersAfter;
		if(matches(text,startIndex," "+MILLION)) {
			matchType = MILLION;
			hit = true;
		}
			
		if(!hit && matches(text,startIndex," "+BILLION)) {
			matchType = BILLION;
			hit = true;
		}
	
		if(!hit && matches(text,startIndex," "+TRILLION))  {
			matchType = TRILLION;
			hit = true;
		}
		// If no hit we are done.
		if(!hit)
			return;

		
		String fullString = preString+valueString+ ' '+matchType;
		if(DEBUG)
			System.out.println("fullString="+fullString);
		
		// In here we do not want to make duplicate matches... like we should check 
		// that there is not ' million dollars' ' billion dollars' or ' trillion dollars' after.
		// It is bit hackish....
		hit = false;
		startIndex = matchIndex + fullString.length();
		if(matches(text, startIndex, " dollars"))
			return;
		
		// checking for "US " in front
		int usCorrection = 0;
		if((matchIndex-2) >= 0)
			if("us".equals(text.substring(matchIndex-2, matchIndex))) {
				fullString="us"+fullString;
				usCorrection = -2;
			}	

		String valueWithoutCommas = valueString.replaceAll(",",""); 

		// TODO DEBUG
		if(DEBUG) {
			System.out.println("fullString="+fullString);
			System.out.println("valueString="+valueString);
			// This is for making sure that 1,000 is seen as 1000

			System.out.println("valueWithoutCommas="+valueWithoutCommas);
			System.out.println("");
		}
		Annotation annotation = new Annotation();
		annotation.className = this.className+matchType; // We assume that we can just post append
		annotation.value = valueWithoutCommas;
		annotation.start = offset + usCorrection + (matchIndex);
		annotation.stop = offset + (matchIndex+fullString.length()+usCorrection);
		annotation.tags.add(matchType);
		annotation.tags.add(MONEY);
		annotation.fullString = fullString;
		matches.add(annotation);

		if(DEBUG) {
			System.out.println("annotation.start="+annotation.start);
		}
			
		// Now lets check for an extra annotation
		if((annotation.start-1-offset) > 0) {
			// We want to have '-' character
			if("-".equals(text.substring(annotation.start-1-offset, annotation.start-offset))) {
				if(DEBUG)
					System.out.println("We think there is '-' possible additional annotation !");
				int numbersFront = findNumbersInFront(text, annotation.start-1-offset);
				if(numbersFront<=0) {
					System.out.println("But no numbers in front");
					return;
				}
				if((annotation.start-offset-1-numbersFront-preString.length())<0) {
					System.out.println("running out of space");
				}
				//Checking for pre string in front
				String testString = text.substring(annotation.start-offset-1-numbersFront-preString.length(),
						annotation.start-offset-1-numbersFront);
				if(DEBUG) {
					System.out.println("TEST STRING IS:"+testString);
				}
				if(preString.equals(testString)) {
					if(DEBUG)
						System.out.println("And a match !");
					Annotation annotationB = new Annotation();
					
					annotationB.className = annotation.className; 
					annotationB.start = annotation.start - preString.length()-numbersFront-1; //offset + usCorrection + (matchIndex);
					annotationB.stop = annotation.stop; //offset + (matchIndex+fullString.length()+usCorrection);
					annotationB.tags.add(matchType);
					annotationB.tags.add(MONEY);
					annotationB.fullString = text.substring(annotationB.start-offset,annotationB.stop-offset); 
					annotationB.value = (text.substring(annotationB.start-offset+preString.length(),
							annotationB.start-offset+preString.length()+numbersFront)).replace(",", "");
					matches.add(annotationB);
					
					
				}
					
					
			}
			
		}
		
		/*
		public static final String USDF = "$66.1 billion";
		public static final String USDD = "US$100 billion";
		public static final String USDI = "$3.5-$4.5 billion"; // TODO WE CAN ADD THE FRONT LATER
		public static final String USDJ = "$7-$12 billion"; // TODO WE CAN ADD THE FRONT LATER
		 */

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

		if(DEBUG) 
			System.out.println("We are at isPostMatch:"+text+" offset:"+offset+ " matchIndex="+matchIndex+ " postString="+postString+
					" matches.size()="+matches.size());
		int numbersInFront = findNumbersInFront(text,matchIndex);
		if(DEBUG) 
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

		String valueWithoutCommas = valueString.replaceAll(",",""); 

		// TODO DEBUG
		if(DEBUG) {
			System.out.println("fullString="+fullString);
			System.out.println("valueString="+valueString);
			// This is for making sure that 1,000 is seen as 1000

			System.out.println("valueWithoutCommas="+valueWithoutCommas);
			System.out.println("");
		}

		Annotation annotation = new Annotation();
		annotation.className = this.className+matchType; // We assume that we can just post append
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
			if(DEBUG)
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



	public int findNumbersAfter(String text, int matchIndex)  {
		int matches = 0;

		int counter = 1;
		// Let's not go over the string ever...
		boolean match = true;
		while(matchIndex +counter <= text.length()-1 && match) {
			String character = text.substring(matchIndex+counter, matchIndex+counter+1); 
			if(DEBUG)
				System.out.println("Testing character=("+character+")");
			// lets check do we have a hit
			match = false;
			for(int i = 0 ; i < okCharacters.size() && !match; i ++) {
				if(okCharacters.get(i).equals(character))
					match = true;
			}

			// only in case we have previous matching character... we increase the matching
			if(match)  {
				counter+=1;
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
