package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.model.DisplayMode;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

/**
 * Lists all courses in the system.
 */
public class ListCoursesCommand extends Command {

    public static final String COMMAND_WORD = "listcourses";

    /**
     * Executes the list courses command.
     *
     * @param model the model
     * @return command result containing the list of courses
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        ObservableList<Course> courses = model.getCourseList();
        model.setCurrentCourseForDisplay(Optional.empty());
        model.setDetailedCoursesForDisplay(List.of());
        model.setDisplayMode(DisplayMode.COURSES);

        if (courses.isEmpty()) {
            return new CommandResult("No Courses found.");
        }

        StringBuilder sb = new StringBuilder("Courses:\n");
        for (int i = 0; i < courses.size(); i++) {
            sb.append(i + 1).append(". ").append(courses.get(i)).append("\n");
        }

        return new CommandResult(sb.toString().trim());
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof ListCoursesCommand);
    }
}
