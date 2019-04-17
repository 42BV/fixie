package nl._42.fixie;

import lombok.AllArgsConstructor;

@Fixture
@AllArgsConstructor
public class PersonFixture {

  private final PersonRepository repository;
  private final CountryFixture countries;

  public Person jan() {
    Person person = new Person();
    person.setName("Jan de Boer");
    person.setCountry(countries.netherlands());
    return repository.save(person);
  }

}
