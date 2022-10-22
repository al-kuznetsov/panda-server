package com.aol.alkuznetsov.panda.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  private Long id;

  @Column(name = "name", nullable = false, length = 512)
  private String name;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private RegionType type;
}
