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
@Table(name = "animal_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalType {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  private Long id;

  @Column(name = "code", nullable = false, length = 256)
  private String code;

  @Column(name = "name", nullable = false, length = 256)
  private String name;

  @Column(name = "description", length = 1024)
  private String description;

  @Column(name = "image_url", length = 512)
  private String imageUrl;
}
