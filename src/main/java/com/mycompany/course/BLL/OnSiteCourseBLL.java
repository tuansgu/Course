/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.BLL;

import com.mycompany.course.DAL.OnSiteCourseDAL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OnSiteCourseDTO;

/**
 *
 * @author pc
 */
public class OnSiteCourseBLL {
    OnSiteCourseDAL dal=new OnSiteCourseDAL();
     public int insertCourseOnSite(OnSiteCourseDTO dto){
        if(dal.insertCourseOnsite(dto)>0){
            return 1;
        }
        return -1;
    }
}
