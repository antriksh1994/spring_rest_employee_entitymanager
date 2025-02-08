package com.demo.springboot.cruddemo.dao;

import com.demo.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeImpl implements EmployeeDAO {
    private EntityManager entityManager;

    public EmployeeImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> allEmployees = theQuery.getResultList();
        System.out.println("Query: " + allEmployees);
        return  allEmployees;
    }

    @Override
    public Employee findById(int theId) {
        // get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        // return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        // save employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        // return employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // find the employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        // delete the employee
        entityManager.remove(theEmployee);
    }
}
