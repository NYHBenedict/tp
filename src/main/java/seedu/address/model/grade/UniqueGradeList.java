package seedu.address.model.grade;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.StudentId;

public class UniqueGradeList {

    private final ObservableList<Grade> internalList = FXCollections.observableArrayList();
    private final ObservableList<Grade> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public ObservableList<Grade> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void removeGradesForStudent(StudentId studentId) {
        internalList.removeIf(g -> g.getStudentId().equals(studentId));
    }

    public void setGrades(List<Grade> grades) {
        internalList.setAll(grades != null ? grades : new ArrayList<>());
    }
}
