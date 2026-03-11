package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class AssessmentName {
    public static final String MESSAGE_CONSTRAINTS = "Assessment name must be non-blank.";
    public final String value;

    public AssessmentName(String name) {
        requireNonNull(name);
        checkArgument(!name.isBlank(), MESSAGE_CONSTRAINTS);
        this.value = name.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssessmentName)) return false;
        return value.equals(((AssessmentName) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
