package com.aol.alkuznetsov.panda.server.model;

import java.time.LocalDate;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "description")
  private String description;

  @Column(name = "full_bio")
  private String fullBio;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "active")
  private Boolean active = false;

  @ManyToOne
  @JoinColumn(name = "status_id")
  private AnimalStatus animalStatus;
}
