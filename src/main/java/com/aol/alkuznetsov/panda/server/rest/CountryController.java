package com.aol.alkuznetsov.panda.server.rest;

import com.aol.alkuznetsov.panda.server.dto.CountryDto;
import com.aol.alkuznetsov.panda.server.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vars.api.version-url}")
public class CountryController {
  @Autowired private CountryService countryService;

  @GetMapping("/countries")
  public List<CountryDto> findAll() {
    return countryService.findAll();
  }
}
