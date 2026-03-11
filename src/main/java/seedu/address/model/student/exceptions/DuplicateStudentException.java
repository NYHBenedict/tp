package seedu.address.model.student.exceptions;

public class DuplicateStudentException extends RuntimeException {
    public DuplicateStudentException() {
        super("Operation would result in duplicate students");
    }
}
