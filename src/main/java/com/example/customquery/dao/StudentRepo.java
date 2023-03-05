package com.example.customquery.dao;

import com.example.customquery.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query(value = "Select * from tbl_student where firstName = :firstName", nativeQuery = true)
    public List<Student> findByFirstName(String firstName);

    @Query(value = "select * from tbl_student where student_id = :studentId", nativeQuery = true)
    public List<Student> getStudentById(int studentId);

    @Query(value = "select * from tbl_student where student_id = 1", nativeQuery = true)
    public List<Student> getAllStudent();

    @Modifying
    @Transactional
    @Query(value = "update tbl_user where student_id = :studentId",
            countQuery = "SELECT count(*) FROM tbl_student", nativeQuery = true)
    public void deleteStudentByStudentId(int studentId);
}
