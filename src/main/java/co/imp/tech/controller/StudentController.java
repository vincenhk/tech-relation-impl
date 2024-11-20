package co.imp.tech.controller;

import co.imp.tech.model.Course;
import co.imp.tech.model.Student;
import co.imp.tech.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}/courses")
    public Student getStudentCourses(@PathVariable Long id) {
        Student student = studentService.getStudentWithCourses(id);
        return student; // Returns the courses the student is enrolled in
    }

    @GetMapping("/{id}/student-list")
    public List<Student> getStudentsByCourseId(@PathVariable Long id) {
        List<Student> student = studentService.getStudentsByCourseId(id);
        return student; // Returns the courses the student is enrolled in
    }
}
