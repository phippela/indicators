package com.indicators;

import java.util.HashSet;
import java.util.Set;

public class Annotation {
	/* This should contain the total from the start of the text */
	public int start = -1; 
	/* Similarly to the stop, contains offset if any */
	public int stop = -1;
	public String className = "";
	public String value;
	public String fullString ="";
	
	// Optional for having identities that are recognized by this annotation
	// For example this could be USD or MILLION
	public Set<String> tags = new HashSet<String>();
}
