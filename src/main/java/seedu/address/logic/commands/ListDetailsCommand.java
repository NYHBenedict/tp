package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.course.Course;
import seedu.address.model.student.Student;

/**
 * Lists all assessments and all students for the specified course(s).
 */
public class ListDetailsCommand extends Command {

    public static final String COMMAND_WORD = "listdetails";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays assessments and students for one or more courses.\n"
            + "Parameters: c/COURSE_CODE[,COURSE_CODE,...]\n"
            + "Example: " + COMMAND_WORD + " c/CS2103T";

    public static final String MESSAGE_COURSE_NOT_FOUND = "\u274C Course %s not found.";

    private final List<String> courseCodes;

    /**
     * List details of given course code(s).
     * Shows assessments and students associated.
     * @param courseCodes
     */
    public ListDetailsCommand(List<String> courseCodes) {
        requireNonNull(courseCodes);
        this.courseCodes = courseCodes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (courseCodes.isEmpty()) {
            throw new CommandException("\u274C No course codes provided.");
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < courseCodes.size(); i++) {
            String courseCode = courseCodes.get(i).trim().toUpperCase();

            if (!model.hasCourse(courseCode)) {
                throw new CommandException(String.format(MESSAGE_COURSE_NOT_FOUND, courseCode));
            }

            Optional<Course> courseOptional = model.getCourse(courseCode);
            if (courseOptional.isEmpty()) {
                throw new CommandException(String.format(MESSAGE_COURSE_NOT_FOUND, courseCode));
            }
            Course course = courseOptional.get();

            sb.append("Course: ").append(course.getCourseCode()).append("\n");

            ObservableList<Assessment> assessments = course.getAssessments();
            sb.append("  Assessments:\n");
            if (assessments.isEmpty()) {
                sb.append("    No assessments found.\n");
            } else {
                for (int j = 0; j < assessments.size(); j++) {
                    sb.append("    ").append(j + 1).append(". ").append(assessments.get(j)).append("\n");
                }
            }

            List<Student> students = course.getStudents();
            sb.append("  Students:\n");
            if (students.isEmpty()) {
                sb.append("    No students found.\n");
            } else {
                for (int j = 0; j < students.size(); j++) {
                    sb.append("    ").append(j + 1).append(". ").append(students.get(j)).append("\n");
                }
            }

            if (i < courseCodes.size() - 1) {
                sb.append("\n");
            }
        }

        return new CommandResult(sb.toString().trim());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ListDetailsCommand
                        && courseCodes.equals(((ListDetailsCommand) other).courseCodes));
    }
}
