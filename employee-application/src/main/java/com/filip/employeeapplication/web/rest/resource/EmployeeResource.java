package com.filip.employeeapplication.web.rest.resource;


import java.util.List;
import java.util.Optional;

import com.filip.employeeapplication.db.entities.Employee;
import com.filip.employeeapplication.db.repository.EmployeeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/employee")
@Api(tags = "Employee Queries", value = "EmployeeQueries")
public class EmployeeResource {

    //Ideally you shall be using Service classes
    @Autowired
    EmployeeRepository employeeRepository;

    @ApiOperation(value = "Get all employees")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity< List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @ApiOperation(value = "Get a person by ID")
    @GetMapping(value = "/{employeeid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> getEmployeeByEmployeeId(@PathVariable("employeeid") int employeeid){
        Optional<Employee> personInDB = employeeRepository.findById(employeeid);
        if(personInDB.isPresent()){
            return ResponseEntity.ok(personInDB.get());
        }else{
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee person ){
        Employee personInDB = employeeRepository.save(person);
        return new ResponseEntity<Employee>(personInDB,HttpStatus.CREATED);
    }

    @PutMapping("/{employeeid}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("employeeid") int employeeid, @RequestBody(required=true) Employee employeeDataToBeUpdated ){

        if(employeeid != employeeDataToBeUpdated.getEmployeeId() ){	//Just to make sure we have same person_id in path param and body.
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }

        Optional<Employee> personInDB = employeeRepository.findById(employeeid);
        if(personInDB.isPresent()){
            Employee person = employeeRepository.saveAndFlush(employeeDataToBeUpdated);
            return ResponseEntity.ok(person);
        }else{
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
}