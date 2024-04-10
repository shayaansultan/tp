package seedu.address.logic.parser;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;
import seedu.address.model.task.Description;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    private static final String COMMAND_FORMAT = AddTaskCommand.COMMAND_WORD
            + ": Adds a task to the todo list of an employee.\n"
            + "Format: " + AddTaskCommand.COMMAND_WORD + "UID (must be a valid UID) "
            + "TASK_DESCRIPTION (cannot be blank)\n"
            + "Example: " + AddTaskCommand.COMMAND_WORD + " uid/1 Buy milk";

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * 
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ", 2);
        if (splitArgs.length < 2) {
            throw new ParseException("Invalid command format!\n"
                    + COMMAND_FORMAT);
        }

        String content = splitArgs[1];
        if (!Description.isValidDescription(content)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }

        Description description = new Description(content);

        String uidStr = splitArgs[0].trim();

        System.out.println("uidStr" + uidStr);

        if (uidStr.isEmpty() || !uidStr.startsWith("uid/")) {
            throw new ParseException("Invalid UID format!");
        }

        UniqueId uid = new UniqueId(uidStr.substring(4));

        return new AddTaskCommand(uid, description);
    }
}
