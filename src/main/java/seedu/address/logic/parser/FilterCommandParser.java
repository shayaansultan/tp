package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Role;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    private static final String MULTIPLE_VALUES_NOT_ALLOWED = "Multiple %s values are not allowed.";
    private static final String INVALID_ARGUMENTS = "Invalid arguments for filter command: %s";
    private static final Logger LOGGER = Logger.getLogger(FilterCommandParser.class.getName());

    @Override
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_TEAM, PREFIX_ROLE);

        checkSingleValues(argMultimap);
        checkAtLeastOneFieldIsPresent(argMultimap, args);

        StringJoiner filterDescription = new StringJoiner(", ");
        Predicate<Employee> predicate = buildPredicatesAndDescriptions(argMultimap, filterDescription);

        return new FilterCommand(predicate, filterDescription.toString());
    }

    /**
     * Checks that only single values are provided for each prefix that doesn't support multiple values.
     *
     * @param argMultimap The parsed argument multimap.
     * @throws ParseException if multiple values are found for a prefix that doesn't support them.
     */
    private void checkSingleValues(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getAllValues(PREFIX_NAME).size() > 1) {
            throw new ParseException(String.format(MULTIPLE_VALUES_NOT_ALLOWED, "name"));
        }
        if (argMultimap.getAllValues(PREFIX_ROLE).size() > 1) {
            throw new ParseException(String.format(MULTIPLE_VALUES_NOT_ALLOWED, "role"));
        }
        if (argMultimap.getAllValues(PREFIX_TEAM).size() > 1) {
            throw new ParseException(String.format(MULTIPLE_VALUES_NOT_ALLOWED, "team"));
        }
    }

    /**
     * Ensures that at least one valid filtering field is present in the argument multimap.
     *
     * @param argMultimap The parsed argument multimap.
     * @param args The original input arguments.
     * @throws ParseException if no valid filter criteria are provided.
     */
    private void checkAtLeastOneFieldIsPresent(ArgumentMultimap argMultimap, String args) throws ParseException {
        if (argMultimap.getValue(PREFIX_NAME).isEmpty()
                && argMultimap.getAllValues(PREFIX_TAG).isEmpty()
                && argMultimap.getValue(PREFIX_TEAM).isEmpty()
                && argMultimap.getValue(PREFIX_ROLE).isEmpty()) {
            LOGGER.log(Level.WARNING, "No valid fields present for filter command");
            throw new ParseException(String.format(INVALID_ARGUMENTS, args));
        }
    }

    /**
     * Builds the predicate for filtering employees and constructs the filter description
     * based on the given arguments.
     *
     * @param argMultimap The parsed argument multimap.
     * @param filterDescription The string joiner for building the filter description.
     * @return A predicate that represents the combined filtering criteria.
     * @throws ParseException if there is an issue parsing any of the provided filter values.
     */
    private Predicate<Employee> buildPredicatesAndDescriptions(
            ArgumentMultimap argMultimap, StringJoiner filterDescription) throws ParseException {
        Predicate<Employee> predicate = employee -> true;

        for (String tagValue : argMultimap.getAllValues(PREFIX_TAG)) {
            Tag tag = ParserUtil.parseTag(tagValue);
            predicate = predicate.and(employee -> employee.getTags().contains(tag));
            filterDescription.add("Tag: " + tag);
        }

        if (argMultimap.getValue(PREFIX_TEAM).isPresent()) {
            String teamName = argMultimap.getValue(PREFIX_TEAM).get().trim();
            predicate = predicate.and(employee -> employee.getTeam().toString().equalsIgnoreCase(teamName));
            filterDescription.add("Team: " + teamName);
        }

        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
            predicate = predicate.and(employee -> employee.getRole().equals(role));
            filterDescription.add("Role: " + role);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameString = argMultimap.getValue(PREFIX_NAME).get().trim();
            predicate = predicate.and(employee -> employee.getName().fullName.equalsIgnoreCase(nameString));
            filterDescription.add("Name: " + nameString);
        }

        return predicate;
    }

    private void requireNonNull(String args) {
        if (args == null) {
            throw new NullPointerException();
        }
    }
}
