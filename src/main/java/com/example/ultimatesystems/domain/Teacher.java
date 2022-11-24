package com.example.ultimatesystems.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String subject;

    @JsonIgnoreProperties("teachers")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(
            name = "Students_Teachers",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
    )
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        this.students.add(student);
        student.getTeachers().add(this);
    }

    public void removeStudent(long studentId) {
        Student student = this.students.stream().filter(s -> s.getId() == studentId).findFirst().orElse(null);
        if (student != null) {
            this.students.remove(student);
            student.getTeachers().remove(this);
        }
    }
}
