package seedu.menion.model.activity;

import seedu.menion.commons.exceptions.IllegalValueException;
import seedu.menion.commons.util.CollectionUtil;
import seedu.menion.model.tag.UniqueTagList;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Person in the address book. Guarantees: details are present and
 * not null, field values are validated.
 */
public class Activity implements ReadOnlyActivity {

    // Types of Activity
    public static final String FLOATING_TASK_TYPE = "floatingTask";
    public static final String TASK_TYPE = "task";
    public static final String EVENT_TYPE = "event";

    public static final Integer FLOATING_TASK_LENGTH = 3; // ActivityType,
                                                          // ActivityName, Note
    public static final Integer TASK_LENGTH = 5; // ActivityType, Note,
                                                 // ActivityName StartDate,
                                                 // StartTime
    public static final Integer EVENT_LENGTH = 7; // ActivityType, Note,
                                                  // ActivityName, StartDate,
                                                  // StartTime, EndDate, EndTime

    // Indexes of the parameters to retrieve from the constructor.
    public static final Integer INDEX_ACTIVITY_TYPE = 0;
    public static final Integer INDEX_ACTIVITY_NAME = 1;
    public static final Integer INDEX_ACTIVITY_NOTE = 2;
    public static final Integer INDEX_ACTIVITY_STARTDATE = 3;
    public static final Integer INDEX_ACTIVITY_STARTTIME = 4;
    public static final Integer INDEX_ACTIVITY_ENDDATE = 5;
    public static final Integer INDEX_ACTIVITY_ENDTIME = 6;

    private ActivityName name;
    private ActivityDate startDate;
    private ActivityTime startTime;
    private ActivityDate endDate;
    private ActivityTime endTime;
    private Note note;
    private String activityType;

    // Every Activity Object will have an array list of it's details for ease of
    // accessibility
    private ArrayList<String> activityDetails;
    private UniqueTagList tags;

    /**
     * For floatingTask
     * Every field must be present and not null.
     */
    public Activity(String type, ActivityName name, Note note) {
        this.activityType = type;
        this.name = name;
        this.note = note;
        setActivityDetails();
    }
    
    /**
     * For Task
     * Every field must be present and not null.
     */
    public Activity(String type, ActivityName name, Note note, ActivityDate startDate, ActivityTime startTime) {
        this.activityType = type;
        this.name = name;
        this.note = note;
        this.startDate = startDate;
        this.startTime = startTime;
        setActivityDetails();
    }
    
    /**
     * For Event
     * Every field must be present and not null.
     */
    public Activity(String type, ActivityName name, Note note, ActivityDate startDate, ActivityTime startTime, ActivityDate endDate, ActivityTime endTime) {
        this.activityType = type;
        this.name = name;
        this.note = note;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        setActivityDetails();
    }
    

    /**
     * Copy constructor.
     * 
     */
    public Activity(ReadOnlyActivity source) {
        
        if (source.getActivityType().equals(FLOATING_TASK_TYPE)) {
            activityType = source.getActivityType();
            name = source.getActivityName();
            note = source.getNote();
        } else if (source.getActivityType().equals(TASK_TYPE)) {
            activityType = source.getActivityType();;
            name = source.getActivityName();
            note = source.getNote();
            startDate = source.getActivityStartDate();
            startTime = source.getActivityStartTime();
        } else if (source.getActivityType().equals(EVENT_TYPE)) {
            activityType = source.getActivityType();;
            name = source.getActivityName();
            note = source.getNote();
            startDate = source.getActivityStartDate();
            startTime = source.getActivityStartTime();
            endDate = source.getActivityEndDate();
            endTime = source.getActivityEndTime();
        }
        this.activityDetails = source.getActivityDetails();

    }

    @Override
    public ActivityName getActivityName() {
        return this.name;
    }

    @Override
    public Note getNote() {
        return this.note;
    }

    @Override
    public ActivityDate getActivityStartDate() {
        return this.startDate;
    }

    @Override
    public ActivityTime getActivityStartTime() {
        return this.startTime;
    }

    @Override
    public ActivityDate getActivityEndDate() {
        return this.endDate;
    }

    @Override
    public ActivityTime getActivityEndTime() {
        return this.endTime;
    }

    @Override
    public String getActivityType() {
        return this.activityType;
    }

    @Override
    public void setActivityDetails() {
        if (activityType == FLOATING_TASK_TYPE) {
            activityDetails = new ArrayList<String>(FLOATING_TASK_LENGTH);
            activityDetails.add(activityType);
            activityDetails.add(name.toString());
            activityDetails.add(note.toString());
        } else if (activityType == TASK_TYPE) {
            activityDetails = new ArrayList<String>(TASK_LENGTH);
            activityDetails.add(activityType);
            activityDetails.add(name.toString());
            activityDetails.add(note.toString());
            activityDetails.add(startDate.toString());
            activityDetails.add(startTime.toString());
        } else if (activityType == EVENT_TYPE) {
            activityDetails = new ArrayList<String>(EVENT_LENGTH);
            activityDetails.add(activityType);
            activityDetails.add(name.toString());
            activityDetails.add(note.toString());
            activityDetails.add(startDate.toString());
            activityDetails.add(startTime.toString());
            activityDetails.add(endDate.toString());
            activityDetails.add(endTime.toString());
        }
    }

    /**
     * returns the arrayList consisting of an activity's details.
     */
    @Override
    public ArrayList<String> getActivityDetails() {
        return activityDetails;
    }

    // USELESS?
    
    @Override
    public UniqueTagList getTags() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTags(UniqueTagList uniqueTagList) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Activity get() {
        return this;
    }

    @Override
    public String toString() {
        switch(this.activityType){
        case FLOATING_TASK_TYPE:
            return getFloatingTaskAsText();
        case TASK_TYPE:
            return getTaskAsText();
        case EVENT_TYPE:
            return getEventAsText();
        }
        return null;
    }
}
