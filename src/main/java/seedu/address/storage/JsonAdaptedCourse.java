package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseCode;
import seedu.address.model.student.Student;

/**
 * Jackson-friendly version of {@link Course}.
 */
class JsonAdaptedCourse {

    private final String courseCode;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedCourse(@JsonProperty("courseCode") String courseCode,
            @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.courseCode = courseCode;
        if (students != null) {
            this.students.addAll(students);
        }
    }

    public JsonAdaptedCourse(Course source) {
        courseCode = source.getCourseCode().value;
        students.addAll(source.getStudents().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    public Course toModelType() throws IllegalValueException {
        if (courseCode == null || !CourseCode.isValidCourseCode(courseCode)) {
            throw new IllegalValueException(CourseCode.MESSAGE_CONSTRAINTS);
        }
        Course course = new Course(new CourseCode(courseCode));
        for (JsonAdaptedStudent jsonStudent : students) {
            Student student = jsonStudent.toModelType();
            if (course.getStudents().contains(student)) {
                throw new IllegalValueException("Duplicate student in course: " + student.getStudentId());
            }
            course.getStudents().add(student);
        }
        return course;
    }
}
