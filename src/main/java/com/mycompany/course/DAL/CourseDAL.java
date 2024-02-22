/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.DAL;

import com.mycompany.course.DTO.CourseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class CourseDAL {
    MyConnection data = new MyConnection();

    public ArrayList<CourseDTO> getAllCourse() {
        ArrayList<CourseDTO> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MyConnection.connect();
            String sql = "SELECT * FROM Course";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CourseDTO dto = new CourseDTO();
                dto.setCourseID(rs.getInt("CourseID"));
                dto.setTitle(rs.getString("Title"));
                dto.setCredits(rs.getInt("Credits"));
                dto.setDepartmentID(rs.getInt("DepartmentID"));
                list.add(dto);
            }

        } catch (Exception e) {
            return null;
        } finally {
            MyConnection.close(conn);
        }
        return list;
    }
    
    public int insertCourse(CourseDTO dto) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "INSERT INTO Course (CourseID,Title,Credits,DepartmentID)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
             st.setInt(1, dto.getCourseID());
            st.setString(2, dto.getTitle());
            st.setInt(3, dto.getCredits());
            st.setInt(4, dto.getDepartmentID());
            result = st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }

    public int updateCourse(CourseDTO dto) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "UPDATE Course SET Title=?,Credits=?,DepartmentID=? WHERE CourseID=? ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, dto.getTitle());
            st.setInt(2, dto.getCredits());
            st.setInt(3, dto.getDepartmentID());
            st.setInt(4, dto.getCourseID());
            result = st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }

    public int deleteCourse(CourseDTO dto) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "DELETE FROM Course WHERE CourseID=?";
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
    public CourseDTO findCourseByID(int courseID) {
        CourseDTO course = null;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT * FROM Course WHERE CourseID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, courseID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                course = new CourseDTO();
                course.setCourseID(rs.getInt("CourseID"));
                course.setTitle(rs.getString("Title"));
                course.setCredits(rs.getInt("Credits"));
                course.setDepartmentID(rs.getInt("DepartmentID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return course;
    }

    public ArrayList<CourseDTO> findCoursesByTitle(String title) {
        ArrayList<CourseDTO> courses = new ArrayList<>();
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT * FROM Course WHERE Title LIKE ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + title + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CourseDTO course = new CourseDTO();
                course.setCourseID(rs.getInt("CourseID"));
                course.setTitle(rs.getString("Title"));
                course.setCredits(rs.getInt("Credits"));
                course.setDepartmentID(rs.getInt("DepartmentID"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return courses;
    }
}
