package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShiftTimeZone() {
		DateTime TimeTest = new DateTime(2018, 10, 17, 14, 53, 0);
		DateTime DateIKnow = new DateTime("2018-10-17 23:53:0");
		
		TimeTest = TimeZoneTranslator.shiftTimeZone(TimeTest, 0, 9);
		assertEquals(TimeTest.toString(),DateIKnow.toString());
		
		//EDGE CASES
		DateTime dateTest = new DateTime(2016, 1, 1, 6, 0, 0);
		String dateTestIKnow = "2015-12-31 21:00:00";
		
		dateTest = TimeZoneTranslator.shiftTimeZone(dateTest, 0, -9);
		
		assertEquals(dateTest.toString(),dateTestIKnow);
	}

	@Test
	public void testShiftEventTimeZone() {
		DateTime eventTestStart = new DateTime(2018,10,17,8,0,0);
		DateTime eventTestEnd = new DateTime(2018,10,17,10,0,0);
		Person Jim = new Person("Jim");
		Person Edvin = new Person("Edvin");
		Place JTH = new Place("JTH",57.7794662,14.1601664,100.0);
		
		Event firstTest = new Event("Test number one",
				eventTestStart,
				eventTestEnd,
				new HashSet<>(Arrays.asList(Jim, Edvin)),
				JTH);
		
		DateTime eventTestIKnowStart = new DateTime(2018,10,17,14,0,0);
		DateTime eventTestIKnowEnd = new DateTime(2018,10,17,16,0,0);
		
		Event firstTestIKnow = new Event("Test number one",
				eventTestIKnowStart,
				eventTestIKnowEnd,
				new HashSet<>(Arrays.asList(Jim,Edvin)),
				JTH);
		
		firstTest = TimeZoneTranslator.shiftEventTimeZone(firstTest, TimeZone.GREENWICH_UTC, TimeZone.BANGLADESH);
	
		assertEquals(firstTest.toString(),firstTestIKnow.toString());
	}
}
