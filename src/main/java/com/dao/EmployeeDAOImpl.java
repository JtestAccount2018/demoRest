package com.dao;

import com.busines.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Repository
public class EmployeeDAOImpl  implements EmployeeDAO{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Employee> getAll() {
        String sql = "SELECT e.*, d.name FROM employee e left join department d ON departmentId=d.id ORDER BY e.id";
        return namedParameterJdbcTemplate.query(sql, (rs, rn)->{
            Employee employee =  new Employee();
            employee.setId(rs.getLong("id"));
            employee.setFirstName(rs.getString("firstName"));
            employee.setLastName(rs.getString("lastName"));
            employee.setBirthDate(rs.getDate("birthDate"));
            employee.setDepartmentId(rs.getLong("departmentId"));
            employee.setDepartmentName(rs.getString("name"));
            employee.setSalary(rs.getDouble("salary"));
            return employee;
        });
    }

    @Override
    public Employee getEmpoyee(long id) {
        String sql = "SELECT e.*, d.name FROM employee e LEFT JOIN department d ON departmentId=d.id WHERE e.id=:id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return namedParameterJdbcTemplate.query(sql, map, rs -> {
            Employee employee = new Employee();
            while (rs.next()) {
                employee.setId(rs.getLong("id"));
                employee.setFirstName(rs.getString("firstName"));
                employee.setLastName(rs.getString("lastName"));
                employee.setBirthDate(rs.getDate("birthDate"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartmentId(rs.getLong("departmentId"));
                employee.setDepartmentName(rs.getString("name"));
            }
            return employee;
        });
    }

    @Override
    public boolean replaceEmployee(Employee e, long id) {
        return false;
    }

    @Override
    public boolean deleteEmployee(Employee e) {
        return false;
    }

    @Override
    public boolean addEmployee(Employee e) {
        return false;
    }
}
