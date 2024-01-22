package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> filterColor(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Faculty findByName(String name) {
        return facultyRepository.findFirstFacultyByNameIgnoreCase(name);
    }

    @Override
    public Faculty findByColor(String color) {
        return facultyRepository.findFirstFacultyByColorIgnoreCase(color);
    }

    @Override
    public List<Faculty> findByColorList(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    @Override
    public Collection<Student> getStudentsOfFaculty(long id) {
        Faculty faculty = facultyRepository.findFacultyById(id);
        return (faculty != null) ? faculty.getStudents() : Collections.emptyList();
    }
}