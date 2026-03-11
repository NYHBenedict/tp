package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.CourseCode;

/**
 * Lists all students in a course and sets the current course for display.
 */
public class ListStudentsCommand extends Command {

    public static final String COMMAND_WORD = "liststudents";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all students in a course.\n"
            + "Format: liststudents c/COURSE_CODE\n"
            + "Example: " + COMMAND_WORD + " c/CS2103T";

    public static final String MESSAGE_SUCCESS = "Displaying students in %1$s.";
    public static final String MESSAGE_COURSE_NOT_FOUND = "Course %1$s not found.";

    private final CourseCode courseCode;

    public ListStudentsCommand(CourseCode courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasCourse(courseCode)) {
            throw new CommandException(String.format(MESSAGE_COURSE_NOT_FOUND, courseCode));
        }
        model.setCurrentCourseForDisplay(java.util.Optional.of(courseCode));
        return new CommandResult(String.format(MESSAGE_SUCCESS, courseCode));
    }
}
