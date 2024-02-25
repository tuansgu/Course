/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.DAL;

import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OfficeAssignmentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class OfficeAssignmentDAL {
    public ArrayList<OfficeAssignmentDTO> getAllOfficeAssignment() {
        ArrayList<OfficeAssignmentDTO> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MyConnection.connect();
            String sql = "SELECT * FROM officeassignment";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OfficeAssignmentDTO dto = new OfficeAssignmentDTO();
                dto.setInstructorID(rs.getInt("InstructorID"));
                dto.setLocation(rs.getString("Location"));
                list.add(dto);
            }

        } catch (Exception e) {
            return null;
        } finally {
            MyConnection.close(conn);
        }
        return list;
    }
}
