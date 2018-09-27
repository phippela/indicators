package com.indicators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

class RMBIndicatorTest {

	public static final String NORMAL_SENTENCE = "This is a normal, ordinary sentence.";
	public static final String START_OF_SENTENCE = "The investing deal size was ";
	public static final String END_OF_SENTENCE = " as evaluated in end of 2017 third quarter.";
	
	// Billions
	public static final String RMBA = "88.14 billion yuan";
	public static final String RMBB = "13 billion yuan";
	public static final String RMBC = "RM55 billion";
	public static final String RMBD = "RM21.9 billion";
	public static final String RMBE = "RM141bil";
	public static final String RMBF = "RM141 billion";
	public static final String RMBG = "RMB 1.02billion";
	public static final String RMBH = "RM141b";
	
	// Trillions
	public static final String RMBI = "RMB7.4 trillion";
	
	// Millions
	public static final String RMBJ = "RM20.70 million";
	public static final String RMBK = "RMB 208.6million";
	
	
	// Anything under a million
	public static final String RMBL = "RM62,736";
	public static final String RMBM = "RMB 11";

	@Test
	void testStart() {

		// This is the basic test
		int offset = 0;
		Indicator indicator = new RMBIndicator(RMBA, "money");
	
		int lenghtOfValue = RMBA.length();
		Collection<Annotation> annotations = indicator.identify(RMBA+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("9504".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (RMBA.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBB.length();
		annotations = indicator.identify(RMBB+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("13.45".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (RMBB.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBC.length();
		annotations = indicator.identify(RMBC+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("200".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (RMBC.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBD.length();
		annotations = indicator.identify(RMBD+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("100".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBE.length();
		annotations = indicator.identify(RMBE+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("3".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBF.length();
		annotations = indicator.identify(RMBF+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("66.1".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
			
		lenghtOfValue = RMBG.length();
		annotations = indicator.identify(RMBG+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBH.length();
		annotations = indicator.identify(RMBH+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBI.length();
		annotations = indicator.identify(RMBI+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==2);
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		System.out.println("annotation.start="+annotation.start);
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+5+offset)));// || (annotation.start == 0+offset+3));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		// we want two
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue(annotation.start == (0+offset)|| (annotation.start == (0+5+offset)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBJ.length();
		annotations = indicator.identify(RMBJ+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==2);
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset+3))||(annotation.start == (0+offset) ));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		// we want two
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
	
		System.out.println("annotation.start="+annotation.value+" annotation.fullText="+annotation.fullString);
		assertTrue((annotation.start == (0+offset+3))||(annotation.start == (0+offset) ));
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
		assertTrue("9504".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+RMBA.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBB.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBB+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("13.45".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+RMBB.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBC.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBC+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("200".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+RMBC.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBD.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBD+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("100".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBE.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBE+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("3".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBF.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBF+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("66.1".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBG.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBG+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBH.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBH+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBI.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBI+".", offset);
		assertTrue(annotations.size()==2);
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue( (annotation.start == ((START_OF_SENTENCE+" ").length()+offset)) || 
				(annotation.start == ((START_OF_SENTENCE+" ").length()+offset)+5));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		// we want two
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue((annotation.start == ((START_OF_SENTENCE+" ").length()+offset))  || 
				(annotation.start == ((START_OF_SENTENCE+" ").length()+offset+5)) );
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = RMBJ.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+RMBJ+".", offset);
		assertTrue(annotations.size()==2);
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == ((START_OF_SENTENCE+" ").length()+offset)) ||
				(annotation.start == ((START_OF_SENTENCE+" ").length()+offset+3)));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		// we want two
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == ((START_OF_SENTENCE+" ").length()+offset)) ||
				(annotation.start == ((START_OF_SENTENCE+" ").length()+offset+3)));
		//assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
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
		assertTrue("9504".equals(annotation.value));
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
		assertTrue("13.45".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));


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
		assertTrue("200".equals(annotation.value));
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
		assertTrue("100".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

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
		assertTrue("3".equals(annotation.value));
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
		assertTrue("66.1".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}


	@Test
	void testRMBG() {

//		public static final String RMBG = "US 1.9 billion dollars";

		int offset = 10;
		int lenghtOfValue = RMBG.length();
		Indicator indicator = new RMBIndicator(RMBG, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBG+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}


	@Test
	void testRMBH() {
//		public static final String RMBH = "25.5 Billion US Dollars";

		int offset = 10;
		int lenghtOfValue = RMBH.length();
		Indicator indicator = new RMBIndicator(RMBH, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBH+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
	}


	@Test
	void testRMBI() {

		//public static final String RMBI = "$3.5-$4.5 billion";

		int offset = 10;
		int lenghtOfValue = RMBI.length();
		Indicator indicator = new RMBIndicator(RMBI, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBI+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==2);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue((annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset)) ||
				(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset+5)));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		// we want to have both values, but we are not too picky
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue((annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset)) ||
				(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset+5)));
		
	//	assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
	}

	@Test
	void testRMBJ() {

		//public static final String RMBJ = "$7-$12 billion";

		int offset = 10;
		int lenghtOfValue = RMBJ.length();
		Indicator indicator = new RMBIndicator(RMBI, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+RMBJ+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==2);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset)) || 
				(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset+3)));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		// we want to have both values, but we are not too picky
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset)) || 
				(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset+3)));
	//	assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
				
	}


//	@Test
//	void testMillion() {
//
//		// This is the basic test
//		int offset = 0;
//		Indicator indicator = new RMBIndicator(RMBA_million, "money");
//	
//		int lenghtOfValue = RMBA_million.length();
//		Collection<Annotation> annotations = indicator.identify(RMBA_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		Annotation annotation = annotations.iterator().next(); 
//		assertTrue("9504".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == (RMBA_million.length())); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//
//		lenghtOfValue = RMBB_million.length();
//		annotations = indicator.identify(RMBB_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("13.45".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == (RMBB_million.length())); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		
//		lenghtOfValue = RMBC_million.length();
//		annotations = indicator.identify(RMBC_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("200".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == (RMBC_million.length())); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		
//		lenghtOfValue = RMBD_million.length();
//		annotations = indicator.identify(RMBD_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("100".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		
//		lenghtOfValue = RMBE_million.length();
//		annotations = indicator.identify(RMBE_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("3".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		
//		lenghtOfValue = RMBF_million.length();
//		annotations = indicator.identify(RMBF_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("66.1".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		
//		lenghtOfValue = RMBG_million.length();
//		annotations = indicator.identify(RMBG_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("1.9".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		
//		lenghtOfValue = RMBH_million.length();
//		annotations = indicator.identify(RMBH_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("25.5".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		
//		lenghtOfValue = RMBI_million.length();
//		annotations = indicator.identify(RMBI_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==2);
//		annotation = annotations.iterator().next(); 
//		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
//		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+5)));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		// we want two
//		annotation = annotations.iterator().next(); 
//		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
//		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+5)));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		
//		lenghtOfValue = RMBJ_million.length();
//		annotations = indicator.identify(RMBJ_million+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==2);
//		annotation = annotations.iterator().next(); 
//		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
//		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+3)));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//		// we want two
//		annotation = annotations.iterator().next(); 
//		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
//		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+3)));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("million"));
//	}
//	
//
//	@Test
//	void testTrillion() {
//
//		// This is the basic test
//		int offset = 0;
//		Indicator indicator = new RMBIndicator(RMBA_trillion, "money");
//	
//		int lenghtOfValue = RMBA_trillion.length();
//		Collection<Annotation> annotations = indicator.identify(RMBA_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		Annotation annotation = annotations.iterator().next(); 
//		assertTrue("9504".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == (RMBA_trillion.length())); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//
//		lenghtOfValue = RMBB_trillion.length();
//		annotations = indicator.identify(RMBB_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("13.45".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == (RMBB_trillion.length())); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		
//		lenghtOfValue = RMBC_trillion.length();
//		annotations = indicator.identify(RMBC_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("200".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == (RMBC_trillion.length())); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		
//		lenghtOfValue = RMBD_trillion.length();
//		annotations = indicator.identify(RMBD_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("100".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		
//		lenghtOfValue = RMBE_trillion.length();
//		annotations = indicator.identify(RMBE_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("3".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		
//		lenghtOfValue = RMBF_trillion.length();
//		annotations = indicator.identify(RMBF_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("66.1".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		
//		lenghtOfValue = RMBG_trillion.length();
//		annotations = indicator.identify(RMBG_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("1.9".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		
//		lenghtOfValue = RMBH_trillion.length();
//		annotations = indicator.identify(RMBH_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==1);
//		annotation = annotations.iterator().next(); 
//		assertTrue("25.5".equals(annotation.value));
//		assertTrue(annotation.start == (0+offset));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		
//		lenghtOfValue = RMBI_trillion.length();
//		annotations = indicator.identify(RMBI_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==2);
//		annotation = annotations.iterator().next(); 
//		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
//		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+5)));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		// we want two
//		annotation = annotations.iterator().next(); 
//		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
//		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+5)));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		
//		lenghtOfValue = RMBJ_trillion.length();
//		annotations = indicator.identify(RMBJ_trillion+END_OF_SENTENCE, offset);
//		assertTrue(annotations.size()==2);
//		annotation = annotations.iterator().next(); 
//		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
//		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+3)));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//		// we want two
//		annotation = annotations.iterator().next(); 
//		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
//		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+3)));
//		assertTrue(annotation.stop == lenghtOfValue); 
//		assertTrue(annotation.tags.contains("money"));
//		assertTrue(annotation.tags.contains("trillion"));
//	}

}
