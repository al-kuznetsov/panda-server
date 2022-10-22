package com.aol.alkuznetsov.panda.server.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "address_type_id")
  private AddressType type;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;

  @ManyToOne
  @JoinColumn(name = "locality_id")
  private Locality locality;

  @ManyToOne
  @JoinColumn(name = "region_id")
  private Region region;

  @Column(name = "post_office_box_number", length = 80)
  private String postOfficeBoxNumber;

  @Column(name = "postal_code", length = 80)
  private String postalCode;

  @Column(name = "street", length = 255)
  private String street;

  @Column(name = "building", length = 80)
  private String building;

  @Column(name = "apartment", length = 80)
  private String apartment;
}
