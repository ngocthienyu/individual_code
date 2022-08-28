package com.example.school.Repository;

import com.example.school.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {
    List<Student> findBynameContaining(String name);
}
