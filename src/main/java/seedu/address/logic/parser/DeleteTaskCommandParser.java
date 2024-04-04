package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteTaskCommand
     * and returns a DeleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ");
        if (splitArgs.length < 2) {
            throw new ParseException("Invalid command format!");
        }

        String uidStr = splitArgs[0].trim();

        if (uidStr.isEmpty() || !uidStr.startsWith("uid/")) {
            throw new ParseException("Invalid UID format!");
        }

        UniqueId uid = new UniqueId(uidStr.substring(4));

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(splitArgs[1]);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid task index format!");
        }

        return new DeleteTaskCommand(uid, taskIndex);
    }
}
