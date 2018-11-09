package com.indicators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Purchase, acquire... 
 */
public class MonthIndicator implements Indicator {

	// PUT this true to enable system out debugs...
	public static final boolean DEBUG = false;

	public static final String MONTH = "month";

	private String className = "";
	private String tag = ""; 

	private Set<String> monthWords = new HashSet<String>();
	private Map<String,String> monthValues = new HashMap<String,String>();
	
	/*
	 * TODO
	 
	 April 11, 2016
fourth quarter of 2017
in 2017
in January 2017
December 2015 and July 2016
21 May

	 */

	public MonthIndicator(String classNameIN ,String tagIN) {
		this.className = classNameIN;
		this.tag = tagIN;

		monthWords.add("January");
		monthWords.add("Jan.");
		monthWords.add("Jan ");
		monthValues.put("January", "1");
		monthValues.put("Jan.", "1");
		monthValues.put("Jan ", "1");
		
		monthWords.add("February");
		monthWords.add("Feb.");
		monthWords.add("Feb ");
		monthValues.put("February", "2");
		monthValues.put("Feb.", "2");
		monthValues.put("Feb ", "2");
		
		monthWords.add("March");
		monthWords.add("Mar.");
		monthWords.add("Mar ");
		monthValues.put("March", "3");
		monthValues.put("Mar.", "3");
		monthValues.put("Mar ", "3");
		
		monthWords.add("April");
		monthWords.add("Apr.");
		monthWords.add("Apr ");
		monthValues.put("April", "4");
		monthValues.put("Apr.", "4");
		monthValues.put("Apr ", "4");
		
		monthWords.add("May");
		monthValues.put("May", "5");
		
		monthWords.add("June");
		monthWords.add("Jun.");
		monthWords.add("Jun ");
		monthValues.put("June", "6");
		monthValues.put("Jun.", "6");
		monthValues.put("Jun ", "6");
		
		monthWords.add("July");
		monthWords.add("Jul.");
		monthWords.add("Jul ");
		monthValues.put("July", "7");
		monthValues.put("Jul.", "7");
		monthValues.put("Jul ", "7");
		
		monthWords.add("August");
		monthWords.add("Aug.");
		monthWords.add("Aug ");
		monthValues.put("August", "8");
		monthValues.put("Aug.", "8");
		monthValues.put("Aug ", "8");
		
		monthWords.add("September");
		monthWords.add("Sep.");
		monthWords.add("Sep ");
		monthWords.add("Sept.");
		monthWords.add("Sept ");
		monthValues.put("September", "9");
		monthValues.put("Sep.", "9");
		monthValues.put("Sep ", "9");
		monthValues.put("Sept.", "9");
		monthValues.put("Sept ", "9");
		
		monthWords.add("October");
		monthWords.add("Oct.");
		monthWords.add("Oct ");
		monthValues.put("October", "10");
		monthValues.put("Oct.", "10");
		monthValues.put("Oct ", "10");
		
		monthWords.add("November");
		monthWords.add("Nov.");
		monthWords.add("Nov ");
		monthValues.put("November", "11");
		monthValues.put("Nov.", "11");
		monthValues.put("Nov ", "11");
		
		monthWords.add("December");
		monthWords.add("Dec.");
		monthWords.add("Dec ");
		monthValues.put("December", "12");
		monthValues.put("Dec.", "12");
		monthValues.put("Dec ", "12");
		
	}

	@Override
	public Collection<Annotation> identify(String text, int offset) {
		Collection<Annotation> matches = new HashSet<Annotation>();

		if(text == null || "".equals(text.trim()))
			return matches;

		// in order not to mess with the white spaces, we should not trim the actual string
		// also month names are sensitive for case
		String lowerText = text;

		if(DEBUG)
			System.out.println("before recognizing months");
		// millions
		findBusinessMatches(lowerText, offset, this.MONTH, monthWords,monthValues, matches);

		return matches;
	}


	public void findBusinessMatches(String text, int offset, String matchType, Set<String>postNames, Map<String,String> monthValues,
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
		
		String fullString = text.substring(matchIndex,matchIndex+preString.length()); //+numbersAfter);
		
		String valueString = ""+monthValues.get(""+fullString);
		
		// We could check for day here... its pre or post after month string - but no time now...
		
	
		// TODO DEBUG
		if(DEBUG) {
			System.out.println("fullString="+fullString);
			System.out.println("valueString="+valueString);
			System.out.println("");
		}
		Annotation annotation = new Annotation();
		annotation.className = this.className+matchType; // We assume that we can just post append
		annotation.value = valueString;
		annotation.start = offset + (matchIndex);
		annotation.stop = offset + (matchIndex + fullString.length());
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
