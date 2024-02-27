package com.mycompany.course.DTO;

import java.sql.Time;

public class OnSiteCourseDTO extends CourseDTO
{
    private String location;
    private String days;
    private Time Time;
    
    public OnSiteCourseDTO()
    {
        // none paremeters
    }

    public OnSiteCourseDTO(int courseID, String title, int credits, int departmentID, String location, String days, Time Time) 
    {
        super( courseID,  title, credits,  departmentID);
        this.location = location;
        this.days = days;
        this.Time = Time;
    }
    
 

    public String getLocation() 
    {
        return location;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getDays() 
    {
        return days;
    }

    public void setDays(String days) 
    {
        this.days = days;
    }

    public Time getTime() 
    {
        return Time;
    }

    public void setTime(Time Time) 
    {
        this.Time = Time;
    }
}
