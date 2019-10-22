package com.web;

import com.busines.Employee;
import com.dao.DepartmentDAOImpl;
import com.dao.EmployeeDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/emp")
public class ControllerEmployee {
    @Autowired
    EmployeeDAOImpl dao;
    @Autowired
    DepartmentDAOImpl departmentDAO;


    @GetMapping("/all")
    public List<Employee> getAll(){
        return dao.getAll();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable long id){
        return dao.getEmpoyee(id);
    }

    @PutMapping("/edit/{id}")
    public Employee editEmpl(@PathVariable long id, @RequestBody Employee e){
        if(dao.replaceEmployee(e, id)){
            return e;
        }
        else return new Employee();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        return new Boolean(dao.deleteEmployee(id)).toString();
    }

    @PostMapping("/add")
    public Employee addNew(@RequestBody Employee e){
        dao.addEmployee(e);
        e.setDepartmentName(departmentDAO.getDepartment(e.getDepartmentId()).getName());
        return e;
    }

    @GetMapping("/inDepartment/{id}")
    public List<Employee> inDepartment(@PathVariable long id){
        return dao.getByDepId(id);
    }

    @GetMapping("/BirthIn")
    public List<Employee> birthIn(@RequestParam long date){
        return dao.getByDate(new Date(date));
    }

    @GetMapping("/datePeriod")
    public List<Employee> datePeriod(@RequestParam("start") long start, @RequestParam("end") long end){

            return dao.getByDatePerido(new Date(start), new Date(end));

    }

}
