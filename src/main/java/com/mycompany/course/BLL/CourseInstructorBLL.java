/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.BLL;

import com.mycompany.course.DAL.CourseInstructorDAL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.CourseInstructorDTO;
import com.mycompany.course.DTO.PersonDTO;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class CourseInstructorBLL {
    CourseInstructorDAL courseInstructorDAL = new CourseInstructorDAL();
    
    public ArrayList<CourseInstructorDTO> getCourseInstructor() {
        return courseInstructorDAL.getCourseInstructor();
    }
    
    public PersonDTO getPersonByPersonID(int personID) {
        return courseInstructorDAL.getPersonByPersonID(personID);
    }
    
    public CourseDTO getCourseByID(int courseID) {
        return courseInstructorDAL.getCourseByID(courseID);
    }
    
    public int[] getAllPersonId() {
        return courseInstructorDAL.getAllPersonId();
    }
    public int insertCourseInstructor(CourseInstructorDTO dto) {
        if(courseInstructorDAL.insertCourseInstructor(dto)>0){
            return 1;
        }
        return -1;
    }
    
    public int updateCourseInstructor(CourseInstructorDTO dto) {
        if(courseInstructorDAL.updateCourseInstructor(dto)>0){
            return 1;
        }
        return -1;
    }
    
    public int deleteCourseInstructor(int CourseID) {
        if(courseInstructorDAL.deleteCourseInstructor(CourseID)>0){
            return 1;
        }
        return -1;
    }
    
    public ArrayList<CourseInstructorDTO> findCoursesInstructorByTitle(String title) {
        return courseInstructorDAL.findCoursesInstructorByTitle(title);
    }
    public PersonDTO getNamePersonById(int PersonId) {
        return courseInstructorDAL.getNamePersonById(PersonId);
    }
    public int checkCourseActive(int CourseId) {
        return courseInstructorDAL.checkCourseActive(CourseId);
    }
}
