package com.dao;

import com.busines.Department;

import java.util.Map;

public interface DepartmentDAO {
    public Map<Department, Double> getAllDepartments();
    public Department getDepartment(long id);
    public boolean renameDepartment(String name, long id);
    public boolean deleteDepartment(long id);
    public boolean addDepartment(Department d);

}
