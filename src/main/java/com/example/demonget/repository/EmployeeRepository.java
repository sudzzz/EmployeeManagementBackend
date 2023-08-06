package com.example.demonget.repository;

import com.example.demonget.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeModel,Integer> {

}
