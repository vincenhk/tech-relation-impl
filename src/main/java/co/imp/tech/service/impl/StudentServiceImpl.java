package co.imp.tech.service.impl;

import co.imp.tech.model.Student;
import co.imp.tech.repository.StudentRepository;
import co.imp.tech.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student getStudentWithCourses(Long studentId) {
        return studentRepository.findStudentWithCourses(studentId);
    }

    @Override
    public List<Student> getStudentsByCourseId(Long studentId) {
        return studentRepository.findStudentsByCourseId(studentId);
    }
}
