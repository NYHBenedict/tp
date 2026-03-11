package seedu.address.model.assessment;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class MaxScore {
    public static final String MESSAGE_CONSTRAINTS = "Max score must be a positive number.";
    public final double value;

    public MaxScore(double value) {
        checkArgument(value > 0, MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaxScore)) return false;
        return Double.compare(((MaxScore) o).value, value) == 0;
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
