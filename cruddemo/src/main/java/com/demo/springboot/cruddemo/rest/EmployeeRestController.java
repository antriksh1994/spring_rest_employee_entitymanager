package com.demo.springboot.cruddemo.rest;

import com.demo.springboot.cruddemo.EmployeeService.EmployeeService;
import com.demo.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        System.out.println("Fetch all employees: ");
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee (@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found" + employeeId);
        }
        return  theEmployee;
    }
    @PostMapping("/employees")
    public Employee addEmployee (@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    @PutMapping("/employees")
    public Employee updateEmployee (@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee (@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        System.out.println("tempEmployee" + tempEmployee);
        if (tempEmployee == null) {
            throw new RuntimeException("Employee ID Not found" + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted EmployeeID " + employeeId;
    }
}
