package nl._42.fixie;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
@Component
@Transactional
public @interface Fixture {
}
