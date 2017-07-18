package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Paul Samsotha.
 */
@Configuration
@ImportResource(locations = {"classpath:applicationContext.xml"})
@ComponentScan(basePackages = {"com.example.resource", "com.example.service"})
public class SpringConfig {
}
