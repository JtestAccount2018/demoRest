package com.dao;

import com.busines.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
@Repository
@Component
public class DepartmentDAOImpl implements DepartmentDAO {

    @Autowired
    DataSource dataSource;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Map<Department, Double> getAllDepartments() {
        String sql = "SELECT * FROM department";
        List<Department> departments =  namedParameterJdbcTemplate.query(sql, (resultSet, rownum) -> {
            Department department =  new Department();
            department.setId(resultSet.getLong("id"));
            department.setName(resultSet.getString("name"));
            return department;
        });
        String sqlSalary = "Select avg() from employee WHERE departmentId = ?"
    }

    @Override
    public Department getDepartment(long id) {
        return null;
    }

    @Override
    public boolean renameDepartment(String name, long id) {
        return false;
    }

    @Override
    public boolean deleteDepartment(long id) {
        return false;
    }
}
