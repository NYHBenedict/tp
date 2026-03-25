package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.assessment.Assessment;

public class AssessmentCard extends UiPart<Region> {

    private static final String FXML = "AssessmentListCard.fxml";

    public final Assessment assessment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label assessmentName;
    @FXML
    private Label courseCode;
    @FXML
    private Label maxScore;

    public AssessmentCard(Assessment assessment, int displayedIndex) {
        super(FXML);
        this.assessment = assessment;

        id.setText(displayedIndex + ". ");
        assessmentName.setText(assessment.getAssessmentName().toString());
        courseCode.setText(assessment.getCourseCode());
        maxScore.setText("Max Grade: " + assessment.getMaxScore());
    }
}
