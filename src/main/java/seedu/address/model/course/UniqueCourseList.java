package seedu.address.model.course;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.course.exceptions.DuplicateCourseException;

public class UniqueCourseList {

    private final ObservableList<Course> internalList = FXCollections.observableArrayList();
    private final ObservableList<Course> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean hasCourse(CourseCode code) {
        requireNonNull(code);
        return internalList.stream().anyMatch(c -> c.getCourseCode().equals(code));
    }

    public Optional<Course> getCourse(CourseCode code) {
        requireNonNull(code);
        return internalList.stream().filter(c -> c.getCourseCode().equals(code)).findFirst();
    }

    public void addCourse(Course course) {
        requireNonNull(course);
        if (hasCourse(course.getCourseCode())) {
            throw new DuplicateCourseException();
        }
        internalList.add(course);
    }

    public void setCourses(List<Course> courses) {
        internalList.setAll(courses != null ? courses : new ArrayList<>());
    }

    public ObservableList<Course> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public List<Course> getCourses() {
        return new ArrayList<>(internalList);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof UniqueCourseList)) return false;
        UniqueCourseList o = (UniqueCourseList) other;
        return internalList.equals(o.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
