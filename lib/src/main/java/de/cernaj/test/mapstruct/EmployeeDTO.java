package de.cernaj.test.mapstruct;

import lombok.Value;

@Value
public class EmployeeDTO {
    String name;
    String address;
    String salary;
}
