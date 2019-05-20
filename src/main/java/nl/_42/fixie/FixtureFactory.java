package nl._42.fixie;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

/**
 * Retrieves the fully proxied fixture objects, as registered
 * in the application context.
 */
@AllArgsConstructor
public class FixtureFactory {

    private final ApplicationContext applicationContext;

    /**
     * Retrieve the proxied fixture object.
     * @param object the plain fixture object
     * @param <T> the type of fixture
     * @return the proxied fixture
     */
    public <T> T get(T object) {
        Class<?> fixtureType = object.getClass();
        return (T) get(fixtureType);
    }

    /**
     * Retrieve the proxied fixture object.
     * @param fixtureType the fixture type
     * @param <T> the type of fixture
     * @return the proxied fixture
     */
    public <T> T get(Class<T> fixtureType) {
        return applicationContext.getBean(fixtureType);
    }

}
