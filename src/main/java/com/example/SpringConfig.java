package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * One service (the SpanishGreetingService) isn't annotated with @Service,
 * but instead is configured in the xml. The EnglishGreetingService will
 * be picked up in the component scan, as it is annotated with @Service.
 * Just throwing a little variation in there for show.
 *
 * The Jersey resources are also part of the component scan, as those will
 * be Spring components also. As a consequence, the resources will automatically
 * be singletons by default, per Spring's default behavior. If you want then to
 * be request scoped, you can add `@Scope("request")` to the resource.
 *
 * Note that it is not required for your resource classes to be Spring beans.
 * If you choose not to make them beans, then the default Jersey lifecycle
 * for resources will still be in effect. The only thing that will not
 * work (that I have found) is `@Value` if you want to inject them into
 * your resources. This makes perfect sense as the resource will not go through
 * the Spring system. The `@Autowired` will still work, as that is actually
 * an HK2/Spring bridge that handle this injection, not Spring directly.
 *
 * @author Paul Samsotha.
 */
@Configuration
@ImportResource(locations = {"classpath:applicationContext.xml"})
@ComponentScan(basePackages = {"com.example.resource", "com.example.service"})
public class SpringConfig {
}
