package com.aol.alkuznetsov.panda.server.rest;

import com.aol.alkuznetsov.panda.server.dto.CountryDto;
import com.aol.alkuznetsov.panda.server.service.CountryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vars.api.version-url}/countries")
@RequiredArgsConstructor
public class CountryRestController {
  private final CountryService countryService;

  @GetMapping
  public List<CountryDto> findAll() {
    return countryService.findAll();
  }
}
