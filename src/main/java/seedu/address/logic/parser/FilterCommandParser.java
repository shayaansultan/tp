package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Role;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    @Override
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_TEAM, PREFIX_ROLE);

        if (argMultimap.getAllValues(PREFIX_NAME).size() > 1) {
            throw new ParseException("Multiple name values are not allowed.");
        }
        if (argMultimap.getAllValues(PREFIX_ROLE).size() > 1) {
            throw new ParseException("Multiple role values are not allowed.");
        }
        if (argMultimap.getAllValues(PREFIX_TEAM).size() > 1) {
            throw new ParseException("Multiple team values are not allowed.");
        }

        if (!argMultimap.getValue(PREFIX_NAME).isPresent()
                && argMultimap.getAllValues(PREFIX_TAG).isEmpty()
                && !argMultimap.getValue(PREFIX_TEAM).isPresent()
                && !argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            throw new ParseException("Invalid arguments for filter command: " + args);
        }

        Predicate<Employee> predicate = employee -> true;
        StringJoiner filterDescription = new StringJoiner("and ");

        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        if (!tags.isEmpty()) {
            for (String tagValue : tags) {
                Tag tag = ParserUtil.parseTag(tagValue);
                predicate = predicate.and(employee -> employee.getTags().contains(tag));
                filterDescription.add("Tag: " + tag);
            }
        }

        if (argMultimap.getValue(PREFIX_TEAM).isPresent()) {
            String teamName = "Team " + argMultimap.getValue(PREFIX_TEAM).get().trim();
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

        return new FilterCommand(predicate, filterDescription.toString());
    }

    private void requireNonNull(String args) {
        if (args == null) {
            throw new NullPointerException();
        }
    }
}
