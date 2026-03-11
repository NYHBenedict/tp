package seedu.address.model.assessment;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UniqueAssessmentList {

    private final ObservableList<Assessment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assessment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public ObservableList<Assessment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void setAssessments(List<Assessment> assessments) {
        internalList.setAll(assessments != null ? assessments : new ArrayList<>());
    }
}
