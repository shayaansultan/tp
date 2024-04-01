package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void isValidDescription() {
        // null description
        assertFalse(Description.isValidDescription(null));

        // blank description
        assertFalse(Description.isValidDescription(""));

        // invalid descriptions
        assertFalse(Description.isValidDescription("-"));
        assertFalse(Description.isValidDescription(" "));

        // valid descriptions
        assertTrue(Description.isValidDescription("Buy milk"));
        assertTrue(Description.isValidDescription("123"));
        assertTrue(Description.isValidDescription("Buy 123 milk"));
    }

    @Test
    public void testToString() {
        Description description = new Description("Buy milk");
        assertEquals("Buy milk", description.toString());
    }

    @Test
    public void testEquals() {
        Description description1 = new Description("Buy milk");
        Description description2 = new Description("Buy milk");
        Description description3 = new Description("Buy bread");

        // same object -> returns true
        assertEquals(description1, description1);

        // same values -> returns true
        assertEquals(description1, description2);

        // different values -> returns false
        assertNotEquals(description1, description3);

        // different types -> returns false
        assertNotEquals(description1, 1);

        // null -> returns false
        assertNotEquals(description1, null);
    }

    @Test
    public void testHashCode() {
        Description description1 = new Description("Buy milk");
        Description description2 = new Description("Buy milk");
        assertEquals(description1.hashCode(), description2.hashCode());
    }
}
