package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseCode;
import seedu.address.model.student.StudentId;

/**
 * Parses input arguments and creates a new RemoveStudentCommand.
 */
public class RemoveStudentCommandParser implements Parser<RemoveStudentCommand> {

    public RemoveStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_COURSE, CliSyntax.PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_COURSE, CliSyntax.PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveStudentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_COURSE, CliSyntax.PREFIX_ID);

        CourseCode courseCode = ParserUtil.parseCourseCode(argMultimap.getValue(CliSyntax.PREFIX_COURSE).get());
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(CliSyntax.PREFIX_ID).get());
        return new RemoveStudentCommand(courseCode, studentId);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(p -> argMultimap.getValue(p).isPresent());
    }
}
