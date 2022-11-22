package com.example.ultimatesystems.service;

import com.example.ultimatesystems.domain.Teacher;
import com.example.ultimatesystems.dto.TeacherDto;
import com.example.ultimatesystems.repository.TeachersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeachersService {

    private final TeachersRepository teachersRepository;

    public TeachersService(TeachersRepository teachersRepository) {
        this.teachersRepository = teachersRepository;
    }

    public Teacher createTeacher(TeacherDto teacherDto) {
        Teacher teacher = Teacher.builder()
                .name(teacherDto.getName())
                .surname(teacherDto.getSurname())
                .age(teacherDto.getAge())
                .email(teacherDto.getEmail())
                .subject(teacherDto.getSubject())
                .students(teacherDto.getStudents())
                .build();
        teachersRepository.save(teacher);
        return teacher;
    }

}
