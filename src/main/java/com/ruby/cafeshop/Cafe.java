package com.ruby.cafeshop;

import lombok.Getter;
import lombok.Setter;
import java.lang.reflect.Method;

@Getter
@Setter
public class Cafe {

    private String name;
    private Method method;
    private Object clazz;
    private String type;
}
