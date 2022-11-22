package com.example.ultimatesystems.repository;

import com.example.ultimatesystems.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<Student, Long> {

}
