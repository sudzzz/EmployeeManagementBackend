package com.example.demonget.response;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeResponse implements Serializable {
    private Integer empId;
    private String firstName;
    private String lastName;
}
