package ps.demo.protobuf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ps.demo.protobuf.model.Course;
import ps.demo.protobuf.repo.CourseRepository;

@RestController
@RequestMapping("/protobuf-course")
public class CourseController {
    @Autowired
    CourseRepository courseRepo;

    @GetMapping("/get/{id}")
    Course customer(@PathVariable Integer id) {
        return courseRepo.getCourse(id);
    }
}