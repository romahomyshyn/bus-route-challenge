package com.goeuro.busroutechallenge.model;

import java.util.List;
import lombok.Data;

@Data
public class BusRoute {

  private final int id;
  private final List<Integer> stations;
}
