package com.mycompany.course.DTO;

public class CourseInstructorDTO 
{
    private int courseID;
    private int personID;
    
    public CourseInstructorDTO()
    {
        // none parameters
    }

    public CourseInstructorDTO(int courseID, int personID) 
    {
        this.courseID = courseID;
        this.personID = personID;
    }

    public int getCourseID() 
    {
        return courseID;
    }

    public void setCourseID(int courseID) 
    {
        this.courseID = courseID;
    }

    public int getPersonID() 
    {
        return personID;
    }

    public void setPersonID(int personID) 
    {
        this.personID = personID;
    }
    
}
