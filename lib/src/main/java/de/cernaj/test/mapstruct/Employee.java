package de.cernaj.test.mapstruct;

import lombok.Value;

@Value
public class Employee {
    String name;

    @Confidential("company")
    String address;

    @Confidential("management")
    String salary;
}
