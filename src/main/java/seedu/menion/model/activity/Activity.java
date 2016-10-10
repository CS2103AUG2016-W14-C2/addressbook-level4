package seedu.menion.model.activity;

import seedu.menion.commons.util.CollectionUtil;
import seedu.menion.model.tag.UniqueTagList;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Activity implements ReadOnlyActivity {

    private ActivityName name;
    private ActivityDate deadline;
    private ActivityTime reminder;
    private Note priority;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Activity(ActivityName name, ActivityDate deadline, ActivityTime reminder, Note priority, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, deadline, reminder, priority, tags);
        this.name = name;
        this.deadline = deadline;
        this.reminder = reminder;
        this.priority = priority;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Activity(ReadOnlyActivity source) {
        this(source.getName(), source.getDeadline(), source.getReminder(), source.getPriority(), source.getTags());
    }

    @Override
    public ActivityName getName() {
        return name;
    }

    @Override
    public ActivityDate getDeadline() {
        return deadline;
    }

    @Override
    public ActivityTime getReminder() {
        return reminder;
    }

    @Override
    public Note getPriority() {
        return priority;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyActivity // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyActivity) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, reminder, priority, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
