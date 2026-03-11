package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's name. 1-60 characters; multiple spaces collapsed.
 */
public class StudentName {

    public static final String MESSAGE_CONSTRAINTS = "Invalid name. Example: n/John Tan";
    public static final int MAX_LENGTH = 60;
    public static final String VALIDATION_REGEX = "[\\p{L}\\p{N}\\s.,'-]+";

    public final String fullName;

    public StudentName(String name) {
        requireNonNull(name);
        String trimmed = name.trim().replaceAll("\\s+", " ");
        checkArgument(isValidStudentName(trimmed), MESSAGE_CONSTRAINTS);
        this.fullName = trimmed;
    }

    public static boolean isValidStudentName(String test) {
        return test != null && !test.isEmpty() && test.length() <= MAX_LENGTH && test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof StudentName)) return false;
        return fullName.equals(((StudentName) other).fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
