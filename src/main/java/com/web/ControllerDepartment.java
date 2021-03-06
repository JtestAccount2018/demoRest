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
    public boolean renameD(@RequestBody Department department){
        return dao.renameDepartment(department.getName(), department.getId());
    }


    @DeleteMapping("/delete/{id}")
    public boolean deleteId(@PathVariable long id){
        return dao.deleteDepartment(id);
    }


    @PostMapping(value = "/addDepartment", headers = {"Content-type=application/json"})
    public Department addD(@RequestBody Department department){
        if(dao.addDepartment(department)){
            return department;
        }
        return new Department();
    }

}
