package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.course.Course;
import seedu.address.model.student.Student;

/**
 * A UI component that displays a course together with its assessments and students.
 */
public class CourseDetailCard extends UiPart<Region> {

    private static final String FXML = "CourseDetailListCard.fxml";

    public final Course course;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label courseCode;
    @FXML
    private Label summary;
    @FXML
    private VBox assessmentsContainer;
    @FXML
    private VBox studentsContainer;

    /**
     * Creates a {@code CourseDetailCard} for the given {@code Course}.
     */
    public CourseDetailCard(Course course, int displayedIndex) {
        super(FXML);
        this.course = course;

        id.setText(displayedIndex + ". ");
        courseCode.setText(course.getCourseCode());
        summary.setText(String.format("Assessments: %d | Students: %d",
                course.getAssessments().size(), course.getStudents().size()));

        populateAssessments();
        populateStudents();
    }

    private void populateAssessments() {
        if (course.getAssessments().isEmpty()) {
            assessmentsContainer.getChildren().add(createDetailLabel("No assessments found."));
            return;
        }

        int index = 1;
        for (Assessment assessment : course.getAssessments()) {
            assessmentsContainer.getChildren().add(createDetailLabel(
                    index + ". " + assessment.getAssessmentName() + " (Max Grade: " + assessment.getMaxScore() + ")"));
            index++;
        }
    }

    private void populateStudents() {
        if (course.getStudents().isEmpty()) {
            studentsContainer.getChildren().add(createDetailLabel("No students found."));
            return;
        }

        int index = 1;
        for (Student student : course.getStudents()) {
            studentsContainer.getChildren().add(createDetailLabel(
                    index + ". " + student.getStudentName() + " (" + student.getStudentId() + ")"));
            index++;
        }
    }

    private Label createDetailLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("cell_small_label");
        label.setWrapText(true);
        return label;
    }
}
