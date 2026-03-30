package seedu.address.model.course.exceptions;

/**
 * Signals that the operation will result in duplicate Courses.
 */
public class DuplicateCourseException extends RuntimeException {
    public DuplicateCourseException() {
        super("Operation would result in duplicate courses");
    }
}
