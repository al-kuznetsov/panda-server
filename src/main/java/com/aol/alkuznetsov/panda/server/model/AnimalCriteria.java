package com.aol.alkuznetsov.panda.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animal_criteria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalCriteria {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  private Long id;

  @Column(name = "stress")
  private Integer stress;

  @Column(name = "sickness")
  private Integer sickness;

  @Column(name = "trauma")
  private Integer trauma;

  @Column(name = "mobility")
  private Integer mobility;

  @Column(name = "tameness")
  private Integer tameness;
}
