package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.person.Email;

public class Student {
    private final StudentId studentId;
    private final StudentName studentName;
    private final Optional<Email> email;

    public Student(StudentId studentId, StudentName studentName) {
        this(studentId, studentName, Optional.empty());
    }

    public Student(StudentId studentId, StudentName studentName, Optional<Email> email) {
        requireAllNonNull(studentId, studentName);
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email != null ? email : Optional.empty();
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public StudentName getStudentName() {
        return studentName;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }
        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Student)) {
            return false;
        }
        Student otherStudent = (Student) other;
        return studentId.equals(otherStudent.studentId)
                && studentName.equals(otherStudent.studentName)
                && email.equals(otherStudent.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, email);
    }

    @Override
    public String toString() {
        return studentId + " " + studentName;
    }
}
