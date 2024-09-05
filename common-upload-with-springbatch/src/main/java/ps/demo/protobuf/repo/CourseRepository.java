package ps.demo.protobuf.repo;

import ps.demo.protobuf.model.Course;

import java.util.Map;

public class CourseRepository {
    Map<Integer, Course> courses;

    public CourseRepository (Map<Integer, Course> courses) {
        this.courses = courses;
    }

    public Course getCourse(int id) {
        return courses.get(id);
    }
}
