package com.mycompany.course.DTO;

public class StudentGradeDTO 
{
    private int enrollmentID;
    private int courseID;
    private int studentID;
    private float grade;
    
    public StudentGradeDTO()
    {
        // none parameters
    }

    public StudentGradeDTO(int enrollmentID, int courseID, int studentID, float grade) 
    {
        this.enrollmentID = enrollmentID;
        this.courseID = courseID;
        this.studentID = studentID;
        this.grade = grade;
    }

    public int getEnrollmentID() 
    {
        return enrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) 
    {
        this.enrollmentID = enrollmentID;
    }

    public int getCourseID() 
    {
        return courseID;
    }

    public void setCourseID(int courseID) 
    {
        this.courseID = courseID;
    }

    public int getStudentID() 
    {
        return studentID;
    }

    public void setStudentID(int studentID) 
    {
        this.studentID = studentID;
    }

    public float getGrade() 
    {
        return grade;
    }

    public void setGrade(float grade) 
    {
        this.grade = grade;
    }
    
}
