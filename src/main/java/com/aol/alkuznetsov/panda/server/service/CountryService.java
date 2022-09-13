package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.CountryDto;
import java.util.List;

public interface CountryService {

  List<CountryDto> findAll();
}
