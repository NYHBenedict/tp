package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.course.Course;

/**
 * Panel containing the expanded course details to be displayed in the UI.
 */
public class CourseDetailListPanel extends UiPart<Region> {

    private static final String FXML = "CourseDetailListPanel.fxml";

    @FXML
    private ListView<Course> courseDetailListView;

    /**
     * Creates a {@code CourseDetailListPanel} that displays the given list of courses.
     */
    public CourseDetailListPanel(ObservableList<Course> courseList) {
        super(FXML);
        courseDetailListView.setItems(courseList);
        courseDetailListView.setCellFactory(listView -> new CourseDetailListViewCell());
    }

    class CourseDetailListViewCell extends ListCell<Course> {
        @Override
        protected void updateItem(Course course, boolean empty) {
            super.updateItem(course, empty);

            if (empty || course == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CourseDetailCard(course, getIndex() + 1).getRoot());
            }
        }
    }
}
