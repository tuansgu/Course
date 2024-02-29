/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.BLL;

import com.mycompany.course.DAL.OnLineCourseDAL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OnLineCourseDTO;

/**
 *
 * @author pc
 */
public class OnLineCourseBLL {

    OnLineCourseDAL dal = new OnLineCourseDAL();
    CourseBLL coursebll = new CourseBLL();

    public boolean insertCourseOnline(OnLineCourseDTO dto) {
        return dal.insertCourseOnline(dto);
    }
    // boolean courseupdate = coursebll.updateCourse(dto);


    public boolean updateOnline(OnLineCourseDTO dto) {
        boolean courseupdate = coursebll.updateCourse(dto);
        boolean onlineupdate = dal.updateCourseOnline(dto);
        if (courseupdate == true && onlineupdate == true) {
            return true;
        }
        return false;
    }

    public int deleteCourseOnline(OnLineCourseDTO dto) {
        if (coursebll.deleteCourse(dto) > 0 && dal.deleteCourseOnline(dto) > 0) {
            return 1;
        }
        return -1;
    }
}
