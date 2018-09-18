package com.indicators;
import java.util.*;

public interface Indicator {
	
	/* Identify all instances from the text. This could be sentence. Offset is for indicating,
	 * from start of the plain text.
	 */
	 public Collection<Annotation> identify(String text, int offset);
	
	 /*
	  * Allows passing another condition that should also match.
	  */
	 public Collection<Annotation> identify(String text, int offset, Indicator condition);
	 
}
