package seedu.address.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a course code. Case-insensitive; stored in uppercase.
 */
public class CourseCode {

    public static final String MESSAGE_CONSTRAINTS = "Invalid course code. Example: c/CS2103T";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9]{2,10}";

    public final String value;

    public CourseCode(String courseCode) {
        requireNonNull(courseCode);
        String trimmed = courseCode.trim();
        checkArgument(isValidCourseCode(trimmed), MESSAGE_CONSTRAINTS);
        this.value = trimmed.toUpperCase();
    }

    public static boolean isValidCourseCode(String test) {
        return test != null && test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof CourseCode)) return false;
        return value.equals(((CourseCode) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
