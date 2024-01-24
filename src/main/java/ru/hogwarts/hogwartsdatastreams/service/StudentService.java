package ru.hogwarts.hogwartsdatastreams.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsdatastreams.model.Student;
import ru.hogwarts.hogwartsdatastreams.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createdStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id){
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }




}
