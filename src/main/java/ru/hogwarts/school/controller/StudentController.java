package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ageFilter/{age}")
    public Collection<Student> ageFilteredStudents(@PathVariable int age) {
        return studentService.filterAge(age);
    }

    @GetMapping("/ageBetween/{min}&{max}")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@PathVariable int min, @PathVariable int max) {
        Collection<Student> students = studentService.findByAgeBetween(min, max);
        if (students.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/studentsFaculty/{id}")
    public ResponseEntity<Faculty> getStudentsFaculty(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getStudentsFaculty(id));
    }

    @GetMapping("/studentsQt")
    public ResponseEntity<Integer> getQt() {
        return ResponseEntity.ok(studentService.getQt());
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Integer> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/fiveLastStudents")
    public ResponseEntity<List<Student>> getLastFiveStudents() {
        List<Student> lastFiveStudents = studentService.getFiveLastStudents();
        if (lastFiveStudents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lastFiveStudents);
    }
}