package co.imp.tech.service;

import co.imp.tech.model.Student;

import java.util.List;

public interface StudentService {
    public Student getStudentWithCourses(Long studentId);
    public List<Student> getStudentsByCourseId(Long studentId);
}
