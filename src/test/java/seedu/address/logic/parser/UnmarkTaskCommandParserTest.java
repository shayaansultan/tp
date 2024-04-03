package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;

public class UnmarkTaskCommandParserTest {

    private final UnmarkTaskCommandParser parser = new UnmarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnsUnmarkTaskCommand() throws Exception {
        UniqueId uid = new UniqueId("123");
        int taskNumber = 1;

        String userInput = "uid/123 1";
        UnmarkTaskCommand expectedCommand = new UnmarkTaskCommand(uid, taskNumber);

        UnmarkTaskCommand actualCommand = parser.parse(userInput);

        assertEquals(expectedCommand.getUid(), actualCommand.getUid());
        assertEquals(expectedCommand.getTaskNumber(), actualCommand.getTaskNumber());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userInput = "invalid arguments";

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidTaskNumber_throwsParseException() {
        String userInput = "uid/123 notAnInteger";

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
