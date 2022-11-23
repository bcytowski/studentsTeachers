package com.example.ultimatesystems.service;

import com.example.ultimatesystems.domain.Student;
import com.example.ultimatesystems.domain.Teacher;
import com.example.ultimatesystems.dto.StudentDto;
import com.example.ultimatesystems.dto.TeacherDto;
import com.example.ultimatesystems.repository.StudentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = new Student();
        if (isValidateProperly(studentDto)) {
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

    public void addTeacherToStudent(Long studentId, TeacherDto teacherDto) {
        Student student = getStudentById(studentId);
        student.getTeachers().add(Teacher.builder()
                .name(teacherDto.getName())
                .surname(teacherDto.getSurname())
                .age(teacherDto.getAge())
                .email(teacherDto.getEmail())
                .students(teacherDto.getStudents())
                .build());
        studentsRepository.save(student);
    }

    public void updateUser(StudentDto studentDto, Long id) {
        Student student = getStudentById(id);
        if (isValidateProperly(studentDto))
            studentsRepository.save(Student.builder()
                    .name(studentDto.getName())
                    .surname(studentDto.getSurname())
                    .age(studentDto.getAge())
                    .email(studentDto.getEmail())
                    .fieldOfStudy(studentDto.getFieldOfStudy())
                    .teachers(studentDto.getTeachers())
                    .build());
    }

    public Student getStudentById(Long id) {
        Student student = studentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Student"));
        return student;
    }


    public List<Student> getAllStudentsSorted() {
        List<Student> students = new ArrayList<>();
        studentsRepository.findAll().forEach(students::add);
        students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
        return students;
    }

    public List<Student> getAllStudentsStronicowanie() {
        List<Student> students = new ArrayList<>();
        studentsRepository.findAll().forEach(students::add);
        return students;
    }

    public List<Student> findStudentsByName(String name) {
        return studentsRepository.findAll().stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsBySurname(String surname) {
        return studentsRepository.findAll().stream()
                .filter(student -> student.getSurname().equals(surname))
                .collect(Collectors.toList());
    }


    public void deleteStudent(Long id) {
        studentsRepository.delete(getStudentById(id));
    }


    private boolean isValidateProperly(StudentDto studentDto) {
        return studentDto.getName().length() > 2 && studentDto.getAge() > 18 && validateEmail(studentDto.getEmail(), "^(.+)@(.+)$");
    }

    private static boolean validateEmail(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
