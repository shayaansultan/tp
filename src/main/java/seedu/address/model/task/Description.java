package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the address book.
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Descriptions can take any values, and it should not be blank";

    /*
     * The description should not be blank.
     */
    public static final String VALIDATION_REGEX = "^.+$";

    public String fullDescription;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        fullDescription = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.trim().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Description)) {
            return false;
        }

        Description otherDescription = (Description) other;
        return fullDescription.equals(otherDescription.fullDescription);
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }
}
