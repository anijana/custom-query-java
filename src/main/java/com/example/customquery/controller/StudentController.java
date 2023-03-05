package com.example.customquery.controller;

import com.example.customquery.dao.StudentRepo;
import com.example.customquery.model.Student;
import com.example.customquery.service.StudentService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class StudentController {

    public class UserController {

        @Autowired
        StudentRepo studentRepo;

        @Autowired
        StudentService studentService;

        @PostMapping(value = "/create-student")
        public ResponseEntity<String> createStudent(@RequestBody String studentData) {

            Student student = setStudent(studentData);
            int studentId = studentService.saveStudent(student);

            return new ResponseEntity("student saved with id "+studentId, HttpStatus.CREATED);
        }


        @GetMapping(value = "/get-student")
        public ResponseEntity<String> getStudent(@Nullable @RequestParam String studentId) {

            JSONArray studentArr = studentService.getStudent(studentId);
            return new ResponseEntity<>(studentArr.toString(), HttpStatus.OK);

        }

        @PutMapping(value = "/update-student/{studentId}")
        public ResponseEntity<String> updateStudent(@PathVariable String studentId, @RequestBody String requestData) {
            Student student = setStudent(requestData);
            studentService.updateStudent(student,studentId);
            return new ResponseEntity("student updated", HttpStatus.OK);
        }


        private Student setStudent(String studentData)  {
            Student student = new Student();
            JSONObject json = new JSONObject(studentData);

            student.setFirstName(json.getString("firstName"));
            student.setLastName(json.getString("lastName"));
            student.setAge(json.getInt("age"));
//            student.setActive(json.getBoolean("active");

            Timestamp createdTime = new Timestamp(System.currentTimeMillis());
            student.setAdmissionDate(createdTime);

            return student;

        }
    }
}
