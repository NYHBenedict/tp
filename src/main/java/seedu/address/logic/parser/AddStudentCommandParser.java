package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseCode;
import seedu.address.model.person.Email;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;

/**
 * Parses input arguments and creates a new AddStudentCommand.
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_COURSE, CliSyntax.PREFIX_ID, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_EMAIL);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_COURSE, CliSyntax.PREFIX_ID, CliSyntax.PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                CliSyntax.PREFIX_COURSE, CliSyntax.PREFIX_ID, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_EMAIL);

        CourseCode courseCode = ParserUtil.parseCourseCode(argMultimap.getValue(CliSyntax.PREFIX_COURSE).get());
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(CliSyntax.PREFIX_ID).get());
        StudentName studentName = ParserUtil.parseStudentName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        Optional<Email> email = Optional.empty();
        if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            email = Optional.of(ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get()));
        }
        Student student = new Student(studentId, studentName, email);
        return new AddStudentCommand(courseCode, student);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(p -> argMultimap.getValue(p).isPresent());
    }
}
