package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.UniqueId;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final Logger LOGGER = Logger.getLogger(DeleteCommandParser.class.getName());
    private static final String UID_PREFIX = "uid/";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        LOGGER.log(Level.INFO, "Parsing delete command: " + args);
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            LOGGER.log(Level.WARNING, "Arguments are empty");
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.startsWith(UID_PREFIX)) {
            return parseDeleteByUniqueId(trimmedArgs);
        }

        try {
            return parseDeleteByIndex(trimmedArgs);
        } catch (ParseException pe) {
            LOGGER.log(Level.INFO, "Parsing by index failed, trying by name");
            return parseDeleteByName(trimmedArgs);
        }
    }

    /**
     * Parses the given string of arguments to extract an index and creates a DeleteCommand
     * for deleting an employee by that index.
     * This method expects the argument string to represent a valid integer index. It attempts
     * to parse the argument as an index using ParserUtil.parseIndex. If successful, it creates
     * and returns a DeleteCommand using this index. If the parsing fails due to an invalid format
     * or other reasons, it throws a ParseException with a message indicating the expected format.
     *
     * @param args The string of arguments entered by the user, expected to represent an index.
     * @return A DeleteCommand created with the parsed index, ready for execution.
     * @throws ParseException If the args string does not represent a valid integer index or
     *                        if it does not conform to the expected format.
     */
    private DeleteCommand parseDeleteByIndex(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            LOGGER.log(Level.WARNING, "Invalid index format");
            throw pe;
        }
    }

    /**
     * Parses the given string of arguments to extract a unique identifier (UID) and creates a DeleteCommand
     * for deleting an employee by that UID.
     * This method expects the argument string to start with a 'uid/' prefix followed by a sequence of digits
     * representing the UID. It validates the format and extracts the UID part. If the UID is valid, it creates
     * and returns a DeleteCommand using this UID. If the UID is empty, does not follow the expected format, or
     * contains non-digit characters, it throws a ParseException with a message indicating the expected format.
     *
     * @param args The string of arguments entered by the user, expected to start with 'uid/' followed by the UID.
     * @return A DeleteCommand created with the parsed UID, ready for execution.
     * @throws ParseException If the UID string does not conform to the expected format or contains invalid characters.
     */
    private DeleteCommand parseDeleteByUniqueId(String args) throws ParseException {
        String[] splitArgs = args.split("/", 2);
        if (splitArgs.length != 2 || splitArgs[1].trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Invalid UID format");
            throw new ParseException("Invalid UID format. " + DeleteCommand.MESSAGE_USAGE);
        }

        String uidStr = splitArgs[1].trim();
        if (!uidStr.matches("\\d+")) {
            LOGGER.log(Level.WARNING, "UID should only contain numbers");
            throw new ParseException("UID should only contain numbers. " + DeleteCommand.MESSAGE_USAGE);
        }

        UniqueId uid = new UniqueId(uidStr);
        return new DeleteCommand(uid);
    }


    /**
     * Parses the given string of arguments to extract a name and creates a DeleteCommand
     * for deleting an employee by that name.
     * This method expects the argument string to represent a valid name consisting of
     * alphabetic characters and spaces. It checks if the name is non-empty and matches
     * the expected pattern. If the validation passes, it creates and returns a DeleteCommand
     * using this name. If the name is empty or does not match the pattern, it throws a
     * ParseException with a message indicating the expected format.
     *
     * @param name The string of arguments entered by the user, expected to represent an employee's name.
     * @return A DeleteCommand created with the parsed name, ready for execution.
     * @throws ParseException If the name string is empty or does not match the expected name pattern.
     */
    private DeleteCommand parseDeleteByName(String name) throws ParseException {
        if (name.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Name cannot be empty");
            throw new ParseException("Name cannot be empty. " + DeleteCommand.MESSAGE_USAGE);
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            LOGGER.log(Level.WARNING, "Name should only contain alphabetic characters and spaces");
            throw new ParseException("Name should only contain alphabetic characters and spaces. "
                    + DeleteCommand.MESSAGE_USAGE);
        }

        return new DeleteCommand(name.trim());
    }
}
