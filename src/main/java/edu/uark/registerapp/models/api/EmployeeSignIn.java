package edu.uark.registerapp.models.api;

public class EmployeeSignIn
{
    private String employeeId;
    private String password;
    public EmployeeSignIn()
    {
        this.employeeId = "";
        this.password = "";
    }
    public EmployeeSignIn(String id, String pass)
    {
        this.employeeId = id;
        this.password = pass;
    }
    public String getEmployeeId()
    {
        return this.employeeId;
    }
    public void setEmployeeId(String id)
    {
        this.employeeId = id;
    }
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String pass)
    {
        this.password = pass;
    }
}