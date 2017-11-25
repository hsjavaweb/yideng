package com.xc.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyAnnotation {
	
    public String name() default "";     //����
    public String type() default "String";    //����
    public String primarykey() default "";	//�Ƿ�������
}