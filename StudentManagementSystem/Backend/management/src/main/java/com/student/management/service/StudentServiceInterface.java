package com.student.management.service;

import com.student.management.model.Student;

import java.util.List;

public interface StudentServiceInterface {
    Student addStudent(Student student);

    List<Student> getAllStudents();

    Student updateStudent(Student student, Long id);

    Student getStudentById(Long id);

    void deleteStudent(Long id);
}
