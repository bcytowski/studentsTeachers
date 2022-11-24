package com.example.ultimatesystems.controller;

import com.example.ultimatesystems.domain.Student;
import com.example.ultimatesystems.dto.StudentDto;
import com.example.ultimatesystems.dto.TeacherDto;
import com.example.ultimatesystems.service.StudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody StudentDto studentDto) {
        return studentsService.createStudent(studentDto);
    }

    @GetMapping(path = "/getAllSorted")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(){
        return studentsService.getAllStudentsSorted();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id){
        studentsService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent (@PathVariable Long id, @RequestBody StudentDto dto){
        studentsService.updateStudent(id, dto);
    }
}
