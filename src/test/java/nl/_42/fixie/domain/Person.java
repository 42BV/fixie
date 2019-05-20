package nl._42.fixie.domain;

import lombok.Getter;
import lombok.Setter;
import nl._42.fixie.domain.Country;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

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
