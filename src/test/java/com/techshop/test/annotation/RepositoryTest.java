package com.techshop.test.annotation;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides annotations for repository test:
 * <ul>
 *     <li>{@link SpringBootTest}</li>
 *     <li>{@link ActiveProfiles} - set active profile with default value "test"</li>
 * </ul>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles
@SpringBootTest
public @interface RepositoryTest {
    @AliasFor(annotation = ActiveProfiles.class, attribute = "value")
    String[] activeProfiles() default {"test"};
}
