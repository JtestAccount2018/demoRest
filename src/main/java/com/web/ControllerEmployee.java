package com.web;

import com.busines.Employee;
import com.dao.EmployeeDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class ControllerEmployee {
    @Autowired
    EmployeeDAOImpl dao;


    @GetMapping("/all")
    public List<Employee> getAll(){
        return dao.getAll();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable long id){
        return dao.getEmpoyee(id);
    }

    @PutMapping("/edit/{id}")
    public String editEmpl(@PathVariable long id, @RequestAttribute Employee e){
        return new Boolean(dao.replaceEmployee(e, id)).toString();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        return new Boolean(dao.deleteEmployee(id)).toString();
    }

    @PostMapping("/add")
    public Employee addNew(@RequestAttribute Employee e){
        dao.addEmployee(e);
        return e;
    }


}
