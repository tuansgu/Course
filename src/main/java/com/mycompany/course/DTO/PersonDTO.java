package com.mycompany.course.DTO;

import java.sql.Date;

public class PersonDTO 
{
    private int personID;
    private String lastName;
    private String firstName;
    private Date hireDate;
    private Date enrollmentDate; // ngày đăng kí

    public PersonDTO()
    {
        // none paremeters
    }
    
    public PersonDTO(int personID, String lastName, String firstName, Date hireDate, Date enrollmentDate) {
        this.personID = personID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.hireDate = hireDate;
        this.enrollmentDate = enrollmentDate;
    }

    public int getPersonID() 
    {
        return personID;
    }

    public void setPersonID(int personID) 
    {
        this.personID = personID;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public Date getHireDate() 
    {
        return hireDate;
    }

    public void setHireDate(Date hireDate) 
    {
        this.hireDate = hireDate;
    }

    public Date getEnrollmentDate() 
    {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) 
    {
        this.enrollmentDate = enrollmentDate;
    }
    
}
