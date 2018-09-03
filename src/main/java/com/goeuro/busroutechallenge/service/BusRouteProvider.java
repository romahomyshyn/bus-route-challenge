package com.goeuro.busroutechallenge.service;

import com.goeuro.busroutechallenge.model.BusRoute;
import java.util.List;

public interface BusRouteProvider {

  List<BusRoute> getRoutes();
}
