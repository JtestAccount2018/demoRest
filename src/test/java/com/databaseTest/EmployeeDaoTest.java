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

import java.sql.Date;
import java.util.GregorianCalendar;
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

    @Test
    public void replaceEmployeeTest(){
        Employee employee = dao.getEmpoyee(1);
        assertTrue(employee.getFirstName()!=null);
        String name = employee.getFirstName();
        employee.setFirstName("Test");
        employee.setBirthDate(new Date(new GregorianCalendar(1999, 10, 05).getTime().getTime()));
        assertTrue(dao.replaceEmployee(employee, 1));
        employee = dao.getEmpoyee(1);
        assertFalse(employee.getFirstName().equals(name));
        assertTrue(employee.getFirstName().equals("Test"));
        assertTrue(employee.getBirthDate().getTime()==new GregorianCalendar(1999, 10, 05).getTime().getTime());
    }

    @Test
    public void deleteEmployeeTest(){
        Employee employee = dao.getEmpoyee(5);
        assertTrue(employee.getFirstName()!=null);
        assertTrue(dao.deleteEmployee(5));
        assertFalse(dao.deleteEmployee(5));
        employee = dao.getEmpoyee(5);
        assertNull(employee.getFirstName());
    }
    @Test
    public void addEmployeeTest(){
        Employee e =  new Employee();
        e.setId(100);
        e.setFirstName("TEST");
        e.setLastName("Ts");
        e.setBirthDate(new Date(new GregorianCalendar(1999, 01, 04).getTime().getTime()));
        e.setSalary(100500.0);
        e.setDepartmentId(2);
        assertTrue(dao.addEmployee(e));
        assertTrue(e.getId()!=100);
        logger.info(e.getId() + " new id");
        e.setFirstName(null);

    }
}
