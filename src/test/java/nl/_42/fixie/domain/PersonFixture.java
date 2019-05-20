package nl._42.fixie.domain;

import lombok.AllArgsConstructor;
import nl._42.fixie.Fixture;
import nl._42.fixie.FixtureFactory;
import nl._42.fixie.Fixtures;

@Fixture
@AllArgsConstructor
public class PersonFixture {

  private final PersonRepository repository;
  private final CountryFixture countries;
  private final FixtureFactory fixtures;

  public Person jan() {
    Person jan = person("Jan de Boer");
    return repository.save(jan);
  }

  public Person eva() {
    Person eva = person("Eva de Vries");
    eva.setPartner(fixtures.get(this).joey());
    return repository.save(eva);
  }

  public Person joey() {
    Person joey = person("Joey Bakker");
    return repository.save(joey);
  }

  private Person person(String name) {
    Person person = new Person();
    person.setName(name);
    person.setCountry(countries.netherlands());
    return person;
  }

}
