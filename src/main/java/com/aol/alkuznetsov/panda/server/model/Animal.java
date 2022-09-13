package com.aol.alkuznetsov.panda.server.model;

import com.aol.alkuznetsov.panda.server.enums.Status;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
  @Column(name = "id")
  private UUID id;

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

  private Status status;
}
