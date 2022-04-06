package com.codinglevel.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS =
            Arrays.asList(
                    new Student(1L, "Mohamed"),
                    new Student(2L, "Hamdi"),
                    new Student(3L, "Aziz"),
                    new Student(4L, "Mark")
            );

    @GetMapping(value = "all/{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student not found"));
    }
}
