package com.example.demonget.controller;

import com.example.demonget.exception.EmployeeNotFoundException;
import com.example.demonget.model.EmployeeModel;
import com.example.demonget.response.EmployeeResponse;
import com.example.demonget.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins={"http://localhost:4200"})
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAll")
    public List<EmployeeModel> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/getEmployeeData")
    public List<EmployeeResponse> getEmployeeData(){
        return employeeService.getPartialDataForEmployee();
    }

    @GetMapping("/getEmployeeById")
    public EmployeeModel getEmployeeDataById(@RequestParam("id") int id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeDataById(id);
    }

    @PostMapping("/saveEmployee")
    public EmployeeModel saveEmployee(@RequestBody EmployeeModel employeeModel){
        logger.info("Employee save request is {}",employeeModel.toString());
        return employeeService.saveEmployee(employeeModel);
    }

    @PutMapping("/updateEmployee/{id}")
    public EmployeeModel updateEmployeeById(@PathVariable("id") int id, @RequestBody EmployeeModel employeeModel) throws EmployeeNotFoundException {
        logger.info("Employee save request is {}",employeeModel.toString());
        return employeeService.updateEmployeeById(id,employeeModel);
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public EmployeeModel deleteEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
        return employeeService.deleteEmployeeById(id);
    }

}
