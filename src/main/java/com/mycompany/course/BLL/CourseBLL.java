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
    public int insertCourse(CourseDTO dto){
        if(dal.insertCourse(dto)>0){
            return 1;
        }
        return -1;
    }
     public int updateCourse(CourseDTO dto){
        if(dal.updateCourse(dto)>0){
            return 1;
        }
        return -1;
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
