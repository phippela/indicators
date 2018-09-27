package com.indicators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

class YuanIndicatorTest {

	public static final String NORMAL_SENTENCE = "This is a normal, ordinary sentence.";
	public static final String START_OF_SENTENCE = "The investing deal size was ";
	public static final String END_OF_SENTENCE = " as evaluated in end of 2017 third quarter.";

	public static final String RMBA = "88.14 billion yuan";
	public static final String RMBB = "13 billion yuan";
	public static final String RMBC = "RM55 billion";
	public static final String RMBD = "RM21.9 billion";
	public static final String RMBE = "RM141bil";
	public static final String RMBF = "RM141 billion";
	public static final String RMBG = "RMB 1.02billion";
	public static final String RMBH = "RM141b";

	public static final String RMBI = "RMB7.4 trillion";

	public static final String RMBJ = "RM20.70 million";
	public static final String RMBK = "RMB 208.6million";

	public static final String RMBL = "RM62,736";
	public static final String RMBM = "RMB 11";

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Test
	void testStart() {

		// This is the basic test
		int offset = 0;
		Indicator indicator = new RMBIndicator(RMBA, "money");

		int lenghtOfValue = RMBA.length();
		Collection<Annotation> annotations = indicator.identify(RMBA + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 1);
		Annotation annotation = annotations.iterator().next();
		assertTrue("88.14".equals(annotation.value));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == (RMBA.length()));
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBB.length();
		annotations = indicator.identify(RMBB + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 1);
		annotation = annotations.iterator().next();
		assertTrue("13".equals(annotation.value));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == (RMBB.length()));
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBC.length();
		annotations = indicator.identify(RMBC + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 1);
		annotation = annotations.iterator().next();
		assertTrue("55".equals(annotation.value));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == (RMBC.length()));
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBD.length();
		annotations = indicator.identify(RMBD + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 1);
		annotation = annotations.iterator().next();
		assertTrue("21.9".equals(annotation.value));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBE.length();
		annotations = indicator.identify(RMBE + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 1);
		annotation = annotations.iterator().next();
		assertTrue("141".equals(annotation.value));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBF.length();
		annotations = indicator.identify(RMBF + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 1);
		annotation = annotations.iterator().next();
		assertTrue("141".equals(annotation.value));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBG.length();
		annotations = indicator.identify(RMBG + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 1);
		annotation = annotations.iterator().next();
		assertTrue("1.9".equals(annotation.value));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBH.length();
		annotations = indicator.identify(RMBH + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 1);
		annotation = annotations.iterator().next();
		assertTrue("25.5".equals(annotation.value));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBI.length();
		annotations = indicator.identify(RMBI + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 2);
		annotation = annotations.iterator().next();
		assertTrue("3.5".equals(annotation.value) || ("4.5".equals(annotation.value)));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		// we want two
		annotation = annotations.iterator().next();
		assertTrue("3.5".equals(annotation.value) || ("4.5".equals(annotation.value)));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));

		lenghtOfValue = RMBJ.length();
		annotations = indicator.identify(RMBJ + END_OF_SENTENCE, offset);
		assertTrue(annotations.size() == 2);
		annotation = annotations.iterator().next();
		assertTrue("7".equals(annotation.value) || ("12".equals(annotation.value)));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
		// we want two
		annotation = annotations.iterator().next();
		assertTrue("7".equals(annotation.value) || ("12".equals(annotation.value)));
		assertTrue(annotation.start == (0 + offset));
		assertTrue(annotation.stop == lenghtOfValue);
		assertTrue(annotation.tags.contains("money"));
		assertTrue(annotation.tags.contains("billion"));
	}

	/*
	 * 
	 * 
	 * 
	 * 9,504 billion dollars 13.45 billion U.S. dollars 200 billion U.S. dollars
	 * US$100 billion 3 billion dollars $66.1 billion US 1.9 billion dollars 25.5
	 * Billion US Dollars $3.5–$4.5 billion $7-$12 billion
	 */
}
