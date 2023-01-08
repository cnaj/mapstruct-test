package de.cernaj.test.mapstruct;

import lombok.Value;

@Value
public class SourceBean {
    @StringConversion("upperCase")
    String firstProperty;

    @StringConversion("reverse")
    String secondProperty;
}
