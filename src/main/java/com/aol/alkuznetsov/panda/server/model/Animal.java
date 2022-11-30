package com.aol.alkuznetsov.panda.server.model;

import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "animal")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", length = 512)
  private String name;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "description", length = 15600)
  private String description;

  @Column(name = "full_bio")
  private String fullBio;

  @Column(name = "image_url", length = 512)
  private String imageUrl;

  @Builder.Default
  @Column(name = "active")
  private Boolean active = false;

  @CreationTimestamp
  @Column(name = "date_created")
  private Instant dateCreated;

  @UpdateTimestamp
  @Column(name = "last_updated")
  private Instant dateUpdated;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private AnimalType type;

  @ManyToOne
  @JoinColumn(name = "breed_id")
  private Breed breed;

  @ManyToOne
  @JoinColumn(name = "status_id")
  private AnimalStatus status;
  
  @ManyToOne
  @JoinColumn(name = "sex_id")
  private Sex sex;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "indicators_id")
  private AnimalIndicators indicators;
}
