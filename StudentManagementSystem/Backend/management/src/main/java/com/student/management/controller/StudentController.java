package com.student.management.controller;

import com.student.management.model.Student;
import com.student.management.service.StudentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
   private final StudentServiceInterface studentServiceInterface;

   @GetMapping("/")
   public ResponseEntity<List<Student>> getStudents(){
       return new ResponseEntity<>(studentServiceInterface.getAllStudents(), HttpStatus.FOUND);
   }

   @PostMapping("/addstudent")
    public Student addStudent(@RequestBody Student student){
       return studentServiceInterface.addStudent(student);
   }

   @PatchMapping("/update/{id}")
   public Student updateStudent(@RequestBody Student student,@PathVariable Long id){
       return studentServiceInterface.updateStudent(student,id);
   }

   @DeleteMapping("/delete/{id}")
   public void deleteStudent(@PathVariable Long id){
       studentServiceInterface.deleteStudent(id);
   }

   @GetMapping("/student/{id}")
   public Student getStudentById(@PathVariable Long id){
       return  studentServiceInterface.getStudentById(id);
   }
}
