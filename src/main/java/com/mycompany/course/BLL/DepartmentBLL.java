/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.BLL;

import com.mycompany.course.DAL.DepartmentDAL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.DepartmentDTO;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class DepartmentBLL {
    DepartmentDAL dal =new DepartmentDAL();
     public ArrayList<DepartmentDTO> getAllDepartment(){
        return dal.getAllDepartment();
    }
}
