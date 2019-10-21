package com.json;

import com.busines.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.init.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.GregorianCalendar;

public class JsonTest {
    private static final Logger logger = LoggerFactory.getLogger(JsonTest.class);
    private AnnotationConfigApplicationContext context;

    @Before
    public void init(){
        context =  new AnnotationConfigApplicationContext(AppConfig.class);
    }
    @After
    public  void  after(){
        context.close();
    }

    @Test
    public void JsonParserTest() throws JsonProcessingException {
        Employee employee =  new Employee();
        employee.setFirstName("first name");
        employee.setLastName("last name");
        employee.setBirthDate(new Date(new GregorianCalendar(1966, 10, 22).getTime().getTime()));
        employee.setSalary(100.0);
        employee.setDepartmentId(1);
        employee.setDepartmentName("IT");
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(employee);
        logger.info("result string is: " + result);
        logger.info("birthDate in ms: " +employee.getBirthDate().getTime());
        logger.info("object info: " +employee.toString());

    }





}
