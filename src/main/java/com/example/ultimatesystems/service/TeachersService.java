package com.example.ultimatesystems.service;

import com.example.ultimatesystems.domain.Student;
import com.example.ultimatesystems.domain.Teacher;
import com.example.ultimatesystems.dto.StudentDto;
import com.example.ultimatesystems.dto.TeacherDto;
import com.example.ultimatesystems.repository.StudentsRepository;
import com.example.ultimatesystems.repository.TeachersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeachersService {

    private final TeachersRepository teachersRepository;
    private final StudentsRepository studentsRepository;

    public TeachersService(TeachersRepository teachersRepository, final StudentsRepository studentsRepository) {
        this.teachersRepository = teachersRepository;
        this.studentsRepository = studentsRepository;
    }

    public Teacher createTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        if (isValidateProperly(teacherDto)) {
            List<Student> byIdIn = studentsRepository.findByIdIn(teacherDto.getStudents());

           teacher = Teacher.builder()
                    .name(teacherDto.getName())
                    .surname(teacherDto.getSurname())
                    .age(teacherDto.getAge())
                    .email(teacherDto.getEmail())
                    .subject(teacherDto.getSubject())
                    .students(byIdIn)
                    .build();
            teachersRepository.save(teacher);
        }
        return teacher;
    }

    public List<Teacher> getAll(){
        return teachersRepository.findAll();
    }

    public List<Teacher> getAllTeachersSorted() {
        List<Teacher> teachers = new ArrayList<>();
        teachersRepository.findAll().forEach(teachers::add);
        teachers.stream()
                .sorted(Comparator.comparing(Teacher::getName))
                .collect(Collectors.toList());
        return teachers;
    }

    public void deleteTeacher(Long id){
        if(teachersRepository.findById(id).isPresent()){
            teachersRepository.deleteById(id);
        } else throw new IllegalArgumentException("couldn't find specific teacher");
    }

    public void updateTeacher(Long teacherId, TeacherDto dto){
        if(teachersRepository.findById(teacherId).isPresent()) {
            if (isValidateProperly(dto)) {
                Teacher teacher = teachersRepository.findById(teacherId)
                        .map(b -> teacherDtoToEntity(dto, b))
                        .orElseThrow(() -> new IllegalArgumentException(" "));
                teachersRepository.save(teacher);
            }
        } else throw new IllegalArgumentException("couldn't find specific teacher");
    }

    private Teacher teacherDtoToEntity(TeacherDto dto, Teacher teacher){
        teacher.setSurname(dto.getSurname());
        teacher.setAge(dto.getAge());
        teacher.setEmail(dto.getEmail());
        teacher.setName(dto.getName());
        teacher.setSubject(dto.getSubject());
        teacher.setStudents(studentsRepository.findByIdIn(dto.getStudents()));

        return teacher;
    }

    public Teacher getTeacherById(Long id) {
        Teacher teacher = teachersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Student"));
        return teacher;
    }

    private boolean isValidateProperly(TeacherDto teacherDto) {
        return teacherDto.getName().length() > 2 && teacherDto.getAge() > 18 && validateEmail(teacherDto.getEmail(), "^(.+)@(.+)$");
    }

    private static boolean validateEmail(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
