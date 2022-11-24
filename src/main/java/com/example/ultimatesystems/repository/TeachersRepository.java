package com.example.ultimatesystems.repository;

import com.example.ultimatesystems.domain.Student;
import com.example.ultimatesystems.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByIdIn(Collection<Long> ids);

}
