package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.StudentName;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    private final String studentId;
    private final String studentName;
    private final String email;

    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("studentId") String studentId,
            @JsonProperty("studentName") String studentName,
            @JsonProperty("email") String email) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
    }

    public JsonAdaptedStudent(Student source) {
        studentId = source.getStudentId().value;
        studentName = source.getStudentName().fullName;
        email = source.getEmail().map(Email::toString).orElse(null);
    }

    public Student toModelType() throws IllegalValueException {
        if (studentId == null || !StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        if (studentName == null || !StudentName.isValidStudentName(studentName)) {
            throw new IllegalValueException(StudentName.MESSAGE_CONSTRAINTS);
        }
        java.util.Optional<Email> modelEmail = java.util.Optional.empty();
        if (email != null && !email.isBlank()) {
            if (!Email.isValidEmail(email)) {
                throw new IllegalValueException("Invalid email in storage");
            }
            modelEmail = java.util.Optional.of(new Email(email));
        }
        return new Student(new StudentId(studentId), new StudentName(studentName), modelEmail);
    }
}
