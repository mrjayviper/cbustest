package com.cbus.aem.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
    name = "Pets API URL Config Service"
    , description = "Pets API URL config"
)
public @interface PetsApiConfig {
    @AttributeDefinition(
        name = "Pets API URL",
        description = "",
        defaultValue = "",
        type = AttributeType.STRING)
    String petsApiUrl() default "https://www.cbussuper.com.au/petowners.json";

    @AttributeDefinition(
        name = "API connection timeout",
        description = "",
        defaultValue = "",
        type = AttributeType.INTEGER)
    int connectionTimeout() default 10000;

    @AttributeDefinition(
        name = "API read timeout",
        description = "",
        defaultValue = "",
        type = AttributeType.STRING)
    int readTimeout() default 10000;

    @AttributeDefinition(
        name = "Pets types",
        description = "",
        defaultValue = "",
        type = AttributeType.STRING)
    String petsTypes() default "cat,dog,fish";
}
