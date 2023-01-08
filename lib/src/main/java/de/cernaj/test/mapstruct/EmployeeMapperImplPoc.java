package de.cernaj.test.mapstruct;

import java.lang.reflect.Method;

public class EmployeeMapperImplPoc implements EmployeeMapper {
    public static final EmployeeMapperImplPoc INSTANCE = new EmployeeMapperImplPoc();

    @Override
    public EmployeeDTO toDto(Employee value, String accessLevel) {
        if (value == null) {
            return null;
        }

        String name = null;
        String address = null;
        String salary = null;

        Method getAddressMethod;
        Method getSalaryMethod;

        try {
            getAddressMethod = Employee.class.getMethod("getAddress");
            getSalaryMethod = Employee.class.getMethod("getSalary");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        name = mapProperty(value.getName());
        address = mapProperty(value.getAddress(), accessLevel, getAddressMethod.getAnnotation(Confidential.class));
        salary = mapProperty(value.getSalary(), accessLevel, getSalaryMethod.getAnnotation(Confidential.class));

        EmployeeDTO employeeDTO = new EmployeeDTO(name, address, salary);

        return employeeDTO;
    }

}
