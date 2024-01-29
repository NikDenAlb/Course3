package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student addStudent(Student faculty);

    Student findStudent(Long facultyId);

    Student editStudent(Student faculty);

    void deleteStudent(Long facultyId);

    Collection<Student> filterAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Faculty getStudentsFaculty(long id);

    Integer getQt();

    Integer getAverageAge();

    List<Student> getFiveLastStudents();

    List<String> filterStartA();

    Double getAverageAgeStream();

    Integer getSum();

    Integer getSumOpt();

    Integer getSumOpt2();
}