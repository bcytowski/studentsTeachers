package com.example.ultimatesystems.controller;

import com.example.ultimatesystems.domain.Student;
import com.example.ultimatesystems.domain.Teacher;
import com.example.ultimatesystems.dto.TeacherDto;
import com.example.ultimatesystems.service.TeachersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/teachers")
public class TeacherController {

    private final TeachersService teachersService;

    public TeacherController(final TeachersService teachersService) {
        this.teachersService = teachersService;
    }

    @GetMapping(path = "/getAllSorted")
    @ResponseStatus(HttpStatus.OK)
    public List<Teacher> getAllTeachers(){
        return teachersService.getAllTeachersSorted();
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeacher(@RequestBody TeacherDto dto){
        teachersService.createTeacher(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeacher(@PathVariable Long id){
        teachersService.deleteTeacher(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeacher (@PathVariable Long id, @RequestBody TeacherDto dto){
        teachersService.updateTeacher(id, dto);
    }


}

