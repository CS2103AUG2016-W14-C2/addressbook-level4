package seedu.menion.logic.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.menion.logic.commands.AddCommand;
import seedu.menion.logic.commands.IncorrectCommand;

import static seedu.menion.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.menion.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

public class AddParser {

	public AddParser() {
	};

	private static final Pattern REGULAR_TASK_REGEX = Pattern
			.compile("(.+)by: (0?[0-3][0-9]-[0-1][0-9]-[0-2][0-9][0-9][0-9]) (0?[0-2][0-9][0-6][0-9]) n:(.+)");
	private static final Pattern EVENTS_REGEX = Pattern
			.compile("(.+)from: (0?[0-3][0-9]-[0-1][0-9]-[0-2][0-9][0-9][0-9]) (0?[0-2][0-9][0-6][0-9]) "
					+ "to: (0?[0-3][0-9]-[0-1][0-9]-[0-2][0-9][0-9][0-9]) (0?[0-2][0-9][0-6][0-9]) n:(.+)");
	private static final Pattern FLOATING_TASK_REGEX = Pattern
			.compile("(.+)n:(.+)");
	
	private static final String REGULAR_TASK = "task";
	private static final String EVENTS = "event";
	private static final String FLOATING_TASK = "floatingTask";

	private static final String WRONG_EVENT = "Wrong format for Event used.";
	
	
	private static Matcher matcher;
	private static ArrayList<String> parsedArguments;

	/**
	 * This method parses the input command and will check the type of add
	 * command.
	 * 
	 * @param args
	 * @return An array of parsed commands
	 */
	public static ArrayList<String> parseCommand(String args) {

		parsedArguments = new ArrayList<String>();
		checkActivityType(args);

		return parsedArguments;
	}

	/**
	 * This method checks the type of activity based on its arguments and sets
	 * the arguments into an array list.
	 * 
	 * @param args
	 */
	public static void checkActivityType(String args) {

		if (isEvents(args)) {
			parsedArguments.add(EVENTS);
			inputEventArguments();
		}

		else if (isTask(args)) {
			parsedArguments.add(REGULAR_TASK);
			inputTaskArguments();
		}

		else if (isFloatingTask(args)){
			parsedArguments.add(FLOATING_TASK);
			inputFloatingTaskArguments();
		}
		

	}

	/**
	 * Input the arguments into the parsedArguments ArrayList.
	 * list(1) = Floating Task Name
	 * list(2) = Floating Task Notes
	 */
	private static void inputFloatingTaskArguments(){

		parsedArguments.add(1, matcher.group(1));
		parsedArguments.add(2, matcher.group(2));
		
	}
	
	/**
	 * Input the arguments into the parsedArguments ArrayList. 
	 * list(1) = Task Name
	 * list(2) = Task Notes
	 * list(3) = Task Start Date
	 * list(4) = Task Start Time
	 */
	private static void inputTaskArguments() {

		parsedArguments.add(1, matcher.group(1));
		parsedArguments.add(2, matcher.group(4));
		parsedArguments.add(3, matcher.group(2));
		parsedArguments.add(4, matcher.group(3));		
		
	}

	/**
	 * Input the arguments into the parsedArguments ArrayList.
	 * list(1) = Event Name
	 * list(2) = Event Notes
	 * list(3) = Event Start Date
	 * list(4) = Event Start Time
	 * list(5) = Event End Date
	 * list(6) = Event End Time
	 */
	private static void inputEventArguments() {

		parsedArguments.add(1, matcher.group(1));
		parsedArguments.add(2, matcher.group(6));
		parsedArguments.add(3, matcher.group(2));
		parsedArguments.add(4, matcher.group(3));
		parsedArguments.add(5, matcher.group(4));
		parsedArguments.add(6, matcher.group(5));

	}

	
	public static Boolean isFloatingTask(String args){
		matcher = FLOATING_TASK_REGEX.matcher(args);
		
		if (matcher.find()){
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * This method checks if the input arguments satisfy the requirements to be
	 * a Task.
	 * 
	 * @return
	 */
	public static Boolean isTask(String args) {
		matcher = REGULAR_TASK_REGEX.matcher(args);

		if (matcher.find()) {
			return true;
		}

		return false;

	}

	/**
	 * This method checks if the input arguments satisfy the requirements to be
	 * a Event.
	 * 
	 * @return
	 */
	public static Boolean isEvents(String args) {
		matcher = EVENTS_REGEX.matcher(args);

		if (matcher.find()) {
			return true;
		}

		return false;

	}

}
