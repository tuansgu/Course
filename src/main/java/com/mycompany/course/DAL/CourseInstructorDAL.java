/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.DAL;

import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.CourseInstructorDTO;
import com.mycompany.course.DTO.PersonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class CourseInstructorDAL {
    MyConnection data = new MyConnection();

    public ArrayList<CourseInstructorDTO> getCourseInstructor() {
        ArrayList<CourseInstructorDTO> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MyConnection.connect();
            String sql = "SELECT * FROM CourseInstructor";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CourseInstructorDTO dto = new CourseInstructorDTO();
                dto.setCourseID(rs.getInt("CourseID"));
                dto.setPersonID(rs.getInt("PersonID"));
                list.add(dto);
            }

        } catch (Exception e) {
            return null;
        } finally {
            MyConnection.close(conn);
        }
        return list;
    }

    public PersonDTO getPersonByPersonID(int personID) {
        PersonDTO person = null;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT * FROM Person WHERE PersonID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, personID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                person = new PersonDTO();
                person.setPersonID(rs.getInt("PersonID"));
                person.setLastName(rs.getString("LastName"));
                person.setFirstName(rs.getString("FirstName"));
                person.setHireDate(rs.getDate("HireDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return person;
    }
    
    public CourseDTO getCourseByID(int courseID) {
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
    
    public int[] getAllPersonId() {
        int[] personId = new int[0];
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT PersonID FROM Person WHERE EnrollmentDate IS null;";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            ArrayList<Integer> personIdList = new ArrayList<>(); 

            while (rs.next()) {
                personIdList.add(rs.getInt("PersonID")); 
            }

            personId = new int[personIdList.size()];
            for (int i = 0; i < personIdList.size(); i++) {
                personId[i] = personIdList.get(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return personId;
    }
    
    public int insertCourseInstructor(CourseInstructorDTO dto) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "INSERT INTO CourseInstructor (CourseID,PersonID)"
                    + "VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, dto.getCourseID());
            st.setInt(2, dto.getPersonID());
            result = st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }
    
    public int updateCourseInstructor(CourseInstructorDTO dto) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "UPDATE CourseInstructor SET CourseID = ?, PersonID = ? WHERE CourseID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, dto.getCourseID()); 
            st.setInt(2, dto.getPersonID()); 
            st.setInt(3, dto.getCourseID()); 
            result = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }
    
    public int deleteCourseInstructor(int CourseID) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "DELETE FROM CourseInstructor WHERE CourseID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, CourseID); 
            result = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }
    
    public ArrayList<CourseInstructorDTO> findCoursesInstructorByTitle(String value) {
        ArrayList<CourseInstructorDTO> courseInstructor = new ArrayList<>();
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT CI.CourseID, CI.PersonID\n" +
            "FROM CourseInstructor AS CI\n" +
            "LEFT JOIN Course ON Course.CourseID = CI.CourseID\n" +
            "LEFT JOIN Person ON Person.PersonID = CI.PersonID\n" +
            "WHERE Course.Title LIKE ? OR Person.Firstname LIKE ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "%" + value + "%");
            st.setString(2, "%" + value + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CourseInstructorDTO courseInstrutorDTO = new CourseInstructorDTO();
                courseInstrutorDTO.setCourseID(rs.getInt("CourseID"));
                courseInstrutorDTO.setPersonID(rs.getInt("PersonID"));
                courseInstructor.add(courseInstrutorDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return courseInstructor;
    }
    
    public PersonDTO getNamePersonById(int PersonId) {
        PersonDTO person = null;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT Firstname, Lastname FROM Person WHERE PersonID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, PersonId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                person = new PersonDTO();
                person.setLastName(rs.getString("LastName"));
                person.setFirstName(rs.getString("FirstName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return person;
    }
    
    public int checkCourseActive(int CourseId) {
        int result = -1;
        Connection con = null;
        try {
            con = MyConnection.connect();
            String sql = "SELECT Grade FROM `StudentGrade` WHERE CourseID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, CourseId);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                result = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(con);
        }
        return result;
    }
}
