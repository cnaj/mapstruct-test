package de.cernaj.test.mapstruct;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EmployeeMapperImplPoc implements EmployeeMapper {
    public static final EmployeeMapperImplPoc INSTANCE = new EmployeeMapperImplPoc();

    private final Confidential addressConfidentialAnnotation;
    private final Confidential salaryConfidentialAnnotation;

    public EmployeeMapperImplPoc() {
        addressConfidentialAnnotation = getMethodAnnotation( Confidential.class, "getAddress" );
        salaryConfidentialAnnotation = getMethodAnnotation( Confidential.class, "getSalary" );
    }

    private static <T extends Annotation> T getMethodAnnotation(Class<T> annotationClass, String methodName) {
        try {
            Method method = Employee.class.getMethod( methodName );
            return method.getAnnotation( annotationClass );
        }
        catch ( NoSuchMethodException e ) {
            throw new RuntimeException( e );
        }
    }

    private static <T extends Annotation> T getFieldAnnotation(Class<T> annotationClass, String fieldName) {
        try {
            Field field = Employee.class.getField( fieldName );
            return field.getAnnotation( annotationClass );
        }
        catch ( NoSuchFieldException e ) {
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
        address = mapProperty( value.getAddress(), accessLevel, addressConfidentialAnnotation );
        salary = mapProperty( value.getSalary(), accessLevel, salaryConfidentialAnnotation );

        EmployeeDTO employeeDTO = new EmployeeDTO( name, address, salary );

        return employeeDTO;
    }

}
