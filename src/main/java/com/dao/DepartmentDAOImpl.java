package com.dao;

import com.busines.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        String sql = "select d.id, d.name, avg(salary) avg_salary FROM department d inner join employee e on d.id=e.departmentId group by d.id order by d.id;";
        Map<Department , Double> res =  namedParameterJdbcTemplate.query(sql, (resultSet) -> {
           Map<Department, Double> result =  new LinkedHashMap<>();
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getLong("id"));
                department.setName(resultSet.getString("name"));
                result.put(department, resultSet.getDouble("avg_salary"));
            }
            return result;

        });
       return res;
    }

    @Override
    public Department getDepartment(long id) {
        String sql = "SELECT * FROM department WHERE id=:id";
        Map<String, Object> map =  new HashMap<>();
        map.put("id", id);
        return namedParameterJdbcTemplate.query(sql, map, resultSet -> {
            Department department =  new Department();
            while (resultSet.next()){
                department.setId(resultSet.getLong("id"));
                department.setName(resultSet.getString("name"));
            }
            return department;
        });
    }

    @Override
    public boolean renameDepartment(String name, long id) {
        String sql = "UPDATE department SET name=:name WHERE id=:id";
        Map<String, Object> map =  new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        return namedParameterJdbcTemplate.update(sql, map)==1?true:false;
    }

    @Override
    public boolean deleteDepartment(long id) {
        String sql = "DELETE FROM department WHERE id=:id";
        Map<String, Object> map =  new HashMap<>();
        map.put("id", id);
        return namedParameterJdbcTemplate.update(sql, map)==1?true:false;
    }


    //need to test
    @Override
    public boolean addDepartment(Department d) {
        String sql = "INSERT INTO department (name) VALUES (:name)";
        KeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource source =  new MapSqlParameterSource();
        source.addValue("name", d.getName());
        int result =  namedParameterJdbcTemplate.update(sql, source, holder);
        if(result!=1){
            return false;
        }
        d.setId(holder.getKey().longValue());
        return true;
    }
}
