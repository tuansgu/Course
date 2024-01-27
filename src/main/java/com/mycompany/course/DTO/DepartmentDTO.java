package com.mycompany.course.DTO;

import java.sql.Date;

public class DepartmentDTO 
{
    private int departmentID;
    private String name;
    private double Budget;
    private Date startDay;
    private int Admin;

    public DepartmentDTO()
    {
        // none parameters
    }
    
    public DepartmentDTO(int departmentID, String name, double Budget, Date startDay, int Admin) 
    {
        this.departmentID = departmentID;
        this.name = name;
        this.Budget = Budget;
        this.startDay = startDay;
        this.Admin = Admin;
    }

    public int getDepartmentID() 
    {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) 
    {
        this.departmentID = departmentID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public double getBudget() 
    {
        return Budget;
    }

    public void setBudget(double Budget) 
    {
        this.Budget = Budget;
    }

    public Date getStartDay() 
    {
        return startDay;
    }

    public void setStartDay(Date startDay) 
    {
        this.startDay = startDay;
    }

    public int getAdmin() 
    {
        return Admin;
    }

    public void setAdmin(int Admin) 
    {
        this.Admin = Admin;
    }
}

