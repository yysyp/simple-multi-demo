package ps.demo.uploadspringbatch.protobuf.repo;

import ps.demo.uploadspringbatch.protobuf.model.Course;

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
