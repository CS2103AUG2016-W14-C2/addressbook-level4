package seedu.menion.logic.commands;

import java.util.ArrayList;

import seedu.menion.commons.core.Messages;
import seedu.menion.commons.core.UnmodifiableObservableList;
import seedu.menion.model.ActivityManager;
import seedu.menion.model.ReadOnlyActivityManager;
import seedu.menion.model.activity.Activity;
import seedu.menion.model.activity.ReadOnlyActivity;
import seedu.menion.model.activity.UniqueActivityList;
import seedu.menion.model.activity.UniqueActivityList.DuplicateTaskException;
import seedu.menion.model.activity.UniqueActivityList.ActivityNotFoundException;

//@@author A0146752B
/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the activity identified by the activity type followed by the index number used in the last activity listing.\n"
            + "Parameters: [Activity_Type] + [Activity_Index]\n"
            + "Example: " + COMMAND_WORD + " " + Activity.FLOATING_TASK_TYPE + " 1\n"
            + "Example: " + COMMAND_WORD + " " + Activity.TASK_TYPE + " 2\n"
            + "Example: " + COMMAND_WORD + " " + Activity.EVENT_TYPE + " 3";

    public static final String MESSAGE_DELETE_ACTIVITY_SUCCESS = "Deleted Activity: %1$s";

    public final String[] targetIndex;
    public final String targetType;
    
    private Activity toBeDeleted;
    
    public DeleteCommand(String targetType, String[] targetIndexArray) {
        this.targetIndex = targetIndexArray;
        this.targetType = targetType.trim();
    }


    @Override
    public CommandResult execute() {
    	assert model != null;
    	
    	storePreviousState();
    	model.updateRecentChangedActivity(null);
    	
        UnmodifiableObservableList<ReadOnlyActivity> lastShownList;
        StringBuilder feedbackString = new StringBuilder();  
        ArrayList<Activity> activitiesToBeDeleted = new ArrayList();
        
        if (targetType.equals(Activity.TASK_TYPE)) {
            lastShownList = model.getFilteredTaskList();
        }
        else if (targetType.equals(Activity.FLOATING_TASK_TYPE)) {
            lastShownList = model.getFilteredFloatingTaskList();
        }
        else if (targetType.equals(Activity.EVENT_TYPE)) {
            lastShownList = model.getFilteredEventList();
        }
        else {
            lastShownList = null;
            indicateAttemptToExecuteIncorrectCommand();
        }
        
        for (int Index = 1; Index < targetIndex.length; Index++){
            try {
                System.out.println("value = " + targetIndex[Index]);
                Integer newTargetIndex = Integer.valueOf(targetIndex[Index]);
                
                if (lastShownList.size() < newTargetIndex) {
                    indicateAttemptToExecuteIncorrectCommand();
                    return new CommandResult(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
                }

                ReadOnlyActivity activityToDelete = lastShownList.get(newTargetIndex - 1);
                toBeDeleted = (Activity)activityToDelete;
                activitiesToBeDeleted.add(toBeDeleted);
            }catch (NumberFormatException error) {
                return new CommandResult(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
            }
            
        }
        
        for (int i = 0; i < activitiesToBeDeleted.size(); i++) {
            ReadOnlyActivity activityToDelete = activitiesToBeDeleted.get(i);
            try {
                if (targetType.equals(Activity.TASK_TYPE)){
                    model.deleteTask(activityToDelete);
                }
                else if (targetType.equals(Activity.EVENT_TYPE)){
                    model.deleteEvent(activityToDelete);
                }
                else {
                    model.deleteFloatingTask(activityToDelete);
                }
            } catch (ActivityNotFoundException pnfe) {
                assert false : "The target activity cannot be missing";
            }
            
            feedbackString.append(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete));
            feedbackString.append("\n");
            //return new CommandResult(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete));
        } 
        return new CommandResult(feedbackString.toString());

        /*
        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        ReadOnlyActivity activityToDelete = lastShownList.get(targetIndex - 1);
        toBeDeleted = (Activity)activityToDelete;
    	
        try {
            if (targetType.equals(Activity.TASK_TYPE)){
                model.deleteTask(activityToDelete);
            }
            else if (targetType.equals(Activity.EVENT_TYPE)){
                model.deleteEvent(activityToDelete);
            }
            else {
                model.deleteFloatingTask(activityToDelete);
            }
        } catch (ActivityNotFoundException pnfe) {
            assert false : "The target activity cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete));
        */
    }

    //@@author A0139515A
    /**
     * Delete command will store previous activity manager to support undo command
     *
     */
    public void storePreviousState() {
        assert model != null;

        ReadOnlyActivityManager beforeState = new ActivityManager(model.getActivityManager());
    	model.addStateToUndoStack(beforeState);
    }
    //@@author
}
