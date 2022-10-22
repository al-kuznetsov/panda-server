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
@Table(name = "breed")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Breed {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  private Long id;

  @Column(name = "name", nullable = false, length = 256)
  private String name;

  @Column(name = "description", length = 1024)
  private String description;

  @Column(name = "valuable")
  private Boolean valuable;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private AnimalType type;

  @Column(name = "image_url", length = 512)
  private String imageUrl;

  @Column(name = "average_body_mass")
  private Float averageBodyMass;

  @Column(name = "average_height")
  private Float averageHeight;

  @Column(name = "average_length")
  private Float averageLength;

  @Column(name = "average_tameness")
  private Integer averageTameness;
}
