package com.aol.alkuznetsov.panda.server.rest;

import com.aol.alkuznetsov.panda.server.dto.SpotDto;
import com.aol.alkuznetsov.panda.server.service.SpotService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for miscellaneous endpoints. */
@RestController
@RequestMapping("${vars.api.version-url}")
@RequiredArgsConstructor
public class SpotRestController {
  private final SpotService spotService;

  @GetMapping("/spots")
  // TODO: broken JSON serialization here (cycle). Need to add proper DTO mapping for Spot class
  public List<SpotDto> findAll() {
    return spotService.findAllSpots();
  }
}
