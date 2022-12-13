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
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_id_sequence")
  @SequenceGenerator(name = "global_id_sequence", allocationSize = 5)
  private Long id;

  @Column(name = "name", nullable = false, length = 512)
  private String name;

  @Column(name = "email", nullable = false, length = 512)
  private String email;

  @Column(name = "job_title", nullable = false, length = 256)
  private String jobTitle;

  @Column(name = "phone", length = 20)
  private String phone;

  @Column(name = "image_url", length = 512)
  private String imageUrl;

  @Column(name = "user_code", nullable = false, updatable = false)
  private String userCode;
}
