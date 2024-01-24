package ru.hogwarts.hogwartsdatastreams.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsdatastreams.model.Faculty;
import ru.hogwarts.hogwartsdatastreams.repository.FacultyRepository;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id){
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
}
