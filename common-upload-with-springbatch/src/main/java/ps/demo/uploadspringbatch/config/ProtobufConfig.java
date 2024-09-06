package ps.demo.uploadspringbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import ps.demo.uploadspringbatch.protobuf.model.Course;
import ps.demo.uploadspringbatch.protobuf.model.Student;
import ps.demo.uploadspringbatch.protobuf.repo.CourseRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProtobufConfig {

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    public CourseRepository createTestCourses() {
        Map<Integer, Course> courses = new HashMap<>();
        Course course1 = Course.newBuilder()
                .setId(1)
                .setCourseName("REST with Spring")
                .addAllStudent(createTestStudents())
                .build();
        Course course2 = Course.newBuilder()
                .setId(2)
                .setCourseName("Learn Spring Security")
                .addAllStudent(new ArrayList<Student>())
                .build();
        courses.put(course1.getId(), course1);
        courses.put(course2.getId(), course2);
        return new CourseRepository(courses);
    }

    public List<Student> createTestStudents() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = Student.newBuilder().setId(i).setFirstName("firstname" + i)
                    .setLastName("lastname" + i).setEmail("email" + i + "@a.com")
                    .build();

//            .setPhone(0, Student.PhoneNumber.newBuilder().setType(Student.PhoneType.MOBILE)
//                    .setNumber("13421541251").build())
            students.add(student);
        }
        return students;
    }

}
