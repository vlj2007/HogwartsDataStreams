package ru.hogwarts.hogwartsdatastreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.hogwartsdatastreams.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
