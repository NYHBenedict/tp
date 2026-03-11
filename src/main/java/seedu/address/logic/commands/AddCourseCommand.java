package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.CourseCode;

/**
 * Adds a course so that students can be added to it.
 */
public class AddCourseCommand extends Command {

    public static final String COMMAND_WORD = "addcourse";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a course.\n"
            + "Parameters: c/COURSE_CODE\n"
            + "Example: " + COMMAND_WORD + " c/CS2103T";

    public static final String MESSAGE_SUCCESS = "Course added: %1$s";
    public static final String MESSAGE_DUPLICATE_COURSE = "Course %1$s already exists.";

    private final CourseCode courseCode;

    public AddCourseCommand(CourseCode courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasCourse(courseCode)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_COURSE, courseCode));
        }
        model.addCourse(courseCode);
        return new CommandResult(String.format(MESSAGE_SUCCESS, courseCode));
    }
}
