package de.cernaj.test.mapstruct;

public class EmployeeMapperImplPoc implements EmployeeMapper {
    public static final EmployeeMapperImplPoc INSTANCE = new EmployeeMapperImplPoc();

    private static final Confidential[] SOURCE_ANNOTATIONS_CONFIDENTIAL;

    static {
        try {
            SOURCE_ANNOTATIONS_CONFIDENTIAL = new Confidential[] {
                Employee.class.getMethod( "getAddress" ).getAnnotation( Confidential.class ),
                Employee.class.getMethod( "getSalary" ).getAnnotation( Confidential.class ),
            };
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

        name = mapProperty( value.getName(), accessLevel, null );
        address = mapProperty( value.getAddress(), accessLevel, SOURCE_ANNOTATIONS_CONFIDENTIAL[0] );
        salary = mapProperty( value.getSalary(), accessLevel, SOURCE_ANNOTATIONS_CONFIDENTIAL[1] );

        EmployeeDTO employeeDTO = new EmployeeDTO( name, address, salary );

        return employeeDTO;
    }

}
