package com.example.school.Service;

import com.example.school.Entity.Student;
import com.example.school.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class StudentService{
    StudentRepository studentRepository;

    public List<Student> findAllStudents()
    {
        List<Student> listStudents = new ArrayList<>();
        studentRepository.findAll().forEach(listStudents::add);
        return listStudents;
    }

    public List<Student> findStudentsByName(String name)
    {
        List<Student> listStudentsName = new ArrayList<>();
        studentRepository.findBynameContaining(name).forEach(listStudentsName :: add);
        return  listStudentsName;
    }

    public Student createNewStudent(Student student)
    {
        return studentRepository.save(student);
    }

    public Student updateStudent(long id, Student student)
    {
        Optional <Student> studentByID = studentRepository.findById(id);
        if(studentByID.isPresent())
        {
            Student studentTmp = studentByID.get();
            studentTmp.setName(student.getName());
            studentTmp.setHometown(student.getHometown());
            studentTmp.setGpa(student.getGpa());
            Student studentUpdate = studentRepository.save(studentTmp);
            return studentUpdate;
        }
        else return null;
    }

    public void deleteAll()
    {
        studentRepository.deleteAll();
    }

    public int deleteByID(Long id)
    {
        Optional <Student> studentByID = studentRepository.findById(id);
        if(studentByID.isPresent())
        {
            studentRepository.deleteById(id);
            return 1;
        }
        else return 0;

    }
}
