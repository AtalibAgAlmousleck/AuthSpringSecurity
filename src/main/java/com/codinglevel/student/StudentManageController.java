package com.codinglevel.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManageController {

    private static final List<Student> STUDENTS =
            Arrays.asList(
                    new Student(1L, "Mohamed"),
                    new Student(2L, "Hamdi"),
                    new Student(3L, "Aziz"),
                    new Student(4L, "Mark")
            );
    // hasRole('ROLE_') hasAnyRole('ROLE_')
    // hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getStudents() {
        System.out.println("getStudents");
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void registerStudent(@RequestBody Student student) {
        System.out.println("registerStudent");
        System.err.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestBody Student student) {
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s", studentId, student));
    }
}
