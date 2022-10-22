package com.aol.alkuznetsov.panda.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "country")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  private Long id;

  @Column(name = "alpha_two_code", nullable = false, length = 2)
  private String alphaTwoCode;

  @Column(name = "short_name", nullable = false, length = 128)
  private String shortName;

  @Column(name = "name", nullable = false, length = 256)
  private String name;
}
