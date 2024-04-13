package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;

public class DeleteTaskCommandParserTest {
    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() throws IllegalStateException, IllegalValueException {
        DeleteTaskCommand command = parser.parse("uid/1 1");
        assertEquals(new DeleteTaskCommand(new UniqueId(1), 1), command);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1"));
        assertThrows(ParseException.class, () -> parser.parse("uid/1"));
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_invalidUid_throwsParseException() {
        try {
            parser.parse("ui/ 1");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Invalid UID format! Correct format: uid/<UID>");
        }
    }

    @Test
    public void parse_invalidTaskIndexFormat_throwsParseException() {
        try {
            parser.parse("uid/100 one");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Invalid task index format!"
                    + "It should be a positive integer more than 0.");
        }
    }
}
