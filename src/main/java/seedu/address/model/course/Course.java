package seedu.address.model.course;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.grade.UniqueGradeList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

public class Course {
    private final CourseCode courseCode;
    private final UniqueStudentList students;
    private final UniqueAssessmentList assessments;
    private final UniqueGradeList grades;

    public Course(CourseCode courseCode) {
        requireAllNonNull(courseCode);
        this.courseCode = courseCode;
        this.students = new UniqueStudentList();
        this.assessments = new UniqueAssessmentList();
        this.grades = new UniqueGradeList();
    }

    public CourseCode getCourseCode() {
        return courseCode;
    }

    public UniqueStudentList getStudents() {
        return students;
    }

    public UniqueAssessmentList getAssessments() {
        return assessments;
    }

    public UniqueGradeList getGrades() {
        return grades;
    }

    /**
     * Removes the student with the given ID and all their grades in this course.
     * @return true if the student was found and removed
     */
    public boolean removeStudent(seedu.address.model.student.StudentId studentId) {
        Optional<Student> toRemove = students.getByStudentId(studentId);
        if (toRemove.isEmpty()) {
            return false;
        }
        students.remove(toRemove.get());
        grades.removeGradesForStudent(studentId);
        return true;
    }

    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }
        return otherCourse != null
                && otherCourse.getCourseCode().equals(getCourseCode());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Course)) return false;
        Course otherCourse = (Course) other;
        return courseCode.equals(otherCourse.courseCode)
                && students.asUnmodifiableObservableList().equals(otherCourse.students.asUnmodifiableObservableList())
                && assessments.asUnmodifiableObservableList().equals(otherCourse.assessments.asUnmodifiableObservableList())
                && grades.asUnmodifiableObservableList().equals(otherCourse.grades.asUnmodifiableObservableList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, students, assessments, grades);
    }
}
