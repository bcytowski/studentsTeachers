package com.example.ultimatesystems.dto;

import com.example.ultimatesystems.domain.Teacher;
import lombok.*;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class StudentDto {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String fieldOfStudy;
    private Set<Teacher> teachers = new HashSet<>();
}
