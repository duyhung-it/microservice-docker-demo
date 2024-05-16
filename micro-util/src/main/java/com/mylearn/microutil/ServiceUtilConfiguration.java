package com.mylearn.microutil;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.mylearn.microutil", "com.mylearn.microutil.configure"})
public class ServiceUtilConfiguration {
}
