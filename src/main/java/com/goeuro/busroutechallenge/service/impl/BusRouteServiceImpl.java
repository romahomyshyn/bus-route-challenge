package com.goeuro.busroutechallenge.service.impl;

import com.goeuro.busroutechallenge.model.BusRoute;
import com.goeuro.busroutechallenge.service.BusRouteProvider;
import com.goeuro.busroutechallenge.service.BusRouteService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusRouteServiceImpl implements BusRouteService {

  private final BusRouteProvider busRouteProvider;

  @Override
  public boolean isDirectBusRoute(int departStationId, int arrivalStationId) {
    // quick solution, but it has bad complexity. it should be updated.
    return busRouteProvider.getRoutes()
        .stream()
        .anyMatch(busRoute -> hasDirectRoute(busRoute, departStationId, arrivalStationId));
  }

  private boolean hasDirectRoute(BusRoute busRoute, Integer departStationId, Integer arrivalStationId) {
    List<Integer> stations = busRoute.getStations();
    return stations.containsAll(Arrays.asList(departStationId, arrivalStationId))
        && stations.indexOf(departStationId) < stations.indexOf(arrivalStationId);
  }
}
