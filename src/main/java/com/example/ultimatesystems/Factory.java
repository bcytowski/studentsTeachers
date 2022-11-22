package com.example.ultimatesystems;

import com.example.ultimatesystems.dto.StudentDto;
import com.example.ultimatesystems.dto.TeacherDto;
import com.example.ultimatesystems.service.StudentsService;
import com.example.ultimatesystems.service.TeachersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class Factory implements CommandLineRunner {

    private final StudentsService studentsService;
    private final TeachersService teachersService;

    public Factory(StudentsService studentsService, TeachersService teachersService) {
        this.studentsService = studentsService;
        this.teachersService = teachersService;
    }

    @Override
    public void run(String... args) throws Exception {
        creatingStudents();
        creatingTeachers();
    }

    public void creatingStudents() {
        List<StudentDto> studentsList = new ArrayList<>(Arrays.asList(
                new StudentDto("Paweł", "Jabłoniec", 30, "jabloniec.pawel90@gmail.com", "Information Technology", new HashSet<>()),
                new StudentDto("Kasia", "Kowalska", 25, "kasiakowalska@gmail.com", "law", new HashSet<>()),
                new StudentDto("Ewelina", "Leszko", 28, "ewelinaleszko@gmail.com", "education", new HashSet<>()),
                new StudentDto("Jan", "Kowalski", 32, "jankowalski@gmail.com", "math", new HashSet<>())
                ));
        for (int i = 0; i < studentsList.size(); i++) {
            studentsService.createStudent(studentsList.get(i));
        }
    }

    public void creatingTeachers() {
        List<TeacherDto> teachersList = new ArrayList<>(Arrays.asList(
                new TeacherDto("Marcin", "Marcinowicz", 50, "marcin@o2.pl", "Information Technology", new HashSet<>()),
                new TeacherDto("Karol", "Karolowicz", 46, "karol@o2.pl", "math", new HashSet<>()),
                new TeacherDto("Aleksandra", "Aleksandrowicz", 40, "ola@o2.pl", "education", new HashSet<>()),
                new TeacherDto("Krystyna", "Krystynowicz", 58, "krysia@o2.pl", "education", new HashSet<>())
        ));
        for (int i = 0; i < teachersList.size(); i++) {
            teachersService.createTeacher(teachersList.get(i));

        }
    }
}
