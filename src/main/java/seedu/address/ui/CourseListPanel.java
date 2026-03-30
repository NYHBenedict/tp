package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.course.Course;

/**
 * Panel containing the list of courses to be displayed in the UI.
 */
public class CourseListPanel extends UiPart<Region> {

    private static final String FXML = "CourseListPanel.fxml";
    private final ObservableList<Assessment> assessmentList;

    @FXML
    private ListView<Course> courseListView;

    /**
     * Creates a {@code CourseListPanel} that displays the given list of courses.
     */
    public CourseListPanel(ObservableList<Course> courseList, ObservableList<Assessment> assessmentList) {
        super(FXML);
        this.assessmentList = assessmentList;
        courseListView.setItems(courseList);
        courseListView.setCellFactory(listView -> new CourseListViewCell());
    }

    class CourseListViewCell extends ListCell<Course> {
        @Override
        protected void updateItem(Course course, boolean empty) {
            super.updateItem(course, empty);

            if (empty || course == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CourseCard(course, getIndex() + 1, assessmentList).getRoot());
            }
        }
    }
}
