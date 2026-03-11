package seedu.address.model.course.exceptions;

public class DuplicateCourseException extends RuntimeException {
    public DuplicateCourseException() {
        super("Course already exists");
    }
}
