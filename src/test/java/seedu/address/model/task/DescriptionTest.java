package seedu.address.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DescriptionTest {

    @Test
    public void isValidDescription() {
        // null description
        assertFalse(Description.isValidDescription(null));

        // blank description
        assertFalse(Description.isValidDescription(""));

        // valid descriptions
        assertTrue(Description.isValidDescription("Buy milk"));
        assertTrue(Description.isValidDescription("-"));
    }
}