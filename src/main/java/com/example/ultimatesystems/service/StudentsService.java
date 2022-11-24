package com.example.ultimatesystems.service;

import com.example.ultimatesystems.domain.Student;
import com.example.ultimatesystems.domain.Teacher;
import com.example.ultimatesystems.dto.StudentDto;
import com.example.ultimatesystems.repository.StudentsRepository;
import com.example.ultimatesystems.repository.TeachersRepository;
import lombok.extern.java.Log;
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
public class StudentsService {

    private final StudentsRepository studentsRepository;
    private final TeachersRepository teachersRepository;

    public StudentsService(StudentsRepository studentsRepository, final TeachersRepository teachersRepository, final TeachersService teachersService) {
        this.studentsRepository = studentsRepository;
        this.teachersRepository = teachersRepository;
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = new Student();

        if (isValidateProperly(studentDto)) {
            Student createdStudent = studentDtoToEntity(studentDto, student);

            studentsRepository.save(createdStudent);

            List<Long> teacherIds = studentDto.getTeachers();
            List<Teacher> allById = teachersRepository.findAllById(teacherIds);
            allById.forEach(teacher -> teacher.addStudent(student));
            allById.forEach(teachersRepository::save);
        }
        return student;
    }

    public void deleteStudent(Long id) {
        studentsRepository.deleteById(id);
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

    public void updateStudent(final Long id, final StudentDto dto) {
        if (isValidateProperly(dto)) {
            Student student = studentsRepository.findById(id)
                    .map(b -> studentDtoToEntity(dto, b))
                    .orElseThrow(() -> new IllegalArgumentException(" "));

            studentsRepository.save(student);

            List<Long> teacherIds = dto.getTeachers();
            List<Teacher> allById = teachersRepository.findAllById(teacherIds);
            allById.forEach(teacher -> teacher.addStudent(student));
            allById.forEach(teachersRepository::save);
        }
    }

    private Student studentDtoToEntity(StudentDto dto, Student student) {

        student.setSurname(dto.getSurname());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        student.setName(dto.getName());
        student.setFieldOfStudy(dto.getFieldOfStudy());
        student.setTeachers(teachersRepository.findByIdIn(dto.getTeachers()));

        return student;
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


    private boolean isValidateProperly(StudentDto studentDto) {
        return studentDto.getName().length() > 2 && studentDto.getAge() > 18 && validateEmail(studentDto.getEmail(), "^(.+)@(.+)$");
    }

    private static boolean validateEmail(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
