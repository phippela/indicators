package com.indicators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

class MonthIndicatorTest {

	public static final String NORMAL_SENTENCE = "This is a normal, ordinary sentence.";
	public static final String START_OF_SENTENCE = "The investing deal size was";
	public static final String END_OF_SENTENCE = " as evaluated in end of 2017 third quarter.";

	public static final String MONTHA = "January";
	public static final String MONTHA_VALUE = "1";
	public static final String MONTHB = "Oct.";
	public static final String MONTHB_VALUE = "10";
	public static final String MONTHC = "Nov ";
	public static final String MONTHC_VALUE = "11";


	@Test
	void testStart() {

		// This is the basic test
		int offset = 0;
		Indicator indicator = new MonthIndicator("com.debugger.Month", "month");
	
		int lenghtOfValue = MONTHA.length();
		Collection<Annotation> annotations = indicator.identify(MONTHA+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(MONTHA_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (MONTHA.length())); 
		assertTrue(annotation.tags.contains("month"));

		lenghtOfValue = MONTHB.length();
		annotations = indicator.identify(MONTHB+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(MONTHB_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (MONTHB.length())); 
		assertTrue(annotation.tags.contains("month"));
		
		lenghtOfValue = MONTHC.length();
		annotations = indicator.identify(MONTHC+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(MONTHC_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (MONTHC.length())); 
		assertTrue(annotation.tags.contains("month"));
		
	}
	

	
	
	@Test
	void testEnd() {
		
		// (START_OF_SENTENCE+" ")
		
		
				int offset = 50;
				Indicator indicator = new MonthIndicator("com.debugger.Month", "month");
			
				int lenghtOfValue = MONTHA.length();
				Collection<Annotation> annotations = indicator.identify((START_OF_SENTENCE+" ")+MONTHA, offset);
				assertTrue(annotations.size()==1);
				Annotation annotation = annotations.iterator().next(); 
				assertTrue(MONTHA_VALUE.equals(annotation.value));
				assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
				assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+offset)+MONTHA.length()); 
				assertTrue(annotation.tags.contains("month"));

				lenghtOfValue = MONTHB.length();
				annotations = indicator.identify((START_OF_SENTENCE+" ")+MONTHB, offset);
				assertTrue(annotations.size()==1);
				annotation = annotations.iterator().next(); 
				assertTrue(MONTHB_VALUE.equals(annotation.value));
				assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
				assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+offset)+MONTHB.length()); 
				assertTrue(annotation.tags.contains("month"));
				
				lenghtOfValue = MONTHC.length();
				annotations = indicator.identify((START_OF_SENTENCE+" ")+MONTHC, offset);
				assertTrue(annotations.size()==1);
				annotation = annotations.iterator().next(); 
				assertTrue(MONTHC_VALUE.equals(annotation.value));
				assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
				assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+offset)+MONTHC.length()); 
				assertTrue(annotation.tags.contains("month"));

	}
	
	
}
