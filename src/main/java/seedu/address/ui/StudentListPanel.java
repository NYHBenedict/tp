package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;

/**
 * Panel containing a table of students (ID, Name, Email).
 */
public class StudentListPanel extends UiPart<Region> {

    private static final String FXML = "StudentListPanel.fxml";

    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;

    public StudentListPanel(ObservableList<Student> studentList) {
        super(FXML);
        studentTableView.setItems(studentList);
        idColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStudentId().value));
        nameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStudentName().fullName));
        emailColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getEmail().map(e -> e.value).orElse("")));
    }
}
