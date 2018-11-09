package com.indicators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

class RMBIndicatorTest {

	public static final String NORMAL_SENTENCE = "This is a normal, ordinary sentence.";
	public static final String START_OF_SENTENCE = "The investing deal size was ";
	public static final String END_OF_SENTENCE = " as evaluated in end of 2017 third quarter.";
	
	// Billions
	public static final String RMBA = "RMB 90.31 billion";
	public static final String RMBA_VALUE = "90.31";
	public static final String RMBB = "RMB 2,211.61 million";
	public static final String RMBB_VALUE = "2211.61";
	public static final String RMBC = "RMB 1.02billion";
	public static final String RMBC_VALUE = "1.02";

	public static final String RMBD = "RMB 208.6million";
	public static final String RMBD_VALUE = "208.6";
	
	public static final String RMBE = "1 billion RMB";
	public static final String RMBE_VALUE = "1";
	
	public static final String RMBF = "RMB36.8 billion";
	public static final String RMBF_VALUE = "36.8";

	@Test
	void testStart() {

		// This is the basic test
		int offset = 0;
		Indicator indicator = new RMBIndicator(RMBA, "money");
	
		int lenghtOfValue = RMBA.length();
		Collection<Annotation> annotations = indicator.identify(RMBA+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(RMBA_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (RMBA.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBB.length();
		annotations = indicator.identify(RMBB+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBB_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (RMBB.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = RMBC.length();
		annotations = indicator.identify(RMBC+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBC_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (RMBC.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBD.length();
		annotations = indicator.identify(RMBD+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBD_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = RMBE.length();
		annotations = indicator.identify(RMBE+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBE_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBF.length();
		annotations = indicator.identify(RMBF+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBF_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
			
		
	}
	
	@Test
	void testEnd() {

		// This is the basic test
		int offset = 0;
		Indicator indicator = new RMBIndicator(RMBA, "money");
	
		int lenghtOfValue = RMBA.length();
		Collection<Annotation> annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBA+".", offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(RMBA_VALUE.equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+RMBA.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBB.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBB+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBB_VALUE.equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+RMBB.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = RMBC.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBC+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBC_VALUE.equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+RMBC.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBD.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBD+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBD_VALUE.equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = RMBE.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBE+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBE_VALUE.equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBF.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBF+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue(RMBF_VALUE.equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
	
	}
	
	@Test
	void testRMBA() {

		// This is the basic test
		int offset = 10;
		int lenghtOfValue = RMBA.length();
		Indicator indicator = new RMBIndicator(RMBA, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBA+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(RMBA_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}

	@Test
	void testRMBB() {

		// This is the basic test
		int offset = 10;
		int lenghtOfValue = RMBB.length();
		Indicator indicator = new RMBIndicator(RMBB, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBB+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(RMBB_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));


	}

	@Test
	void testRMBC() {

		//		public static final String RMBC = "200 billion U.S. dollars";

		int offset = 11;
		int lenghtOfValue = RMBC.length();
		Indicator indicator = new RMBIndicator(RMBC, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBC+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(RMBC_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}

	@Test
	void testRMBD() {

		// public static final String RMBD = "US$100 billion";
		int offset = 0;
		int lenghtOfValue = RMBD.length();
		Indicator indicator = new RMBIndicator(RMBD, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBD+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(RMBD_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));

	}

	@Test
	void testRMBE() {

		//		public static final String RMBE = "3 billion dollars";

		int offset = 10;
		int lenghtOfValue = RMBE.length();
		Indicator indicator = new RMBIndicator(RMBE, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBE+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(RMBE_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}
	
	@Test
	void testRMBF() {

		//public static final String RMBF = "$66.1 billion";

		int offset = 103;
		int lenghtOfValue = RMBF.length();
		Indicator indicator = new RMBIndicator(RMBF, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBF+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue(RMBF_VALUE.equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}




}
