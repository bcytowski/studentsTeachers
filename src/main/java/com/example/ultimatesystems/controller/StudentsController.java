package com.example.ultimatesystems.controller;

import com.example.ultimatesystems.domain.Student;
import com.example.ultimatesystems.dto.StudentDto;
import com.example.ultimatesystems.service.StudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Students")
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody StudentDto studentDto) {
        return studentsService.createStudent(studentDto);
    }
}
