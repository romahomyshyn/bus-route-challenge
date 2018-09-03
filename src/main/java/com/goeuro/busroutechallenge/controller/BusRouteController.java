package com.goeuro.busroutechallenge.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.goeuro.busroutechallenge.dto.DirectBusRouteResponseDto;
import com.goeuro.busroutechallenge.service.BusRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusRouteController {

  private final BusRouteService busRouteService;

  @GetMapping(value = "/direct", params = {"dep_sid", "arr_sid"})
  public ResponseEntity<DirectBusRouteResponseDto> directRoute(
      @RequestParam(value = "dep_sid") int departStationId,
      @RequestParam(value = "arr_sid") int arrivalStationId) {
    boolean directBusRoute = busRouteService.isDirectBusRoute(departStationId, arrivalStationId);
    return ok(new DirectBusRouteResponseDto(departStationId, arrivalStationId, directBusRoute));
  }
}
