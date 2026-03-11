package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.ListStudentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseCode;

/**
 * Parses input arguments and creates a new ListStudentsCommand.
 */
public class ListStudentsCommandParser implements Parser<ListStudentsCommand> {

    public ListStudentsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_COURSE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_COURSE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListStudentsCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_COURSE);
        CourseCode courseCode = ParserUtil.parseCourseCode(argMultimap.getValue(CliSyntax.PREFIX_COURSE).get());
        return new ListStudentsCommand(courseCode);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(p -> argMultimap.getValue(p).isPresent());
    }
}
