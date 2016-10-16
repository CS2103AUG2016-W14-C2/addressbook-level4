package seedu.address.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import seedu.menion.logic.parser.AddParser;

public class AddParserTest {

	
	@Test
	public void checkIsTask_returnsTrue() {

		String arguments = "complete cs2103t by : 10-08-2016 1900 n : important";

		assertTrue(AddParser.isTask(arguments));

	}

	@Test
	public void checkIsTask_returnsFalse(){
		
		String arguments = "complete 2103t by: 10-08-16 9999 n:important";
		
		assertFalse(AddParser.isTask(arguments));
	}
	
	@Test
	public void checkIsEvent_returnsTrue(){
		
		String arguments = "meet prof damith from: 10-08-2016 1900 to: 11-08-2016 1900 n:he is fierce";
		
		assertTrue(AddParser.isEvents(arguments));
	}
	
	@Test
	public void checkIsEvent_returnsFalse(){
		
		String arguments = "meet prof damith from: 10-08-2016 1900 by: 11-08-2016 1900 n:he is fierce";
	
		assertFalse(AddParser.isEvents(arguments));
	
	}
	
	@Test
	public void checkTypeOfActivity_returnsTask(){
		
		String arguments = "complete cs2103t by : 10-08-2016 1900 n : important";

		
		assertEquals("task", AddParser.parseCommand(arguments).get(0));
	}
	
	@Test
	public void checkTypeOfActivity_returnsEvents(){
		
		String arguments = "meet prof damith from: 10-08-2016 1900 to: 11-08-2016 1900 n:he is fierce";
		
		assertEquals("event", AddParser.parseCommand(arguments).get(0));
	}
	
	@Test
	public void checkTypeOfActivity_returnsFloatingTask(){
		
		String arguments = "complete cs2103t n:important";
		
		assertEquals("floatingTask", AddParser.parseCommand(arguments).get(0));
	}
	
	@Test
	public void checkEventArguments_returnsCorrectArguments(){
		
		String arguments = "meet prof damith from: 10-08-2016 1900 to: 11-08-2016 1900 n:he is fierce";
		
		assertEquals("meet prof damith", AddParser.parseCommand(arguments).get(1));
		assertEquals("he is fierce", AddParser.parseCommand(arguments).get(2));
		assertEquals("8 October 2016", AddParser.parseCommand(arguments).get(3));
		assertEquals("1900", AddParser.parseCommand(arguments).get(4));
		assertEquals("8 November 2016", AddParser.parseCommand(arguments).get(5));
		assertEquals("1900", AddParser.parseCommand(arguments).get(6));
	}
	
	@Test
	public void checkTaskArguments_returnsCorrectArguments(){
		
		String arguments = "complete cs2103t by : 10-08-2016 1900 n:important";
		
		assertEquals("complete cs2103t", AddParser.parseCommand(arguments).get(1));
		assertEquals("important", AddParser.parseCommand(arguments).get(2));
		assertEquals("8 October 2016", AddParser.parseCommand(arguments).get(3));
		assertEquals("1900", AddParser.parseCommand(arguments).get(4));
		
	}
	
	@Test
	public void checkFloatingTaskArguments_returnsCorrectArguments(){
		
		String arguments = "complete cs2103t n:important";		
	
		assertEquals("complete cs2103t", AddParser.parseCommand(arguments).get(1));
		assertEquals("important", AddParser.parseCommand(arguments).get(2));
		
	}

	@Test
	public void checkParserReturnValues(){
		
		assertEquals("7 August 2016", AddParser.dateHandler("7th august 2016"));
			
	}
	
	@Test
	public void checkDateHandleReturnValues(){
		
		assertEquals("1 December 2016", AddParser.dateHandler("December 1st 16"));
	}
	
}
