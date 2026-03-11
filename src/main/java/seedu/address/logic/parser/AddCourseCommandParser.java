package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseCode;

/**
 * Parses input arguments and creates a new AddCourseCommand.
 */
public class AddCourseCommandParser implements Parser<AddCourseCommand> {

    public AddCourseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_COURSE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_COURSE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCourseCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_COURSE);
        CourseCode code = ParserUtil.parseCourseCode(argMultimap.getValue(CliSyntax.PREFIX_COURSE).get());
        return new AddCourseCommand(code);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(p -> argMultimap.getValue(p).isPresent());
    }
}
