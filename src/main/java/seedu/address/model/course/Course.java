/**
 * Represents a course.
 * @author zow1e
 */

package seedu.address.model.course;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a course identified by its course code.
 */
public class Course {
    private final String courseCode;

    public Course(String courseCode) {
        requireAllNonNull(courseCode);
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }
        return otherCourse != null
                && otherCourse.courseCode.equals(this.courseCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Course && courseCode.equals(((Course) other).courseCode));
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return "[" + courseCode + "]";
    }
}