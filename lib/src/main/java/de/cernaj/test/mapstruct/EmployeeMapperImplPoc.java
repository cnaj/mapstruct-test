package de.cernaj.test.mapstruct;

import java.lang.reflect.Method;

public class EmployeeMapperImplPoc implements EmployeeMapper {
    public static final EmployeeMapperImplPoc INSTANCE = new EmployeeMapperImplPoc();

    private static final Confidential getAddressMethodConfidentialAnnotation;
    private static final Confidential getSalaryMethodConfidentialAnnotation;

    static {
        Method getAddressMethod;
        Method getSalaryMethod;

        try {
            getAddressMethod = Employee.class.getMethod( "getAddress" );
            getSalaryMethod = Employee.class.getMethod( "getSalary" );

            getAddressMethodConfidentialAnnotation = getAddressMethod.getAnnotation( Confidential.class );
            getSalaryMethodConfidentialAnnotation = getSalaryMethod.getAnnotation( Confidential.class );
        }
        catch ( NoSuchMethodException e ) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public EmployeeDTO toDto(Employee value, String accessLevel) {
        if ( value == null ) {
            return null;
        }

        String name = null;
        String address = null;
        String salary = null;

        name = mapProperty( value.getName() );
        address = mapProperty( value.getAddress(), accessLevel, getAddressMethodConfidentialAnnotation );
        salary = mapProperty( value.getSalary(), accessLevel, getSalaryMethodConfidentialAnnotation );

        EmployeeDTO employeeDTO = new EmployeeDTO( name, address, salary );

        return employeeDTO;
    }

}
