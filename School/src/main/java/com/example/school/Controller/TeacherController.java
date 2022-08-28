package com.example.school.Controller;

import com.example.school.Entity.Student;
import com.example.school.Entity.Teacher;
import com.example.school.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/teachers")
    ResponseEntity<List<Teacher>> getAllTeachers(@RequestParam (required = false) String name)
    {
        List<Teacher> listTeachers = new ArrayList<>();
        if(name == null)
        {
            listTeachers = teacherService.getAllTeacher();
        }
        else
        {
            listTeachers = teacherService.findTeachersByName(name);
        }
        if(listTeachers.size() > 0) return  new ResponseEntity<>(listTeachers, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/teacher")
    ResponseEntity<Teacher> createTeacher(Teacher teacher)
    {
        try {
            Teacher teacherCreate = teacherService.createNewTeacher(teacher);
            return new ResponseEntity<>(teacherCreate, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/teacher/{id}")
    ResponseEntity<Teacher> updateTeacher(@PathVariable long id, Teacher teacher)
    {
        Teacher teacherUpdate = teacherService.updateTeacher(id, teacher);
        if(teacherUpdate == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(teacherUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/teachers")
    ResponseEntity<?> deleteAllTeachers()
    {
        teacherService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/teacher/{id}")
    ResponseEntity<Teacher> deleteTeacherByID(@PathVariable long id)
    {
        int check = teacherService.deleteByID(id);
        if(check == 0)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }
}
