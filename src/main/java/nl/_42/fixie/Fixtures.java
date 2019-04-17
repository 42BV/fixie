package nl._42.fixie;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Storage of all active fixtures.
 */
public class Fixtures {

  private final ConcurrentHashMap<String, Object> values = new ConcurrentHashMap<>();

  public void clear() {
    values.clear();
  }

  public Object get(Method method, Generator generator) {
    String key = method.getDeclaringClass().getName() + "." + method.getName();
    return get(key, generator);
  }

  public Object get(String key, Generator generator) {
    return values.computeIfAbsent(key, argument -> generate(generator));
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
