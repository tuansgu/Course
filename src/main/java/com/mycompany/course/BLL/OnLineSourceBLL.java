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
public class OnLineSourceBLL {
     OnLineCourseDAL dal=new OnLineCourseDAL();
     public int insertCourseOnline(OnLineCourseDTO dto){
        if(dal.insertCourseOnline(dto)>0){
            return 1;
        }
        return -1;
    }
}
