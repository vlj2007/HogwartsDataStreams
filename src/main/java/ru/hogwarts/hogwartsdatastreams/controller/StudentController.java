package ru.hogwarts.hogwartsdatastreams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.hogwartsdatastreams.model.Student;
import ru.hogwarts.hogwartsdatastreams.service.FacultyService;
import ru.hogwarts.hogwartsdatastreams.service.StudentService;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping //POST localhost:8080/students/1 (для отправки данных на сервер, создаем студента)
    public ResponseEntity<Student> createStudent(Student student) {
        return ResponseEntity.ok(studentService.createdStudent(student));
    }

    @GetMapping("{id}") //http://localhost:8080/students/4 (для получения данных с сервера, выводит студента по id)
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }


    @PutMapping //PUT localhost:8080/students/ (для редактирования данных студента)
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

}
