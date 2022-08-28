package com.example.springbootdemo.Repository;

import com.example.springbootdemo.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findBynameContaining(String name);
}
