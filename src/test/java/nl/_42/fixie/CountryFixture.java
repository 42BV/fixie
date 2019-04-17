package nl._42.fixie;

import lombok.AllArgsConstructor;

@Fixture
@AllArgsConstructor
public class CountryFixture {

    private CountryRepository repository;

    public Country netherlands() {
        return build("NL", "Netherlands");
    }

    private Country build(String code, String name) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        return repository.save(country);
    }

}
