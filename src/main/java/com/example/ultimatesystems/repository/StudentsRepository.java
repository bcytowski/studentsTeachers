package com.example.ultimatesystems.repository;

import com.example.ultimatesystems.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    List<Student> findByIdIn(Collection<Long> ids);
}
