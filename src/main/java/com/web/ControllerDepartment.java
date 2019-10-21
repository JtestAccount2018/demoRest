package com.web;

import com.busines.Department;
import com.dao.DepartmentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/department")
public class ControllerDepartment {
    @Autowired
    DepartmentDAOImpl dao;

    @GetMapping("/getAll")
    public Map<Department, Double> getDepartments(){
        return dao.getAllDepartments();
    }

    @GetMapping("/getById/{id}")
    public Department getById(@PathVariable long id){
        return dao.getDepartment(id);
    }

    @PutMapping("/renameD")
    public boolean renameD(@RequestAttribute String name, @RequestAttribute long id){
        return dao.renameDepartment(name, id);
    }


    @DeleteMapping("/delete/{id}")
    public boolean deleteId(@PathVariable long id){
        return dao.deleteDepartment(id);
    }

    @PostMapping("/addDepartment")
    public boolean addD(@RequestAttribute Department department){
        return  dao.addDepartment(department);
    }

}
