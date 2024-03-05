/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.BLL;

import com.mycompany.course.DAL.StudentGradeDAL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.PersonDTO;
import com.mycompany.course.DTO.StudentGradeDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class StudentGradeBLL {

    public static ArrayList<StudentGradeDTO> getAll() throws SQLException {
        return StudentGradeDAL.getInstance().getAll();
    }

    public static CourseDTO getTitle(int id) {
        return StudentGradeDAL.getInstance().getTitle(id);
    }

    public static PersonDTO getName(int id) {
        return StudentGradeDAL.getInstance().getName(id);
    }

    public static boolean addNewStudentGrade(int courseID, int studentID) {
        
        return StudentGradeDAL.getInstance().addNewStudentGrade(courseID, studentID);
    }

    public static boolean updateStudentGrade(StudentGradeDTO studentGradeDTO) {
        return StudentGradeDAL.getInstance().updateStudentGrade(studentGradeDTO);
    }

    public static ArrayList<CourseDTO> getAllCourse() throws SQLException {
        return StudentGradeDAL.getInstance().getAllCourse();
    }

    public static ArrayList<PersonDTO> getAllStudent() throws SQLException {
        return StudentGradeDAL.getInstance().getAllStudent();
    }

    public static CourseDTO getCourseID(String title) {
        return StudentGradeDAL.getInstance().getCourseID(title);
    }

    public static PersonDTO getStudentID(String Firstname) {
        return StudentGradeDAL.getInstance().getStudentID(Firstname);
    }

    public static List findStudentGrade(String z, String txtSearch) throws SQLException {
        List list = new ArrayList();
        list = StudentGradeDAL.getInstance().findStudentGrade(z, txtSearch);
        return list;
    }

    public static List findStudentName(String txtSearch) throws SQLException {
        List list = new ArrayList();
        list = StudentGradeDAL.getInstance().findStudentName(txtSearch);
        return list;
    }

}
