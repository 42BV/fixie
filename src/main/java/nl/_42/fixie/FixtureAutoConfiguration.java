package nl._42.fixie;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "fixtures.enabled", havingValue = "true", matchIfMissing = true)
public class FixtureAutoConfiguration {

  @Bean
  public Fixtures fixtures() {
    return Fixtures.get();
  }

  @Bean
  public FixtureAspect fixtureAspect(Fixtures fixtures) {
    return new FixtureAspect(fixtures);
  }

}
