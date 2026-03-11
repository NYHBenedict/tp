package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.CourseCode;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;

/**
 * Adds a student to a course.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "addstudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to a course.\n"
            + "Format: addstudent c/COURSE_CODE id/STUDENT_ID n/NAME [e/EMAIL]\n"
            + "Example: " + COMMAND_WORD + " c/CS2103T id/A0123456X n/John Tan e/john@u.nus.edu";

    public static final String MESSAGE_SUCCESS = "Student added: %1$s %2$s (Course: %3$s).";
    public static final String MESSAGE_COURSE_NOT_FOUND = "Course %1$s not found.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "Student %1$s already exists in %2$s.";

    private final CourseCode courseCode;
    private final Student student;

    public AddStudentCommand(CourseCode courseCode, Student student) {
        this.courseCode = courseCode;
        this.student = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasCourse(courseCode)) {
            throw new CommandException(String.format(MESSAGE_COURSE_NOT_FOUND, courseCode));
        }
        try {
            model.addStudentToCourse(courseCode, student);
        } catch (DuplicateStudentException e) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_STUDENT,
                    student.getStudentId(), courseCode));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                student.getStudentId(), student.getStudentName(), courseCode));
    }
}
