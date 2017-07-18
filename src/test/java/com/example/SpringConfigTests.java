package com.example;

import com.example.service.EnglishGreetingService;
import com.example.service.SpanishGreetingService;
import com.sun.javafx.scene.control.behavior.ScrollPaneBehavior;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Paul Samsotha.
 */
public class SpringConfigTests {

    private ApplicationContext context;


    @Before
    public void setUp() {
        this.context = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    @Test
    public void testGreetingServicesAreBeansInContext() {
        assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
                .isThrownBy(() -> this.context.getBean("DoesNotExistBean"));
        assertThat(this.context.getBean(EnglishGreetingService.class)).isNotNull();
        assertThat(this.context.getBean(SpanishGreetingService.class)).isNotNull();
    }
}
