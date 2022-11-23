package com.example.ultimatesystems.repository;

import com.example.ultimatesystems.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Long> {

}
