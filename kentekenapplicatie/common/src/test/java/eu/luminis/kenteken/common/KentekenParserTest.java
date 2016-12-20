package eu.luminis.kenteken.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class KentekenParserTest {

	private KentekenParser parser;
	
	@Before
	public void setUp() {
		parser = new KentekenParser();
	}
	
	@Test
	public void isValidKenteken() {
		assertTrue(parser.isValidKenteken("90-PTV-7"));
		assertTrue(parser.isValidKenteken("GF-258-N"));
		assertTrue(parser.isValidKenteken("XK-04-YK"));
	}
	
	@Test
	public void isValidKenteken_IllegalCharacters() {
		assertFalse(parser.isValidKenteken("90-QTV-7"));
		assertFalse(parser.isValidKenteken("GF-2!8-N"));
		assertFalse(parser.isValidKenteken("XK_04-YK"));
	}
	
	@Test
	public void isValidKenteken_WrongStructure() {
		assertFalse(parser.isValidKenteken("9-PTSD-7"));
		assertFalse(parser.isValidKenteken("GF--9871"));
		assertFalse(parser.isValidKenteken("XK-04-Y"));
		assertFalse(parser.isValidKenteken("XK-044-YK"));
	}
}
