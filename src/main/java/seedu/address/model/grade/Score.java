package seedu.address.model.grade;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Score {
    public static final String MESSAGE_CONSTRAINTS = "Score must be a non-negative number.";
    public final double value;

    public Score(double value) {
        checkArgument(value >= 0, MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        return Double.compare(((Score) o).value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
