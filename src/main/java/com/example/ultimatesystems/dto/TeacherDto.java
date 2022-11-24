package com.example.ultimatesystems.dto;

import com.example.ultimatesystems.domain.Student;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TeacherDto {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String subject;
    private List<Long> students = new ArrayList<>();
}
