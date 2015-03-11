package org.imaginea.test.automation.framework.dom;

import org.imaginea.test.automation.framework.pagemodel.PageClass;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by varunm on 03-03-2015.
 */
@Retention(RUNTIME)
@Target({FIELD })
public @interface ElementOptions {
    String name() default "";
    Class<? extends PageClass>[] navigablePageClasses() default {};
    boolean required() default true;
}
