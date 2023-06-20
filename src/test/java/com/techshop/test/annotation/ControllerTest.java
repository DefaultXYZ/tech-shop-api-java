package com.techshop.test.annotation;

import com.techshop.config.SecurityConfig;
import com.techshop.config.properties.api.ApiProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides annotations for unit tests of RestController:
 * <ul>
 *     <li>{@link WebMvcTest}</li>
 *     <li>{@link ActiveProfiles} - set active profile with default value "test"</li>
 *     <li>{@link Import} - import classes</li>
 *     <li>{@link ExtendWith} - extends {@link SpringExtension}</li>
 *     <li>{@link ContextConfiguration} - configures {@link SecurityConfig}</li>
 *     <li>{@link EnableConfigurationProperties} - binds {@link ApiProperties}</li>
 * </ul>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SecurityConfig.class})
@EnableConfigurationProperties(value = {ApiProperties.class})
@WebMvcTest
public @interface ControllerTest {

    @AliasFor(annotation = ActiveProfiles.class, attribute = "value")
    String[] activeProfiles() default {"test"};

    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] importClasses() default {};
}
