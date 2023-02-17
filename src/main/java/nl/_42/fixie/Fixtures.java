package nl._42.fixie;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Storage of all active fixtures.
 */
public class Fixtures {

  private final Map<String, Object> values = new HashMap<>();

  private static final Fixtures INSTANCE = new Fixtures();

  private Fixtures() {
  }

  public static Fixtures get() {
    return INSTANCE;
  }

  public void clear() {
    values.clear();
  }

  Object get(Method method, Generator generator) {
    String key = keyOf(method.getDeclaringClass(), method.getName());
    return get(key, generator);
  }

  private static String keyOf(Class<?> fixtureClass, String name) {
    return format("%s.%s", fixtureClass.getName(), name);
  }

  Object get(String key, Generator generator) {
    Object value = values.get(key);
    if (value == null) {
      value = generate(generator);
      values.put(key, value);
    }
    return value;
  }

  private Object generate(Generator generator) {
    try {
      return generator.generate();
    } catch (Throwable cause) {
      throw new IllegalStateException("Could not generate fixture object", cause);
    }
  }

  @FunctionalInterface
  interface Generator {

    Object generate() throws Throwable;

  }

}
