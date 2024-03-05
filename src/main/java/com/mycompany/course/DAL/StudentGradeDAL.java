package com.mycompany.course.DAL;

import com.mycompany.course.DTO.CourseDTO;
import com.mycompany.course.DTO.PersonDTO;
import com.mycompany.course.DTO.StudentGradeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public class StudentGradeDAL {
    
    public static StudentGradeDAL getInstance() {
        return new StudentGradeDAL();
    }
    
    public ArrayList<StudentGradeDTO> getAll() throws SQLException {
        ArrayList<StudentGradeDTO> studentGradeList = new ArrayList<StudentGradeDTO>();
        Connection conn = MyConnection.connect();
        String query = "SELECT * FROM StudentGrade\n"
                + "LEFT JOIN person ON studentgrade.StudentID = person.PersonID\n"
                + "LEFT JOIN course ON studentgrade.CourseID = course.CourseID\n"
                + "WHERE person.EnrollmentDate > 0";
        
        PreparedStatement pst = conn.prepareStatement(query);
        
        ResultSet rs = pst.executeQuery(query);
        
        while (rs.next()) {
            
            int inrollmentID = rs.getInt("EnrollmentID");
            int courseID = rs.getInt("CourseID");
            int studentID = rs.getInt("StudentID");
            float grade = rs.getFloat("Grade");
            StudentGradeDTO studentGradeDTO = new StudentGradeDTO(inrollmentID, courseID, studentID, grade);
            studentGradeList.add(studentGradeDTO);
        }
        MyConnection.close(conn);
        return studentGradeList;
    }
    
    public CourseDTO getTitle(int id) {
        CourseDTO result = null;
        PreparedStatement stm;
        
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(id);
        String query = "SELECT * FROM course WHERE CourseID = ?";
        
        try {
            
            Connection conn = MyConnection.connect();
            ResultSet rs = null;
            
            stm = conn.prepareStatement(query);
            if (arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    stm.setObject(i + 1, arr.get(i));
                }
            }
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                result = (new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"),
                        rs.getInt("Credits"), rs.getInt("DepartmentID")));
            }
            MyConnection.close(conn);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return result;
    }
    
    public PersonDTO getName(int id) {
        PersonDTO result = null;
        PreparedStatement stm;
        
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(id);
        String query = "SELECT * FROM `person` WHERE PersonID = ?";
        
        try {
            
            Connection conn = MyConnection.connect();
            ResultSet rs = null;
            
            stm = conn.prepareStatement(query);
            if (arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    stm.setObject(i + 1, arr.get(i));
                }
            }
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                result = (new PersonDTO(rs.getInt("PersonID"), rs.getString("Lastname"),
                        rs.getString("Firstname"), rs.getDate("HireDate"), rs.getDate("EnrollmentDate")));
            }
            MyConnection.close(conn);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return result;
    }
    
    public List findStudentGrade(String x, String txtSearch) throws SQLException {
        List list = new ArrayList();
        Connection conn = MyConnection.connect();
        String query = "SELECT * FROM StudentGrade WHERE concat(" + x + ") LIKE '%" + txtSearch + "%'";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery(query);
        
        if (rs != null) {
            while (rs.next()) {
                int inrollmentID = rs.getInt("EnrollmentID");
                int courseID = rs.getInt("CourseID");
                int studentID = rs.getInt("StudentID");
                float grade = rs.getFloat("Grade");
                StudentGradeDTO studentGradeDTO = new StudentGradeDTO(inrollmentID, courseID, studentID, grade);
                list.add(studentGradeDTO);
            }
        }
        
        return list;
    }
    
    public List findStudentName(String txtSearch) throws SQLException {
        List list = new ArrayList();
        Connection conn = MyConnection.connect();
//        String query = "SELECT * FROM StudentGrade WHERE concat(Firstname , ' ', Lastname) LIKE '%" + txtSearch + "%'";
        String query = "SELECT * FROM StudentGrade \n"
                + "JOIN person on person.PersonID = studentgrade.StudentID \n"
                + "WHERE concat(person.Lastname , ' ', person.Firstname) LIKE '%" + txtSearch + "%'";
        
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery(query);
        
        if (rs != null) {
            while (rs.next()) {
                int inrollmentID = rs.getInt("EnrollmentID");
                int courseID = rs.getInt("CourseID");
                int studentID = rs.getInt("StudentID");
                float grade = rs.getFloat("Grade");
                StudentGradeDTO studentGradeDTO = new StudentGradeDTO(inrollmentID, courseID, studentID, grade);
                list.add(studentGradeDTO);
            }
        }
        
        return list;
    }

//    public PersonDTO getStudentID(int id) {
//        PersonDTO result = null;
//        PreparedStatement stm;
//
//        ArrayList<Object> arr = new ArrayList<>();
//        arr.add(id);
//        String query = "SELECT * FROM person WHERE EnrollmentDate > 0";
//
//        try {
//
//            Connection conn = MyConnection.connect();
//            ResultSet rs = null;
//
//            stm = conn.prepareStatement(query);
//            if (arr.size() > 0) {
//                for (int i = 0; i < arr.size(); i++) {
//                    stm.setObject(i + 1, arr.get(i));
//                }
//            }
//
//            rs = stm.executeQuery();
//
//            if (rs.next()) {
//                result = (new PersonDTO(rs.getInt("PersonID"), rs.getString("Lastname"),
//                        rs.getString("Firstname"), rs.getDate("HireDate"), rs.getDate("EnrollmentDate")));
//            }
//            MyConnection.close(conn);
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//
//        return result;
//    }
    public ArrayList<CourseDTO> getAllCourse() throws SQLException {
        ArrayList<CourseDTO> courseList = new ArrayList<CourseDTO>();
        Connection conn = MyConnection.connect();
        String query = "SELECT * FROM `course`";
        
        PreparedStatement pst = conn.prepareStatement(query);
        
        ResultSet rs = pst.executeQuery(query);
        
        while (rs.next()) {
            
            int courseID = rs.getInt("CourseID");
            String title = rs.getString("Title");
            int credits = rs.getInt("Credits");
            int departmentID = rs.getInt("DepartmentID");
            
            CourseDTO courseDTO = new CourseDTO(courseID, title, credits, departmentID);
            courseList.add(courseDTO);
        }
        MyConnection.close(conn);
        return courseList;
    }
    
    public ArrayList<PersonDTO> getAllStudent() throws SQLException {
        ArrayList<PersonDTO> studentList = new ArrayList<PersonDTO>();
        Connection conn = MyConnection.connect();
        String query = "SELECT * FROM person WHERE EnrollmentDate > 0";
        
        PreparedStatement pst = conn.prepareStatement(query);
        
        ResultSet rs = pst.executeQuery(query);
        
        while (rs.next()) {
            
            int personID = rs.getInt("PersonID");
            String lastname = rs.getString("Lastname");
            String firstname = rs.getString("Firstname");
            Date hireDate = rs.getDate("HireDate");
            Date enrollmentDate = rs.getDate("EnrollmentDate");
            
            PersonDTO student = new PersonDTO(personID, lastname, firstname, hireDate, enrollmentDate);
            studentList.add(student);
        }
        MyConnection.close(conn);
        return studentList;
    }
    
    public CourseDTO getCourseID(String title) {
        CourseDTO result = null;
        PreparedStatement stm;
        
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(title);
        String query = "SELECT * FROM course WHERE Title = ?";
        
        try {
            
            Connection conn = MyConnection.connect();
            ResultSet rs = null;
            
            stm = conn.prepareStatement(query);
            if (arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    stm.setObject(i + 1, arr.get(i));
                }
            }
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                result = (new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"),
                        rs.getInt("Credits"), rs.getInt("DepartmentID")));
            }
            MyConnection.close(conn);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return result;
    }
    
    public PersonDTO getStudentID(String Firstname) {
        PersonDTO result = null;
        PreparedStatement stm;
        
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(Firstname);
        String query = "SELECT * FROM person WHERE Firstname = ?";
        
        try {
            
            Connection conn = MyConnection.connect();
            ResultSet rs = null;
            
            stm = conn.prepareStatement(query);
            if (arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    stm.setObject(i + 1, arr.get(i));
                }
            }
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                result = (new PersonDTO(rs.getInt("PersonID"), rs.getString("Lastname"),
                        rs.getString("Firstname"), rs.getDate("HireDate"), rs.getDate("EnrollmentDate")));
            }
            MyConnection.close(conn);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return result;
    }
    
    public boolean addNewStudentGrade(int courseID, int studentID) {
        boolean result = false;
        try {
//            String query = "INSERT INTO studentgrade(`CourseID`, `StudentID`) VALUES (?,?)";
//                    + " ON DUPLICATE KEY UPDATE CourseID = ?";
            String query = "INSERT INTO studentgrade (CourseID, StudentID) "
                    + "SELECT ?, ? "
                    + "FROM dual "
                    + "WHERE NOT EXISTS ("
                    + "SELECT * FROM studentgrade "
                    + "WHERE CourseID = ? AND studentID = ?"
                    + ")";
            Connection conn = MyConnection.connect();
            PreparedStatement pst = conn.prepareStatement(query);
            
            pst.setInt(1, courseID);
            pst.setInt(2, studentID);
            pst.setInt(3, courseID);
            pst.setInt(4, studentID);
            
            result = pst.executeUpdate() > 0;
            if (result) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data already exists or insertion failed.");
            }
            System.out.println(pst);
            MyConnection.close(conn);
        } catch (SQLException e) {
            System.err.println(e);
            System.err.println("loi them");
        }
        return result;
    }

//    INSERT INTO studentgrade (CourseID, StudentID)
//SELECT 1061, 29
//FROM dual
//WHERE NOT EXISTS (
//    SELECT *
//    FROM studentgrade
//    WHERE courseID = 1061 AND studentID = 29
//);
    public boolean updateStudentGrade(StudentGradeDTO studentGradeDTO) {
        boolean result = false;
        try {
            String query = "UPDATE studentgrade SET `Grade`=? WHERE EnrollmentID =?";
            Connection conn = MyConnection.connect();
            PreparedStatement pst = conn.prepareStatement(query);

//            pst.setInt(1, studentGradeDTO.getCourseID());
//            pst.setInt(2, studentGradeDTO.getStudentID());
            pst.setFloat(1, studentGradeDTO.getGrade());
            pst.setInt(2, studentGradeDTO.getEnrollmentID());
            
            result = pst.executeUpdate() > 0;
            System.out.println(pst);
            MyConnection.close(conn);
        } catch (Exception e) {
            System.err.println(e);
        }
        return result;
    }
    
    public boolean deleteStudentGrade(int id) {
        boolean result = false;
        try {
            String query = "DELETE FROM `studentgrade` WHERE EnrollmentID = ?";
            Connection conn = MyConnection.connect();
            PreparedStatement pst = conn.prepareStatement(query);
            
            result = pst.executeUpdate() > 0;
            System.out.println(pst);
            MyConnection.close(conn);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return result;
    }
    
}
