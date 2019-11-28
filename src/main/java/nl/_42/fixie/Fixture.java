package nl._42.fixie;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that an annotated class is a "Fixture", functioning as a
 * catalog for standardised test objects. Rather than recreating complex
 * test objects we define a single factory method, which will be cached
 * during each test run.
 *
 * Each public no-arg method in this annotated class will be intercepted
 * and cached between test runs. This prevents a similar object from being
 * persisted multiple times in a database, potentially violating semantic
 * unique constraints.
 *
 * @author Jeroen van Schagen
 * @see Component
 */
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Transactional
public @interface Fixture {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any (or empty String otherwise)
     */
    @AliasFor(annotation = Component.class)
    String value() default "";

}
