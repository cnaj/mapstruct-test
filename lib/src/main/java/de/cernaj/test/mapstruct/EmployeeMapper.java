package de.cernaj.test.mapstruct;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.SourceAnnotation;

@Mapper
public interface EmployeeMapper {
    String CONFIDENTIAL_MARKER = "<confidential>";

    EmployeeDTO toDto(Employee value, @Context String accessLevel);

    default String mapProperty(String value, @Context String accessLevel, @SourceAnnotation Confidential confidential) {
        if (confidential == null) {
            return value;
        }

        String requiredLevel = confidential.value();

        // business logic follows

        switch (requiredLevel) {
        case "company":
            return StringUtils.equalsAny(accessLevel, "company", "management") ? value : CONFIDENTIAL_MARKER;
        case "management":
            return StringUtils.equalsAny(accessLevel, "management") ? value : CONFIDENTIAL_MARKER;
        default:
            return value;
        }
    }
}
