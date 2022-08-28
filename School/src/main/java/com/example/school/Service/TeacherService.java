package com.example.school.Service;

import com.example.school.Entity.Student;
import com.example.school.Entity.Teacher;
import com.example.school.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    public List<Teacher> getAllTeacher()
    {
        List<Teacher> listTeachers = new ArrayList<>();
        teacherRepository.findAll().forEach(listTeachers::add);
        return listTeachers;
    }

    public List<Teacher> findTeachersByName(String name)
    {
        List<Teacher> listTeachersByName = new ArrayList<>();
        teacherRepository.findBynameContaining(name).forEach(listTeachersByName :: add);
        return listTeachersByName;
    }

    public Teacher createNewTeacher(Teacher teacher)
    {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(long id, Teacher teacher)
    {
        Optional<Teacher> teacherByID = teacherRepository.findById(id);
        if(teacherByID.isPresent())
        {
            Teacher teacherTmp = teacherByID.get();
            teacherTmp.setName(teacher.getName());
            teacherTmp.setHometown(teacher.getHometown());
            teacherTmp.setGvcn(teacher.isGvcn());
            Teacher teacherUpdate = teacherRepository.save(teacherTmp);
            return teacherUpdate;
        }
        else return null;
    }

    public void deleteAll()
    {
        teacherRepository.deleteAll();
    }

    public int deleteByID(Long id)
    {
        Optional <Teacher> teacherByID = teacherRepository.findById(id);
        if(teacherByID.isPresent())
        {
            teacherRepository.deleteById(id);
            return 1;
        }
        else return 0;

    }
}
