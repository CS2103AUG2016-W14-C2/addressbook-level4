package guitests;

import guitests.guihandles.ActivityCardHandle;
import org.junit.Test;

import seedu.address.testutil.TestActivity;
import seedu.address.testutil.TestUtil;
import seedu.menion.commons.core.Messages;
import seedu.menion.logic.commands.AddCommand;

import static org.junit.Assert.assertTrue;

public class AddCommandTest extends AddressBookGuiTest {

    @Test
    public void add() {
        //add one activity
        TestActivity[] currentList = td.getTypicalActivity();
        TestActivity activityToAdd = td.task;
        
        assertAddSuccess(activityToAdd, currentList);
        currentList = TestUtil.addPersonsToList(currentList, activityToAdd);

        /*
        //add another activity
        activityToAdd = td.floatingTask;
        assertAddSuccess(activityToAdd, currentList);
        currentList = TestUtil.addPersonsToList(currentList, activityToAdd);
        
        /*
        //add duplicate activity
        commandBox.runCommand(td.hoon.getAddCommand());
        assertResultMessage(AddCommand.MESSAGE_DUPLICATE_TASK);
        assertTrue(personListPanel.isListMatching(currentList));
         */
        
        //add to empty list
        commandBox.runCommand("clear");
        assertAddSuccess(td.task);

        //invalid command
        commandBox.runCommand("adds Johnny");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertAddSuccess(TestActivity activityToAdd, TestActivity... currentList) {
        
        commandBox.runCommand(activityToAdd.getAddCommand());

        //confirm the new card contains the right data
        ActivityCardHandle addedCard = activityListPanel.navigateToPerson(activityToAdd.getActivityName().toString());
        assertMatching(activityToAdd, addedCard);

        //confirm the list now contains all previous activities plus the new activity
        TestActivity[] expectedList = TestUtil.addPersonsToList(currentList, activityToAdd);
        assertTrue(activityListPanel.isListMatching(expectedList));
    }

}