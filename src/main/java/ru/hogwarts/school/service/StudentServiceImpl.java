package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.debug("Adding Student");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.debug("Finding Student {}", id);
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.debug("Editing Student");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.debug("Deleting Student by id={}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> filterAge(int age) {
        logger.debug("AgeFiltering Students age={}", age);
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.debug("AgeFinding Students from {} to {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getStudentsFaculty(long id) {
        logger.debug("getStudentsFaculty: id = {}", id);
        return studentRepository.findStudentById(id).getFaculty();
    }

    @Override
    public Integer getQt() {
        logger.debug("Getting all students quantity");
        return studentRepository.getQt();
    }

    @Override
    public Integer getAverageAge() {
        logger.debug("Getting all students AverageAge");
        return studentRepository.getAverageAge();
    }

    @Override
    public List<Student> getFiveLastStudents() {
        logger.debug("Getting FiveLastStudents");
        return studentRepository.getFiveLastStudents();
    }
}