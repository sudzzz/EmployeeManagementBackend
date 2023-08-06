package com.example.demonget.service;

import com.example.demonget.exception.EmployeeNotFoundException;
import com.example.demonget.model.EmployeeModel;
import com.example.demonget.repository.EmployeeRepository;
import com.example.demonget.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public List<EmployeeResponse> getPartialDataForEmployee(){
        List<EmployeeModel> employeeModels = getAllEmployee();
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for(EmployeeModel employeeModel : employeeModels){
            EmployeeResponse employeeResponse = EmployeeResponse.builder()
                    .empId(employeeModel.getEmpId())
                    .firstName(employeeModel.getFirstName())
                    .lastName(employeeModel.getLastName())
                    .build();
            employeeResponseList.add(employeeResponse);
        }
        return employeeResponseList;
    }

    public EmployeeModel saveEmployee(EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel getEmployeeDataById(int id) throws EmployeeNotFoundException{
        return employeeRepository.findById(id).orElseThrow(
                ()-> new EmployeeNotFoundException("Employee with Id " + id + " not present!!")
        );
    }

    public EmployeeModel updateEmployeeById(int id,EmployeeModel employeeModel) throws EmployeeNotFoundException {
        EmployeeModel emp = getEmployeeDataById(id);
        emp.setFirstName(employeeModel.getFirstName());
        emp.setLastName(employeeModel.getLastName());
        emp.setDesignation(employeeModel.getDesignation());
        emp.setProject(employeeModel.getProject());
        emp.setReportingManager(employeeModel.getReportingManager());

        return employeeRepository.save(emp);
    }

    public EmployeeModel deleteEmployeeById(int id) throws EmployeeNotFoundException {
        EmployeeModel employeeModel = getEmployeeDataById(id);
        employeeRepository.delete(employeeModel);
        return employeeModel;
    }
}
