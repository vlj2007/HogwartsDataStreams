package ru.hogwarts.hogwartsdatastreams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.hogwartsdatastreams.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
