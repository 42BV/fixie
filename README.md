# Fixie

Fixie is a lightweight library for managing test `Fixtures`.

Fixtures are standardized data sets that represent production data as much as possible. 
These fixtures are used as input in regression tests to verify that the functionality 
will work accordingly:

```java
@Fixture
@AllArgsConstructor
public class CountryFixtures {
    
    private final CountryRepository repository;
    
    public Country netherlands() {
        Country country = new Country();
        country.setCode("NL");
        country.setName("Netherlands");
        return repository.save(country);
    }
    
}
```

Because fixtures object are unique, they should only be created once per test method. Otherwise
we might violate database constraints or create strange test results. Fixie manages this uniqueness, 
allowing the developers to focus on their tests instead.

By annotating the fixture class with `@Fixture`, Fixie will automatically proxy the bean with an
in-memory cache, named `Fixtures`. This cache is reset before and after each test method invocation,
considering the database should also be truncated.

The uniqueness of our fixtures is guaranteed, allowing fixtures to be used multiple times and even use 
fixtures in other fixtures. This way we reuse code as much as possible and make the construction of 
complex data structures easier:

```java
@Fixture
@AllArgsConstructor
public class PersonFixtures {
    
    private final PersonRepository repository;
    private final CountryFixtures countries;
    
    public Person jan() {
        Person person = new Person();
        person.setName("Jan");
        person.setEmail("jan@42.nl");
        person.setCountry(countries.netherlands());
        return repository.save(person);
    }
    
    public Person dirk() {
        Person person = new Person();
        person.setName("Dirk");
        person.setEmail("dirk@42.nl");
        person.setCountry(countries.netherlands());
        return repository.save(person);
    }
    
}
```

Allowing us to write understandable unit tests, without violating database unique constraints:

```java
public class FixtureTest {

  private PersonFixture persons;
  private CountryFixture countries;
  private DatabaseTruncator truncator;

  @BeforeEach
  public void clear() {
    truncator.truncate();
  }

  @Test
  public void unique() {
    Person jan = persons.jan();
    Person dirk = persons.dirk();

    assertEquals("Jan", jan.getName());
    assertEquals("Dirk", dirk.getName());
    
    assertEquals(jan.getCountry(), dirk.getCountry());
    assertEquals(countries.netherlands(), jan.getCountry());
  }
  
}
```

## Usage

Include the dependency in your project:

```xml
<dependency>
    <groupId>nl.42</groupId>
    <artifactId>fixie</artifactId>
    <version>1.1.0</version>
</dependency>
```

This includes a Spring Boot starter, requiring no further configuration.

### Plain Spring

Fixie can also be used without Spring Boot. 

Register the following aspect:

```java
@Configuration
public class FixtureConfiguration {
    
    @Bean
    public FixtureAspect fixtureAspect() {
      return new FixtureAspect(Fixtures.get());
    }
    
}
```

Reset the fixtures before each unit test:

```java
public class SomeTest {
    
    @BeforeEach
    public void clear() {
        Fixtures.get().clear();
    }
    
}
```
