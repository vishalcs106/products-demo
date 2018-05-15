package com.demo.productsdemo.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Vishal on 13-05-2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
