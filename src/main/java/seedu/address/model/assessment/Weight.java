package seedu.address.model.assessment;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Weight {
    public static final String MESSAGE_CONSTRAINTS = "Weight must be between 0 and 100.";
    public final double value;

    public Weight(double value) {
        checkArgument(value >= 0 && value <= 100, MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weight)) return false;
        return Double.compare(((Weight) o).value, value) == 0;
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
