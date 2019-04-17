package nl._42.fixie;

import nl._42.database.truncator.DatabaseTruncator;
import org.junit.jupiter.api.BeforeEach;
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
  public void fixtures_simple() {
    Country netherlands = countries.netherlands();

    assertEquals("NL", netherlands.getCode());
    assertEquals("Netherlands", netherlands.getName());
    assertEquals(netherlands, countries.netherlands());
  }

  @Test
  public void fixtures_complex() {
    Person jan = persons.jan();

    assertEquals("Jan de Boer", jan.getName());
    assertEquals(countries.netherlands(), jan.getCountry());
    assertEquals(jan, persons.jan());
  }

}
