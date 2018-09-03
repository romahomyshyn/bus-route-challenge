package com.goeuro.busroutechallenge.service.impl;

import com.goeuro.busroutechallenge.model.BusRoute;
import com.goeuro.busroutechallenge.service.BusRouteProvider;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileBusRouteProvider implements BusRouteProvider {

  private static final String EMPTY_SPACE_REGEX = "\\s+";

  private final List<BusRoute> routes;

  public FileBusRouteProvider(@Value("${data.path}") String dataPath) {
    this.routes = readData(dataPath);
  }

  @Override
  public List<BusRoute> getRoutes() {
    return routes;
  }

  private List<BusRoute> readData(String dataPath) {
    try (Stream<String> lines = Files.lines(Paths.get(dataPath))) {
      return lines.skip(1)
          .map(FileBusRouteProvider::mapStringToBusRoute)
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private static BusRoute mapStringToBusRoute(String routesData) {
    String[] routes = routesData.split(EMPTY_SPACE_REGEX);

    int routeId = Integer.parseInt(routes[0]);
    List<Integer> stationIds = Arrays.stream(routes)
        .mapToInt(Integer::parseInt)
        .boxed()
        .skip(1)
        .collect(Collectors.toList());

    return new BusRoute(routeId, stationIds);
  }
}
