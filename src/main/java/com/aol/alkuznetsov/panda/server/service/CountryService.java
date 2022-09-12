package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.entity.Country;
import java.util.List;

public interface CountryService {

  List<Country> findAll();
}
