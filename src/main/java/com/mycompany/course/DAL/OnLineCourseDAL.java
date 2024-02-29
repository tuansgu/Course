/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.DAL;

import com.mycompany.course.BLL.CourseBLL;
import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OnLineCourseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class OnLineCourseDAL {

    CourseBLL coursebll = new CourseBLL();
    public boolean insertCourseOnline(OnLineCourseDTO dto) {
        Connection con = null;
        boolean result=false;
        try {
            con = MyConnection.connect();
            String courseInsertSql = "INSERT INTO Course (Title,Credits,DepartmentID) VALUES (?,?,?)";
            String onlineCourseInsertSql = "INSERT INTO onlinecourse (CourseID, url) VALUES (?, ?)";
            // Thực hiện chèn khóa học và lấy giá trị tự động được tạo cho CourseID
            try (PreparedStatement courseStatement = con.prepareStatement(courseInsertSql, Statement.RETURN_GENERATED_KEYS)) {
                courseStatement.setString(1, dto.getTitle());
                courseStatement.setInt(2, dto.getCredits());
                courseStatement.setInt(3, dto.getDepartmentID());
                int affectedRows = courseStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating course failed, no rows affected.");
                }

                try (ResultSet generatedKeys = courseStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int courseId = generatedKeys.getInt(1);
                        // Thêm dòng mới vào bảng onlinecourse với courseId vừa được tạo
                        try (PreparedStatement onlineCourseStatement = con.prepareStatement(onlineCourseInsertSql)) {
                            onlineCourseStatement.setInt(1, courseId);
                            onlineCourseStatement.setString(2, dto.getUrl());
                            int row= onlineCourseStatement.executeUpdate();
                            if(row>0){
                                result=true;
                            }
                        }
                    } else {
                        throw new SQLException("Creating course failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }

    public boolean updateCourseOnline(OnLineCourseDTO dto) {
        boolean result = false;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "UPDATE onlinecourse SET url=? WHERE CourseID=? ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, dto.getUrl());
            st.setInt(2, dto.getCourseID());
            result = st.execute();
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }
    public int deleteCourseOnline(CourseDTO dto) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "DELETE FROM onlinecourse WHERE CourseID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, dto.getCourseID());
            result = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }
}
