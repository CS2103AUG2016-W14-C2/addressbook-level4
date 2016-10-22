package seedu.menion.logic.commands;

import java.text.DateFormatSymbols;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.menion.commons.exceptions.IllegalValueException;

/**
 * Lists all tasks in the task manager to the user.
 */
// @@author A0139277U
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String LIST_ALL = "all";
    public static final String LIST_MONTH = "month";
    public static final String LIST_DATE = "date";
    private static final Pattern VALID_DATE = Pattern
			.compile("(0?[0-1][0-9]-[0-3][0-9]-[0-2][0-9][0-9][0-9])");
    
    public static final String WRONG_ARGUMENT = "Wrong argument! Please input either list all, list DATE or list MONTH";
    public static final String MESSAGE_SUCCESS_ALL = "Listed all activities";
    public static final String MESSAGE_SUCCESS_DATE_MONTH = "Menion lists all activities that falls on ";

    private String listArgument;
    private String listType;
    private String monthToList;
    private String dateToList;
    private Set<String> argumentsToList;
    
    private static Matcher matcher;
    
    
    public ListCommand(String args){
    	argumentsToList = new HashSet<String>();
    	this.listArgument = args;
    	System.out.println("args = " + args);
    }

    /**
     * This method checks the argument input by the user.
     * @param args
     * @return
     * @throws IllegalValueException
     */
    public String checkListType(String args) throws IllegalValueException{
    	
    	if (args.equals("") || args.toLowerCase().equals(LIST_ALL)){
    		return LIST_ALL;
    	}
    	
    	else if (isMonth(args)){
    		
    		return LIST_MONTH;
    	}
    	
    	else if (isDate(args)){
    		return LIST_DATE;
    	}
    		
    	else {
    		throw new IllegalValueException(WRONG_ARGUMENT);
    	}
    			
    	
    }
    
    /**
     * This method checks if the arguments is in the format of a Date.
     * @param args
     * @return true if the arguments fit the format of a Date.
     */
    private Boolean isDate (String args){
    	matcher = VALID_DATE.matcher(args);
    	if (matcher.find()){
    		this.dateToList = args;
    		this.argumentsToList.add(args);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * This method checks if the arguments is a month
     * @param args
     * @return true if the arguments fit the format of a Month.
     */
    private Boolean isMonth (String args){
    	
    	String monthString;
    	
    	for (Integer i = 0 ; i < 12; i ++){
    		monthString = new DateFormatSymbols().getMonths()[i];
    		if (args.toLowerCase().equals(monthString.toLowerCase())){
    			this.argumentsToList.add(monthToList);
    			this.monthToList = monthString;
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    @Override
    public CommandResult execute() {
    	
    	try{
    		
    		this.listType = checkListType(this.listArgument);
    		
    		switch (this.listType){
        	
        	case LIST_ALL:

                model.updateFilteredListToShowAll();
                return new CommandResult(MESSAGE_SUCCESS_ALL);
        		
        	case LIST_DATE:
        		
        		model.updateFilteredTaskList(this.argumentsToList);
        		model.updateFilteredEventList(this.argumentsToList);
        		model.updateFilteredFloatingTaskList(this.argumentsToList);
        		return new CommandResult(MESSAGE_SUCCESS_DATE_MONTH + this.dateToList);
        	
        	case LIST_MONTH:
        		
        		model.updateFilteredTaskList(this.argumentsToList);
        		model.updateFilteredEventList(this.argumentsToList);
        		model.updateFilteredFloatingTaskList(this.argumentsToList);
        		return new CommandResult(MESSAGE_SUCCESS_DATE_MONTH + this.monthToList);
        		
        	default:
        		return new CommandResult(WRONG_ARGUMENT);
        		
        	}
    		
    	} catch (IllegalValueException e){
    		
    		return new CommandResult(WRONG_ARGUMENT);
    		
    	}
    	
    }
}
