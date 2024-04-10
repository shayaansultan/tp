package seedu.address.logic.parser;

import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;

/**
 * Parses input arguments and creates a new MarkTaskCommand object
 */
public class MarkTaskCommandParser implements Parser<MarkTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * MarkTaskCommand and returns a MarkTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public MarkTaskCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split("\\s+");
        if (splitArgs.length != 2 || !splitArgs[0].startsWith("uid/")) {
            throw new ParseException("Invalid command format! Expected format: mark uid/[UID] [TASK_NUMBER]");
        }

        UniqueId uid = new UniqueId(splitArgs[0].substring(4));
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(splitArgs[1]);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid task number format! Task number should be an integer.");
        }

        return new MarkTaskCommand(uid, taskNumber);
    }
}
