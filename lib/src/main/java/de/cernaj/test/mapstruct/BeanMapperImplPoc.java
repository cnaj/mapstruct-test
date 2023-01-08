package de.cernaj.test.mapstruct;

import java.lang.reflect.Method;

public class BeanMapperImplPoc implements BeanMapper {

    @Override
    public TargetBean toTarget(SourceBean value) {
        if (value == null) {
            return null;
        }

        String firstProperty = null;
        String secondProperty = null;

        Method getFirstPropertyMethod;
        Method getSecondPropertyMethod;

        try {
            getFirstPropertyMethod = SourceBean.class.getMethod("getFirstProperty");
            getSecondPropertyMethod = SourceBean.class.getMethod("getSecondProperty");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        firstProperty =
                mapProperty(value.getFirstProperty(), getFirstPropertyMethod.getAnnotation(StringConversion.class));
        secondProperty =
                mapProperty(value.getSecondProperty(), getSecondPropertyMethod.getAnnotation(StringConversion.class));

        TargetBean targetBean = new TargetBean(firstProperty, secondProperty);

        return targetBean;
    }

}
