package nl._42.fixie.domain;

import lombok.Getter;
import lombok.Setter;
import nl._42.fixie.domain.Country;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Person {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  private Country country;

  @ManyToOne
  private Person partner;

}
