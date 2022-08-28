package com.example.school.Controller;

import com.example.school.Entity.Student;
import com.example.school.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    ResponseEntity<List<Student>> getAllStudent(@RequestParam (required = false) String name) {
        List<Student> studentsList = new ArrayList<>();
        if (name == null) {
            studentsList = studentService.findAllStudents();
            if (studentsList.size() > 0) {
                return new ResponseEntity<>(studentsList, HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            studentsList = studentService.findStudentsByName(name);
            if (studentsList.size() > 0) {
                return new ResponseEntity<>(studentsList, HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/student")
    ResponseEntity<Student> createStudent(@RequestBody Student student)
    {
        try {
            Student studentCreate = studentService.createNewStudent(student);
            return new ResponseEntity<>(studentCreate, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("student/{id}")
    ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student student)
    {
        Student studentUpdate = studentService.updateStudent(id, student);
        if (studentUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
        }

    }

    @DeleteMapping("students")
    ResponseEntity<?> deleteAllStudents()
    {
        studentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("student/{id}")
    ResponseEntity<?> deleteByID(@PathVariable long id)
    {
        int check = studentService.deleteByID(id);
        if(check == 0)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }

}
