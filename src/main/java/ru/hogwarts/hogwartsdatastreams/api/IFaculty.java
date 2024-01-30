package ru.hogwarts.hogwartsdatastreams.api;

import ru.hogwarts.hogwartsdatastreams.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface IFaculty {

    Faculty createFaculty(Faculty faculty);
    Faculty editFaculty(Faculty faculty);
    Faculty findFaculty(Long id);
    List<Faculty> showAllFaculty();
    List<Faculty> findFacultyByName(String name);
    Collection<Faculty> findFacultyByNameIgnoreCase(String name);
    Collection<Faculty> findFacultyByColorIgnoreCase(String color);
    public void deleteAllFaculty(long id);
    public void deleteAllFaculty(Faculty faculty);

}
