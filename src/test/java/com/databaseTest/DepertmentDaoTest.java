package com.databaseTest;

import ch.qos.logback.classic.Logger;
import com.busines.Department;
import com.dao.DepartmentDAOImpl;
import com.init.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.junit.Assert.*;

public class DepertmentDaoTest {

   // private static final Logger logger = (Logger) LoggerFactory.getLogger(DepertmentDaoTest.class);

    AnnotationConfigApplicationContext context;

    @Before
    public void intit(){
        context =  new AnnotationConfigApplicationContext(AppConfig.class);
    }
    @After
    public void  after(){
        context.close();
    }


    @Test
    public void getAllDepartmentsTest(){
        DepartmentDAOImpl dao =  context.getBean(DepartmentDAOImpl.class);
        assertNotNull(dao);
        Map<Department, Double> map = dao.getAllDepartments();
        assertNotNull(map);
        map.forEach((department, aDouble) -> {
            System.out.println(department+ " salary: "+ aDouble);
        });
    }
    @Test
    public void renameDepartmentTest(){
        DepartmentDAOImpl dao =  context.getBean(DepartmentDAOImpl.class);
        assertNotNull(dao);
        String name = "new Department";
        assertTrue(dao.renameDepartment(name, 1));
        assertFalse(dao.renameDepartment(name, 100));
        assertTrue(dao.getDepartment(1).getName().equals(name));
    }


    @Test
    public void getDepartmentByIdTest(){
        DepartmentDAOImpl dao =  context.getBean(DepartmentDAOImpl.class);
        assertNotNull(dao);
        Department department = dao.getDepartment(1);
        assertNotNull(department);
        assertTrue(department.getName().equals("IT"));
        department = dao.getDepartment(0);
        assertNull(department.getId());
    }
    @Test
    public void deleteDepartmentTest(){
        DepartmentDAOImpl dao =  context.getBean(DepartmentDAOImpl.class);
        assertNotNull(dao);
        Department department = dao.getDepartment(1);
        assertTrue(department.getName()!=null);
        assertTrue(dao.deleteDepartment(department.getId()));
        department = dao.getDepartment(1);
        assertTrue(department.getName()==null);
    }
}
