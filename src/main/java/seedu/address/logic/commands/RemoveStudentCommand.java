package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.CourseCode;
import seedu.address.model.student.StudentId;

/**
 * Removes a student from a course (and all their grades in that course).
 */
public class RemoveStudentCommand extends Command {

    public static final String COMMAND_WORD = "removestudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a student from a course.\n"
            + "Format: removestudent c/COURSE_CODE id/STUDENT_ID\n"
            + "Example: " + COMMAND_WORD + " c/CS2103T id/A0123456X";

    public static final String MESSAGE_SUCCESS = "Removed student %1$s from %2$s.";
    public static final String MESSAGE_COURSE_NOT_FOUND = "Course %1$s not found.";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student %1$s not found in %2$s.";

    private final CourseCode courseCode;
    private final StudentId studentId;

    public RemoveStudentCommand(CourseCode courseCode, StudentId studentId) {
        this.courseCode = courseCode;
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasCourse(courseCode)) {
            throw new CommandException(String.format(MESSAGE_COURSE_NOT_FOUND, courseCode));
        }
        boolean removed = model.removeStudentFromCourse(courseCode, studentId);
        if (!removed) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, studentId, courseCode));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentId, courseCode));
    }
}
