package nl._42.fixie;

import nl._42.database.truncator.DatabaseTruncator;
import nl._42.fixie.domain.Country;
import nl._42.fixie.domain.CountryFixture;
import nl._42.fixie.domain.Person;
import nl._42.fixie.domain.PersonFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FixtureTest {

  @Autowired
  private PersonFixture persons;

  @Autowired
  private CountryFixture countries;

  @Autowired
  private DatabaseTruncator truncator;

  @BeforeEach
  public void clear() throws Exception {
    truncator.truncate();
  }

  @Test
  @DisplayName("Fixtures should return an object")
  public void fixtures_simple() {
    Country netherlands = countries.netherlands();

    assertEquals("NL", netherlands.getCode());
    assertEquals("Netherlands", netherlands.getName());
  }

  @Test
  @DisplayName("Fixtures should return the same object")
  public void fixtures_reuse() {
    Country netherlands = countries.netherlands();

    assertEquals(netherlands, countries.netherlands());
  }

  @Test
  @DisplayName("Fixtures should be able to re-use other fixtures")
  public void fixtures_with_fixtures() {
    Person jan = persons.jan();

    assertEquals("Jan de Boer", jan.getName());
    assertEquals(countries.netherlands(), jan.getCountry());
    assertEquals(jan, persons.jan());
  }

  @Test
  @DisplayName("Fixtures should be able to re-use similar fixtures")
  public void fixtures_with_fixtures_same_type() {
    Person eva = persons.eva();

    assertEquals("Eva de Vries", eva.getName());
    assertEquals(countries.netherlands(), eva.getCountry());

    Person joey = eva.getPartner();
    assertEquals(persons.joey(), joey);
    assertEquals("Joey Bakker", joey.getName());
    assertEquals(countries.netherlands(), joey.getCountry());
  }

  @Nested
  public class NestedTest {

    @Test
    @DisplayName("Fixtures should also be reset in a nested context")
    public void fixtures_complex() {
      Person jan = persons.jan();

      assertEquals("Jan de Boer", jan.getName());
      assertEquals(countries.netherlands(), jan.getCountry());
      assertEquals(jan, persons.jan());
    }

  }

}
