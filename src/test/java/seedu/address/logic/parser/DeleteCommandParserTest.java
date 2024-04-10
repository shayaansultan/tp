package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_EMPLOYEE));
    }

    @Test
    public void parse_validName_returnsDeleteCommand() {
        String validName = "John Doe";
        DeleteCommand expectedCommand = new DeleteCommand(validName);
        assertParseSuccess(parser, validName, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_specialCharacterArgs_throwsParseException() {
        String expectedMessage = "Name should only contain alphabetic characters and spaces. "
                + DeleteCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "$", expectedMessage);
    }


    @Test
    public void parse_invalidUidFormat_throwsNullPointerException() {
        assertThrows(ParseException.class, () -> parser.parse("uid/"));
    }

    @Test
    public void parse_missingUid_throwsParseException() {
        String expectedMessage = "Invalid UID format. " + DeleteCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "uid/", expectedMessage);
    }

    @Test
    public void parse_validUid_returnsDeleteCommand() {
        String validUid = "12345";
        UniqueId uid = new UniqueId(validUid);
        DeleteCommand expectedCommand = new DeleteCommand(uid);

        assertNotNull(expectedCommand);
        assertParseSuccess(parser, "uid/" + validUid, expectedCommand);
    }

    @Test
    public void parseDeleteByUniqueId_invalidUidFormat_throwsParseException() {
        // UID format with non-digits
        assertParseFailure(parser, "uid/abc",
                "UID should only contain numbers. "
                        + DeleteCommand.MESSAGE_USAGE);

        // UID format with special characters
        assertParseFailure(parser, "uid/123!@#",
                "UID should only contain numbers. "
                        + DeleteCommand.MESSAGE_USAGE);

        // No UID after the prefix
        assertParseFailure(parser, "uid/",
                "Invalid UID format. "
                        + DeleteCommand.MESSAGE_USAGE);
    }

    @Test
    public void parseDeleteByName_invalidNameFormat_throwsParseException() {
        // Name with numbers
        assertParseFailure(parser, "John Doe1",
                "Name should only contain alphabetic characters and spaces. "
                        + DeleteCommand.MESSAGE_USAGE);

        // Name with special characters
        assertParseFailure(parser, "John@Doe",
                "Name should only contain alphabetic characters and spaces. "
                        + DeleteCommand.MESSAGE_USAGE);
    }
    @Test
    public void parse_emptyStringAfterTrim_throwsParseException() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "   ", expectedMessage);
    }

    @Test
    public void parse_invalidUid_throwsParseException() {
        String invalidUid = "invalid";
        assertParseFailure(parser, "uid/" + invalidUid,
                "UID should only contain numbers. " + DeleteCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_missingUidPart_throwsParseException() {
        String expectedMessage = "Invalid UID format. " + DeleteCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "uid/", expectedMessage);
    }

    @Test
    public void parse_uidWithExtraSlash_throwsParseException() {
        String uidWithExtraSlash = "12345/";
        assertParseFailure(parser, "uid/" + uidWithExtraSlash,
                "UID should only contain numbers. " + DeleteCommand.MESSAGE_USAGE);
    }
}
