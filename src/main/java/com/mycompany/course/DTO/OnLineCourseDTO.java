package com.mycompany.course.DTO;

public class OnLineCourseDTO extends CourseDTO
{
    protected String url;
    public OnLineCourseDTO()
    {
        // none parameters
         super();
    }

    public OnLineCourseDTO(int courseID, String title, int credits, int departmentID,String url) {
        super(courseID, title, credits, departmentID);
        this.url = url;
    }
    public String getUrl() 
    {
        return url;
    }

    public void setUrl(String url) 
    {
        this.url = url;
    }
    
}
