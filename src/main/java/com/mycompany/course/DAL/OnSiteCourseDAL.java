package com.mycompany.course.DAL;

import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OnLineCourseDTO;
import com.mycompany.course.DTO.OnSiteCourseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pc
 */
public class OnSiteCourseDAL {

    public boolean insertCourseOnsite(OnSiteCourseDTO dto) {
        Connection con = null;
        boolean result=false;
        try {
            con = MyConnection.connect();
            String courseInsertSql = "INSERT INTO Course (Title,Credits,DepartmentID) VALUES (?,?,?)";
            String onlineCourseInsertSql = "INSERT INTO onsitecourse (CourseID, Location,Days,Time) VALUES (?, ?,?,?)";
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
                            onlineCourseStatement.setString(2, dto.getLocation());
                            onlineCourseStatement.setString(3, dto.getDays());
                            onlineCourseStatement.setTime(4, dto.getTime());
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
    public boolean updateCourseOnsite(OnSiteCourseDTO dto) {
        boolean result = false;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "UPDATE onlinecourse SET Location=?,Days=?,Time=? WHERE CourseID=? ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, dto.getLocation());
            st.setString(2, dto.getDays());
            st.setTime(3, dto.getTime());
            st.setInt(4, dto.getCourseID());
            result = st.execute();
            System.err.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }
    
    public int deleteCourseOnsite(OnSiteCourseDTO dto) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "DELETE FROM onsitecourse WHERE CourseID=?";
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
