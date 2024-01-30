package ru.hogwarts.hogwartsdatastreams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsdatastreams.model.Avatar;
import ru.hogwarts.hogwartsdatastreams.model.Student;
import ru.hogwarts.hogwartsdatastreams.repository.AvatarRepository;
import ru.hogwarts.hogwartsdatastreams.repository.StudentRepository;
import ru.hogwarts.hogwartsdatastreams.exception.BadRequestException;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

//    @Value("${avatars.dir.path}")
//    private String avatarsDir;

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;

    }

    public Student createdStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new BadRequestException("Отсутствует id"));
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public void deleteAllStudents(Student student) {
        studentRepository.deleteAll();
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    public Collection<Student> findStudentByNameIgnoreCase(String name) {
        return studentRepository.findStudentByNameIgnoreCase(name);
    }

    public Collection<Student> findStudentByNameIgnoreCaseIsLike(String like) {
        return studentRepository.findStudentByNameIgnoreCaseIsLike(like);
    }







    public Avatar findAvatar(long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }





}
