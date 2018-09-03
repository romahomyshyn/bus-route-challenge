package com.goeuro.busroutechallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DirectBusRouteResponseDto {

  @JsonProperty("dep_sid")
  private int departStationId;

  @JsonProperty("arr_sid")
  private int arrivalStationId;

  @JsonProperty("direct_bus_route")
  private boolean directBusRoute;
}
