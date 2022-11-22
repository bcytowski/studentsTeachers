package com.example.ultimatesystems.service;

import com.example.ultimatesystems.domain.Student;
import com.example.ultimatesystems.dto.StudentDto;
import com.example.ultimatesystems.repository.StudentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = new Student();
        if (studentDto.getName().length() > 2 && studentDto.getAge() > 18 && validateEmail(studentDto.getEmail(), "^(.+)@(.+)$")) {
            student = Student.builder()
                    .name(studentDto.getName())
                    .surname(studentDto.getSurname())
                    .age(studentDto.getAge())
                    .email(studentDto.getEmail())
                    .fieldOfStudy(studentDto.getFieldOfStudy())
                    .teachers(studentDto.getTeachers())
                    .build();
            studentsRepository.save(student);
        }
        return student;
    }
//
//    public List<Student> getAllStudents() {
//        return studentsRepository.findAll();
//    }





    private static boolean validateEmail(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
