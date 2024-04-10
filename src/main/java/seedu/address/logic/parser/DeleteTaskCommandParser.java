package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {
    private static final String COMMAND_FORMAT = DeleteTaskCommand.COMMAND_WORD
            + ": Deletes a task from the todo list of an employee. "
            + "Parameters: UID (must be a positive integer) TASK_INDEX (must be a positive integer)\n"
            + "The TASK_INDEX is one based and not zero based.\n"
            + "Example: " + DeleteTaskCommand.COMMAND_WORD + " uid/1 1";
    private static final String INVALID_FORMAT = "Invalid format!";
    private static final String INVALID_UID_FORMAT = "Invalid UID format! Correct format: uid/<UID>";
    private static final String INVALID_TASK_INDEX_FORMAT = "Invalid task index format! It should be a positive integer.";

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteTaskCommand
     * and returns a DeleteTaskCommand object for execution.
     * 
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ");
        if (splitArgs.length < 2) {
            throw new ParseException(INVALID_FORMAT + "\n" + COMMAND_FORMAT);
        }

        String uidString = splitArgs[0].trim();

        if (uidString.isEmpty() || !uidString.startsWith("uid/")) {
            throw new ParseException(INVALID_UID_FORMAT);
        }

        UniqueId uid = new UniqueId(uidString.substring(4));

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(splitArgs[1]);
        } catch (NumberFormatException e) {
            throw new ParseException(INVALID_TASK_INDEX_FORMAT);
        }

        return new DeleteTaskCommand(uid, taskIndex);
    }
}
