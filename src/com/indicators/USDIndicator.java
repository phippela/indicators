package com.indicators;

import java.util.Collection;
import java.util.HashSet;

/*
 * USD 5.3 million 
 */
public class USDIndicator implements Indicator {

	private String className = "";
	private String tag = "";
	
	public USDIndicator(String classNameIN,String tagIN) {
		this.className = classNameIN;
		this.tag = tagIN;
	}
	
	@Override
	public Collection<Annotation> identify(String text, int offset) {
		Collection<Annotation> matches = new HashSet<Annotation>();
		
		// Lets find if we got hit
		
		// Then verify that hit is hit...
		
		// And repeat for all the matches
		
		return null;
	}

	@Override
	public Collection<Annotation> identify(String text, int offset, Indicator condition) {
		Collection<Annotation> matches = condition.identify(text,offset);
		
		if(matches.size()>0)
				matches = this.identify(text,offset);
		
		return matches;
	}

}
