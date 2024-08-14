package com.student.management.service;

import com.student.management.exception.StudentAlreadyExistsException;
import com.student.management.exception.StudentNotFoundException;
import com.student.management.model.Student;
import com.student.management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements StudentServiceInterface{
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail() + "already exists!");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            if (student.getFirstName() != null) {
                st.setFirstName(student.getFirstName());
            }
            if (student.getLastName() != null) {
                st.setLastName(student.getLastName());
            }
            if (student.getEmail() != null) {
                st.setEmail(student.getEmail());
            }
            if (student.getDepartment() != null) {
                st.setDepartment(student.getDepartment());
            }
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry, no student cannot be found with the id : " + id));
    }


    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->
             new StudentNotFoundException("Sorry, Student cannot be found with the id : " + id) );
    }

    @Override
    public void deleteStudent(Long id) {
       if(!studentRepository.existsById(id)){
           throw new StudentNotFoundException("Sorry, Student Not found with the id : " + id);
       }
        studentRepository.deleteById(id);
    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
