package com.aol.alkuznetsov.panda.server.controller;

import com.aol.alkuznetsov.panda.server.dto.SexDto;
import com.aol.alkuznetsov.panda.server.service.SexService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vars.api.version-url}/sexes")
@RequiredArgsConstructor
public class SexRestController {
  private final SexService sexService;

  @GetMapping
  public List<SexDto> findAll() {
    return sexService.findAll();
  }
}
