package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CooperatorService {

    Boolean CheckNotEmpty(String s) {
        return s != null && !s.equals("") && s.trim().length() > 0;
    }

    @Autowired
    CoursesService coursesService;

    @Autowired
    UserEntityService userEntityService;

    @Transactional
    public Course createCourse(String name) {
        return coursesService.createCourse(name);
    }

    @Transactional
    public List<Course> getAllCourses() {
        return coursesService.getAllCourses();
    }


    @Transactional
    public ProgramManager createProgramManager(String firstName, String lastName, String email, String password) {
        return userEntityService.createProgramManager(firstName,lastName,email,password);
    }


    @Transactional
    public TermInstructor createTermInstructor(String firstName, String lastName, String email, String password) {
        return userEntityService.createTermInstructor(firstName,lastName,email,password);
    }

    @Transactional
    public List<UserEntity> getAllUserEntities() {
        return userEntityService.getAllUserEntities();
    }

    @Transactional
    public UserEntity login(String email, String password) {
        UserEntity user = userEntityService.getUserEntityByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}