/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.BLL;

import com.mycompany.course.DAL.OfficeAssignmentDAL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OfficeAssignmentDTO;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class OfficeAssignmentBLL {
    OfficeAssignmentDAL dal=new OfficeAssignmentDAL();
     public ArrayList<OfficeAssignmentDTO> getAllOfficeAssignment(){
        return dal.getAllOfficeAssignment();
    }
}
