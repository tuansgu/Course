package com.mycompany.course.BLL;

import com.mycompany.course.DAL.CourseDAL;
import com.mycompany.course.DTO.CourseDTO;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class CourseBLL {
    CourseDAL dal=new CourseDAL();
    public ArrayList<CourseDTO> getAllCourses(){
        return dal.getAllCourse();
    }
    public ArrayList<CourseDTO> getCourseById(int id){
        return dal.getCourseByID(id);
    }
    
     public boolean updateCourse(CourseDTO dto){
        return dal.updateCourse(dto);
    }
     public CourseDTO getCourseNotInstructorByID(int id){
         return dal.getCourseNotInstructorByID(id);
     }
     public int deleteCourse(CourseDTO dto){
        if(dal.deleteCourse(dto)>0){
            return 1;
        }
        return -1;
    }
     public CourseDTO searchCourseById(int courseID){
         return dal.findCourseByID(courseID);
     }
     public ArrayList<CourseDTO> searchCourseByTitle(String Title){
         return dal.findCoursesByTitle(Title);
     }
}
