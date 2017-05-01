package com.xaskysad.xmlparser_master.xmlparser_master;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TList {

    String value();
}
