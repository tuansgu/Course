/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.DAL;

import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.DepartmentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class DepartmentDAL {
    public ArrayList<DepartmentDTO> getAllDepartment() {
        ArrayList<DepartmentDTO> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MyConnection.connect();
            String sql = "SELECT * FROM Department";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                DepartmentDTO dto = new DepartmentDTO();
                dto.setDepartmentID(rs.getInt("DepartmentID"));
                dto.setName(rs.getString("Name"));
                dto.setAdmin(rs.getInt("Administrator"));
                dto.setBudget(rs.getDouble("Budget"));
                dto.setStartDay(rs.getDate("StartDate"));
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
