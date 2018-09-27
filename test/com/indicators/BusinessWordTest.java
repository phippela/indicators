package com.indicators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class BusinessWordTest {

	public static final String NORMAL_SENTENCE = "This is a normal, ordinary sentence.";
	public static final String START_OF_SENTENCE = "The investing deal size was ";
	public static final String END_OF_SENTENCE = " as evaluated in end of 2017 third quarter.";

	public List<String> businessWords = new ArrayList<String>();
	


	@Test
	void testStart() {

		
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

		assertTrue(false);
	}
	/*
		// This is the basic test
		int offset = 0;
		Indicator indicator = new USDIndicator(USDA, "money");
	
		int lenghtOfValue = USDA.length();
		Collection<Annotation> annotations = indicator.identify(USDA+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("9504".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDA.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = USDB.length();
		annotations = indicator.identify(USDB+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("13.45".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDB.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDC.length();
		annotations = indicator.identify(USDC+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("200".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDC.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDD.length();
		annotations = indicator.identify(USDD+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("100".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDE.length();
		annotations = indicator.identify(USDE+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("3".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDF.length();
		annotations = indicator.identify(USDF+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("66.1".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
			
		lenghtOfValue = USDG.length();
		annotations = indicator.identify(USDG+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDH.length();
		annotations = indicator.identify(USDH+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDI.length();
		annotations = indicator.identify(USDI+END_OF_SENTENCE, offset);
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
		
		lenghtOfValue = USDJ.length();
		annotations = indicator.identify(USDJ+END_OF_SENTENCE, offset);
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
		Indicator indicator = new USDIndicator(USDA, "money");
	
		int lenghtOfValue = USDA.length();
		Collection<Annotation> annotations = indicator.identify((START_OF_SENTENCE+" ")+USDA+".", offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("9504".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+USDA.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = USDB.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDB+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("13.45".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+USDB.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDC.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDC+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("200".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == ((START_OF_SENTENCE+" ").length()+USDC.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDD.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDD+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("100".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDE.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDE+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("3".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDF.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDF+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("66.1".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDG.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDG+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDH.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDH+".", offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == ((START_OF_SENTENCE+" ").length()+offset));
		assertTrue(annotation.stop == (START_OF_SENTENCE+" ").length()+lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		
		lenghtOfValue = USDI.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDI+".", offset);
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
		
		lenghtOfValue = USDJ.length();
		annotations = indicator.identify((START_OF_SENTENCE+" ")+USDJ+".", offset);
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
	void testUSDA() {

		// This is the basic test
		int offset = 10;
		int lenghtOfValue = USDA.length();
		Indicator indicator = new USDIndicator(USDA, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDA+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("9504".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}

	@Test
	void testUSDB() {

		// This is the basic test
		int offset = 10;
		int lenghtOfValue = USDB.length();
		Indicator indicator = new USDIndicator(USDB, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDB+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("13.45".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));


	}

	@Test
	void testUSDC() {

		//		public static final String USDC = "200 billion U.S. dollars";

		int offset = 11;
		int lenghtOfValue = USDC.length();
		Indicator indicator = new USDIndicator(USDC, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDC+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("200".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}

	@Test
	void testUSDD() {

		// public static final String USDD = "US$100 billion";
		int offset = 0;
		int lenghtOfValue = USDD.length();
		Indicator indicator = new USDIndicator(USDD, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDD+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("100".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}

	@Test
	void testUSDE() {

		//		public static final String USDE = "3 billion dollars";

		int offset = 10;
		int lenghtOfValue = USDE.length();
		Indicator indicator = new USDIndicator(USDE, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDE+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("3".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}
	
	@Test
	void testUSDF() {

		//public static final String USDF = "$66.1 billion";

		int offset = 103;
		int lenghtOfValue = USDF.length();
		Indicator indicator = new USDIndicator(USDF, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDF+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("66.1".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}


	@Test
	void testUSDG() {

//		public static final String USDG = "US 1.9 billion dollars";

		int offset = 10;
		int lenghtOfValue = USDG.length();
		Indicator indicator = new USDIndicator(USDG, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDG+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

	}


	@Test
	void testUSDH() {
//		public static final String USDH = "25.5 Billion US Dollars";

		int offset = 10;
		int lenghtOfValue = USDH.length();
		Indicator indicator = new USDIndicator(USDH, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDH+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+offset));
		assertTrue(annotation.stop == (NORMAL_SENTENCE.length()+1+START_OF_SENTENCE.length()+lenghtOfValue+offset)); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
	}


	@Test
	void testUSDI() {

		//public static final String USDI = "$3.5-$4.5 billion";

		int offset = 10;
		int lenghtOfValue = USDI.length();
		Indicator indicator = new USDIndicator(USDI, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDI+END_OF_SENTENCE, offset);
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
	void testUSDJ() {

		//public static final String USDJ = "$7-$12 billion";

		int offset = 10;
		int lenghtOfValue = USDJ.length();
		Indicator indicator = new USDIndicator(USDI, "money");
		Collection<Annotation> annotations = indicator.identify(NORMAL_SENTENCE+" "+START_OF_SENTENCE+USDJ+END_OF_SENTENCE, offset);
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


	@Test
	void testMillion() {

		// This is the basic test
		int offset = 0;
		Indicator indicator = new USDIndicator(USDA_million, "money");
	
		int lenghtOfValue = USDA_million.length();
		Collection<Annotation> annotations = indicator.identify(USDA_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("9504".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDA_million.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));

		lenghtOfValue = USDB_million.length();
		annotations = indicator.identify(USDB_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("13.45".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDB_million.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = USDC_million.length();
		annotations = indicator.identify(USDC_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("200".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDC_million.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = USDD_million.length();
		annotations = indicator.identify(USDD_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("100".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = USDE_million.length();
		annotations = indicator.identify(USDE_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("3".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = USDF_million.length();
		annotations = indicator.identify(USDF_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("66.1".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = USDG_million.length();
		annotations = indicator.identify(USDG_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = USDH_million.length();
		annotations = indicator.identify(USDH_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = USDI_million.length();
		annotations = indicator.identify(USDI_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==2);
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+5)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		// we want two
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+5)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		
		lenghtOfValue = USDJ_million.length();
		annotations = indicator.identify(USDJ_million+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==2);
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+3)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
		// we want two
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+3)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("million"));
	}
	

	@Test
	void testTrillion() {

		// This is the basic test
		int offset = 0;
		Indicator indicator = new USDIndicator(USDA_trillion, "money");
	
		int lenghtOfValue = USDA_trillion.length();
		Collection<Annotation> annotations = indicator.identify(USDA_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		Annotation annotation = annotations.iterator().next(); 
		assertTrue("9504".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDA_trillion.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));

		lenghtOfValue = USDB_trillion.length();
		annotations = indicator.identify(USDB_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("13.45".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDB_trillion.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		
		lenghtOfValue = USDC_trillion.length();
		annotations = indicator.identify(USDC_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("200".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == (USDC_trillion.length())); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		
		lenghtOfValue = USDD_trillion.length();
		annotations = indicator.identify(USDD_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("100".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		
		lenghtOfValue = USDE_trillion.length();
		annotations = indicator.identify(USDE_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("3".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		
		lenghtOfValue = USDF_trillion.length();
		annotations = indicator.identify(USDF_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("66.1".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		
		lenghtOfValue = USDG_trillion.length();
		annotations = indicator.identify(USDG_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		
		lenghtOfValue = USDH_trillion.length();
		annotations = indicator.identify(USDH_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==1);
		annotation = annotations.iterator().next(); 
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == (0+offset));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		
		lenghtOfValue = USDI_trillion.length();
		annotations = indicator.identify(USDI_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==2);
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+5)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		// we want two
		annotation = annotations.iterator().next(); 
		assertTrue("3.5".equals(annotation.value)||("4.5".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+5)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		
		lenghtOfValue = USDJ_trillion.length();
		annotations = indicator.identify(USDJ_trillion+END_OF_SENTENCE, offset);
		assertTrue(annotations.size()==2);
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+3)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
		// we want two
		annotation = annotations.iterator().next(); 
		assertTrue("7".equals(annotation.value)||("12".equals(annotation.value)));
		assertTrue((annotation.start == (0+offset)) || (annotation.start == (0+offset+3)));
		assertTrue(annotation.stop == lenghtOfValue); 
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("trillion"));
	}
	

	/*

88.14 billion yuan
13 billion yuan
RM55 billion
RM21.9 billion
RM141bil
RM141 billion
RMB 1.02billion
RM141b
RMB7.4 trillion
RM20.70 million
RMB 208.6million
RM62,736
RMB 11

9,504 billion dollars
13.45 billion U.S. dollars
200 billion U.S. dollars
US$100 billion
3 billion dollars
$66.1 billion
US 1.9 billion dollars
25.5 Billion US Dollars
$3.5–$4.5 billion
$7-$12 billion
	 */
}
