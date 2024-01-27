package com.mycompany.course.DTO;

import java.sql.Time;

public class OnSiteCourseDTO 
{
    private int courseID;
    private String location;
    private String days;
    private Time Time;
    
    public OnSiteCourseDTO()
    {
        // none paremeters
    }

    public OnSiteCourseDTO(int courseID, String location, String days, Time Time) 
    {
        this.courseID = courseID;
        this.location = location;
        this.days = days;
        this.Time = Time;
    }
    
    public int getCourseID() 
    {
        return courseID;
    }

    public void setCourseID(int courseID) 
    {
        this.courseID = courseID;
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
