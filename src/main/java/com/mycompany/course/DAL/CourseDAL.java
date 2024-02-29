/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.DAL;

import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.OnLineCourseDTO;
import com.mycompany.course.DTO.OnSiteCourseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
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
            String sql = "SELECT * FROM Course \n"
                    + "LEFT JOIN onsitecourse ON course.CourseID = onsitecourse.CourseID\n"
                    + "LEFT JOIN onlinecourse ON course.CourseID = onlinecourse.CourseID";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CourseDTO dto;
                if (rs.getString("url") != null) {
                    int CourseID = rs.getInt("CourseID");
                    String Title = rs.getString("Title");
                    int Credits = rs.getInt("Credits");
                    int DepartmentId = rs.getInt("DepartmentId");
                    String url = rs.getString("url");
                    dto = new OnLineCourseDTO(CourseID, Title, Credits, DepartmentId, url);
                } else {
                    int CourseID = rs.getInt("CourseID");
                    String Title = rs.getString("Title");
                    int Credits = rs.getInt("Credits");
                    int DepartmentId = rs.getInt("DepartmentId");
                    String Location = rs.getString("Location");
                    String Days = rs.getString("Days");
                    Time time = rs.getTime("Time");
                    dto = new OnSiteCourseDTO(CourseID, Title, Credits, DepartmentId, Location, Days, time);
                }

                list.add(dto);
            }

        } catch (Exception e) {
            return null;
        } finally {
            MyConnection.close(conn);
        }
        return list;
    }

    public boolean updateCourse(CourseDTO dto) {
        boolean result = false;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "UPDATE course SET Title=?,Credits=?,DepartmentID=? WHERE CourseID=? ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, dto.getTitle());
            st.setInt(2, dto.getCredits());
            st.setInt(3, dto.getDepartmentID());
            st.setInt(4, dto.getCourseID());
            result = st.execute();

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

    public ArrayList<CourseDTO> getCourseByID(int id) {
        ArrayList<CourseDTO> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MyConnection.connect();
            String sql = "SELECT * FROM Course \n"
                    + "LEFT JOIN onsitecourse ON course.CourseID = onsitecourse.CourseID\n"
                    + "LEFT JOIN onlinecourse ON course.CourseID = onlinecourse.CourseID\n"
                    + "Where course.CourseID=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CourseDTO dto;
                if (rs.getString("url") != null) {
                    int CourseID = rs.getInt("CourseID");
                    String Title = rs.getString("Title");
                    int Credits = rs.getInt("Credits");
                    int DepartmentId = rs.getInt("DepartmentId");
                    String url = rs.getString("url");
                    dto = new OnLineCourseDTO(CourseID, Title, Credits, DepartmentId, url);
                } else {
                    int CourseID = rs.getInt("CourseID");
                    String Title = rs.getString("Title");
                    int Credits = rs.getInt("Credits");
                    int DepartmentId = rs.getInt("DepartmentId");
                    String Location = rs.getString("Location");
                    String Days = rs.getString("Days");
                    Time time = rs.getTime("Time");
                    dto = new OnSiteCourseDTO(CourseID, Title, Credits, DepartmentId, Location, Days, time);
                }

                list.add(dto);
            }

        } catch (Exception e) {
            return null;
        } finally {
            MyConnection.close(conn);
        }
        return list;
    }

    // lấy ra khóa học không được phân công 
    // dựa vào id
    public CourseDTO getCourseNotInstructorByID(int id) {
        Connection conn = null;
        try { 
            conn = MyConnection.connect();
            String sql = "SELECT * FROM course\n"
                    + "LEFT JOIN onsitecourse ON course.CourseID = onsitecourse.CourseID\n"
                    + "LEFT JOIN onlinecourse ON course.CourseID = onlinecourse.CourseID\n"
                    + "LEFT JOIN courseinstructor ON courseinstructor.CourseID=course.CourseID\n"
                    + "WHERE course.CourseID=? AND courseinstructor.CourseID IS null";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CourseDTO dto;
                if (rs.getString("url") != null) {
                    int CourseID = rs.getInt("CourseID");
                    String Title = rs.getString("Title");
                    int Credits = rs.getInt("Credits");
                    int DepartmentId = rs.getInt("DepartmentId");
                    String url = rs.getString("url");
                    dto = new OnLineCourseDTO(CourseID, Title, Credits, DepartmentId, url);
                } else {
                    int CourseID = rs.getInt("CourseID");
                    String Title = rs.getString("Title");
                    int Credits = rs.getInt("Credits");
                    int DepartmentId = rs.getInt("DepartmentId");
                    String Location = rs.getString("Location");
                    String Days = rs.getString("Days");
                    Time time = rs.getTime("Time");
                    dto = new OnSiteCourseDTO(CourseID, Title, Credits, DepartmentId, Location, Days, time);
                }

                return dto;
            }

        } catch (Exception e) {
            return null;
        } finally {
            MyConnection.close(conn);
        }
        return null;
    }
    
    public String getTitleById(int Id) {
        String title = "";
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT Title FROM Course WHERE CourseID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, Id); // Set the CourseID parameter
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                title = rs.getString("Title");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return title;
    }
    
    public String getCourseTypeById(int Id) {
        String courseType = ""; 
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT \n" +
            "    CASE \n" +
            "        WHEN EXISTS (SELECT 1 FROM OnlineCourse WHERE CourseID = ?) \n" +
            "        THEN 'OnlineCourse'\n" +
            "        ELSE 'OnsiteCourse' \n" +
            "    END AS CourseType;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, Id); // Set the CourseID parameter
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                courseType = rs.getString("CourseType");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return courseType;
    }
    
    public int[] getAllCourseId() {
        int[] courseId = new int[0];
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT * FROM course\n" +
            "LEFT JOIN onsitecourse ON course.CourseID = onsitecourse.CourseID\n" +
            "LEFT JOIN onlinecourse ON course.CourseID = onlinecourse.CourseID\n" +
            "LEFT JOIN courseinstructor ON courseinstructor.CourseID=course.CourseID\n" +
            "WHERE courseinstructor.CourseID IS null";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            ArrayList<Integer> courseIdList = new ArrayList<>(); 

            while (rs.next()) {
                courseIdList.add(rs.getInt("CourseID")); 
            }

            courseId = new int[courseIdList.size()];
            for (int i = 0; i < courseIdList.size(); i++) {
                courseId[i] = courseIdList.get(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return courseId;
    }
}
