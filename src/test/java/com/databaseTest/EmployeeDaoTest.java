package com.databaseTest;

import ch.qos.logback.classic.Logger;
import com.busines.Employee;
import com.dao.EmployeeDAOImpl;
import com.init.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static junit.framework.TestCase.*;

public class EmployeeDaoTest {
    AnnotationConfigApplicationContext context;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(EmployeeDaoTest.class);
    EmployeeDAOImpl dao;


    @Before
    public void init(){
        context =  new AnnotationConfigApplicationContext(AppConfig.class);
        dao = context.getBean(EmployeeDAOImpl.class);
    }


    @After
    public void after(){
        context.close();
    }

    @Test
    public void getAllEmployeeTest(){
        assertNotNull(dao);
        List<Employee> list =  dao.getAll();
        assertTrue(list.size()>0);
        list.forEach(employee -> {
            System.out.println(employee);
        });
    }


    @Test
    public void getEmployeeByIdTest(){
        Employee employee = dao.getEmpoyee(1);
        assertTrue(employee.getFirstName().equals("John") && employee.getSalary().equals(1245.3));
        employee = dao.getEmpoyee(0);
        assertNull(employee.getBirthDate());
    }
}
