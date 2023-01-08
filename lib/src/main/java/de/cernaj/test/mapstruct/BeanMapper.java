package de.cernaj.test.mapstruct;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.SourceAnnotation;

@Mapper
public interface BeanMapper {
    TargetBean toTarget(SourceBean value);

    default String mapProperty(String value) {
        return value;
    }

    default String mapProperty(String value, @SourceAnnotation StringConversion conversion) {
        if (conversion == null) {
            return value;
        }

        String conversionType = conversion.value();

        switch (conversionType) {
        case "reverse":
            return StringUtils.reverse(value);
        case "upperCase":
            return StringUtils.toRootUpperCase(value);
        case "strip":
            return StringUtils.strip(value);
        default:
            return value;
        }
    }
}
