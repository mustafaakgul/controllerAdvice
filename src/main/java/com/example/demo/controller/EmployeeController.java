package com.example.demo.controller;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //Happy path, an employee is returned as response
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmpl() throws ResourceNotFoundException, EmployeeServiceException {
        try {
            Employee emp = employeeService.getEmployee();

            if (emp == null) {
                throw new ResourceNotFoundException("Employee not found");
            }
            return emp;
        } catch (EmployeeServiceException e) {
            throw new EmployeeServiceException("Internal Server Exception while getting exception");
        }
    }

    //no employee found so ResourceNotFoundException is thrown
    @RequestMapping(value = "/employee2", method = RequestMethod.GET)
    public Employee getEmp2() throws ResourceNotFoundException, EmployeeServiceException {
        try {
            Employee emp = employeeService.getEmployeeNull();
            if (emp == null) {
                throw new ResourceNotFoundException("Employee not found");
            }

            return emp;
        } catch (EmployeeServiceException e) {
            throw new EmployeeServiceException("Internal Server Exception while getting exception");
        }
    }

    //Some exception is thrown by service layer
    @RequestMapping(value = "/employee3", method = RequestMethod.GET)
    public Employee getEmp3() throws ResourceNotFoundException, EmployeeServiceException {
        try {
            Employee emp = employeeService.getEmployeeException();
            if (emp == null) {
                throw new ResourceNotFoundException("Employee not found");
            }
            return emp;
        } catch (EmployeeServiceException e) {
            throw new EmployeeServiceException("Internal Server Exception while getting exception");
        }
    }
}