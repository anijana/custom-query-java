package com.example.customquery.service;

import com.example.customquery.dao.StudentRepo;
import com.example.customquery.model.Student;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public int saveStudent(Student student) {

        Student studentObj = studentRepo.save(student);
        return studentObj.getStudentId();
    }

    public JSONArray getStudent(String studentId) {

        JSONArray studentArray = new JSONArray();

        if(null != studentId){

            List<Student> studentList= studentRepo.getStudentById(Integer.valueOf(studentId));
            for (Student student:studentList) {
                JSONObject studentObj = setStudent(student);
                studentArray.put(studentObj);
            }
        }else {
            List<Student> studentList = studentRepo.getAllStudent();

            for (Student student : studentList){
                JSONObject studentObj = setStudent(student);
                studentArray.put(studentObj);
            }
        }
        return studentArray;
    }

    private JSONObject setStudent(Student student){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("studentId", student.getStudentId());
        jsonObject.put("firstName", student.getFirstName());
        jsonObject.put("lastName", student.getLastName());
        jsonObject.put("age", student.getAge());
        jsonObject.put("active", student.getActive());

        return jsonObject;
    }

    public void updateStudent(Student newStudent, String studentId) {
        if(studentRepo.findById(Integer.valueOf(studentId)).isPresent()){
            Student student = studentRepo.findById(Integer.valueOf(studentId)).get();
            newStudent.setStudentId(student.getStudentId());
            studentRepo.save(newStudent);
        }
    }


    public void deleteStudentByStudentId(int studentId) {
        studentRepo.deleteStudentByStudentId(studentId);
    }
}
