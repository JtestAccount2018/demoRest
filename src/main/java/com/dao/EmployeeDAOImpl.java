package com.dao;

import com.busines.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Repository
@Transactional
public class EmployeeDAOImpl  implements EmployeeDAO{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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
        String sql = "UPDATE employee SET firstName=:firstName, lastName=:lastName, birthDate=:birthDate, salary=:salary, departmentId=:did WHERE id=:id";
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", e.getFirstName());
        map.put("lastName", e.getLastName());
        map.put("birthDate",  e.getBirthDate());
        map.put("salary", e.getSalary());
        map.put("did", e.getDepartmentId());
        map.put("id", id);
        if(namedParameterJdbcTemplate.update(sql, map)==1){
            e.setId(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(long id) {
        String sql = "DELETE FROM employee WHERE id=:id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return namedParameterJdbcTemplate.update(sql, map)==1?true:false;
    }

    @Override
    public boolean addEmployee(Employee e) {
        String sql = "INSERT INTO employee (firstName, lastName, birthDate, salary, departmentId) VALUES (:firstName, :lastName," +
                ":birthDate, :salary, :departmentId)";
        MapSqlParameterSource source =  new MapSqlParameterSource();
        source.addValue("firstName", e.getFirstName());
        source.addValue("lastName", e.getLastName());
        source.addValue("birthDate", e.getBirthDate());
        source.addValue("salary", e.getSalary());
        source.addValue("departmentId", e.getDepartmentId());
        KeyHolder keyHolder =  new GeneratedKeyHolder();
        int result =  namedParameterJdbcTemplate.update(sql, source, keyHolder);
        if(result==1){
            e.setId(keyHolder.getKey().longValue());
            return true;
        }
        return false;
    }
}
