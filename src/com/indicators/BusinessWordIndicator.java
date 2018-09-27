package com.indicators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
 * Purchase, acquire... 
 */
public class BusinessWordIndicator implements Indicator {

	// PUT this true to enable system out debugs...
	public static final boolean DEBUG = true;

	public static final String BUSINESS = "business";

	private String className = "";
	private String tag = ""; 

	private Set<String> businessWords = new HashSet<String>();
	

	public BusinessWordIndicator(String classNameIN ,String tagIN) {
		this.className = classNameIN;
		this.tag = tagIN;

		businessWords.add("buy assets");
		businessWords.add("invest in");
		businessWords.add("take over");
		businessWords.add("joint venture");
		businessWords.add("deal");
		businessWords.add("purchase");
		businessWords.add("aqcuire");
		businessWords.add("transaction");
		businessWords.add("buy stakes in");
		businessWords.add("merger");
		businessWords.add("stock purchase");
		
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
		findBusinessMatches(lowerText, offset, this.BUSINESS, businessWords,matches);

		return matches;
	}


	public void findBusinessMatches(String text, int offset, String matchType, Set<String>postNames, 
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
				isPreMatch(text, offset, matchIndex,matchType, postString, matches);

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
	public void isPreMatch(String text, int offset, int matchIndex, String matchType, String preString, 
			Collection<Annotation> matches) {

		//String matchType = ""; // This is determined after the number value like $5 billion... 
	
		if(DEBUG)
			System.out.println("We are at isPostMatch:"+text+" offset:"+offset+ " matchIndex="+matchIndex+ " preString="+preString+
					" matches.size()="+matches.size());
		
		String valueString = text.substring(matchIndex,matchIndex+preString.length()); //+numbersAfter);
		
		
		String fullString = valueString;
		if(DEBUG)
			System.out.println("fullString="+fullString);
		

		String valueWithoutCommas = valueString; 

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
		annotation.start = offset + (matchIndex);
		annotation.stop = offset + (matchIndex+fullString.length());
		annotation.tags.add(tag);
		annotation.fullString = fullString;
		matches.add(annotation);
	

	}

	
	@Override
	public Collection<Annotation> identify(String text, int offset, Indicator condition) {
		Collection<Annotation> matches = condition.identify(text,offset);

		if(matches.size()>0)
			matches = this.identify(text,offset);

		return matches;
	}

}
