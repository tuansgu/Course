/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.BLL;

import com.mycompany.course.DAL.OnSiteCourseDAL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OnLineCourseDTO;
import com.mycompany.course.DTO.OnSiteCourseDTO;

/**
 *
 * @author pc
 */
public class OnSiteCourseBLL {

    CourseBLL coursebll = new CourseBLL();
    OnSiteCourseDAL dal = new OnSiteCourseDAL();

    public boolean insertCourseOnSite(OnSiteCourseDTO dto) {
        return dal.insertCourseOnsite(dto);
    }

    public boolean updateCourseOnline(OnSiteCourseDTO dto) {
        return dal.updateCourseOnsite(dto);
    }

    public boolean updateOnsite(OnSiteCourseDTO dto) {
        boolean courseupdate = coursebll.updateCourse(dto);
        boolean onsiteupdate = dal.updateCourseOnsite(dto);
        if (courseupdate == true && onsiteupdate == true) {
            return true;
        }
        return false;
    }

    public int deleteCourseOnsite(OnSiteCourseDTO dto) {
        if (coursebll.deleteCourse(dto) > 0 && dal.deleteCourseOnsite(dto)>0) {
            return 1;
        }
        return -1;
    }
}
