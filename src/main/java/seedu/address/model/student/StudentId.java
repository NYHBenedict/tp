package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student ID. Case-insensitive; stored in uppercase.
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Invalid student ID. Example: id/A0123456X";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9]{6,12}";

    public final String value;

    public StudentId(String studentId) {
        requireNonNull(studentId);
        String trimmed = studentId.trim();
        checkArgument(isValidStudentId(trimmed), MESSAGE_CONSTRAINTS);
        this.value = trimmed.toUpperCase();
    }

    public static boolean isValidStudentId(String test) {
        return test != null && test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof StudentId)) return false;
        return value.equals(((StudentId) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
