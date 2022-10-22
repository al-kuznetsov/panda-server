package com.aol.alkuznetsov.panda.server.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
  private UUID id;
  private AddressTypeDto type;
  private CountryDto country;
  private LocalityDto locality;
  private RegionDto region;
  private String postOfficeBoxNumber;
  private String postalCode;
  private String street;
  private String building;
  private String apartment;
}
