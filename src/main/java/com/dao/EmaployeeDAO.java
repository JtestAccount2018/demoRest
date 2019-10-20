package com.dao;

import com.busines.Employee;

import java.util.List;

public interface EmaployeeDAO {
     public List<Employee> getAll();
     public Employee getEmpoyee(long id);
     public boolean replaceEmployee(Employee e, long id);
     public boolean deleteEmployee(Employee e);
     public boolean addEmployee(Employee e);

}
