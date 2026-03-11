package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.StudentNotFoundException;

/**
 * A list of students that enforces uniqueness by {@code Student#isSameStudent(Student)}.
 */
public class UniqueStudentList {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudent);
    }

    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
    }

    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public boolean removeByStudentId(StudentId id) {
        requireNonNull(id);
        return internalList.removeIf(s -> s.getStudentId().equals(id));
    }

    public Optional<Student> getByStudentId(StudentId id) {
        requireNonNull(id);
        return internalList.stream().filter(s -> s.getStudentId().equals(id)).findFirst();
    }

    public void setStudents(List<Student> replacement) {
        List<Student> list = replacement != null ? replacement : new ArrayList<>();
        if (!studentsAreUnique(list)) {
            throw new DuplicateStudentException();
        }
        internalList.setAll(list);
    }

    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameStudent(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
