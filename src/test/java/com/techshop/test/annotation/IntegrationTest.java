package com.techshop.test.annotation;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides annotations for integration tests:
 * <ul>
 *     <li>{@link SpringBootTest}</li>
 *     <li>{@link ActiveProfiles} - set active profile with default value "test"</li>
 *     <li>{@link AutoConfigureMockMvc} - provides {@link org.springframework.test.web.servlet.MockMvc} </li>
 * </ul>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles
@SpringBootTest
@AutoConfigureMockMvc
public @interface IntegrationTest {
    @AliasFor(annotation = ActiveProfiles.class, attribute = "value")
    String[] activeProfiles() default {"test"};
}
