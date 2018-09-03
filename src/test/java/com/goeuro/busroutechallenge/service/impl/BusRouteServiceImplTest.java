package com.goeuro.busroutechallenge.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.goeuro.busroutechallenge.model.BusRoute;
import com.goeuro.busroutechallenge.service.BusRouteProvider;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BusRouteServiceImplTest {

  @InjectMocks
  private BusRouteServiceImpl busRouteService;

  @Mock
  private BusRouteProvider busRouteProvider;

  @Test
  public void shouldNotHaveConnectionWhenBusRouteEmpty() {
    when(busRouteProvider.getRoutes()).thenReturn(Collections.emptyList());

    boolean directBusRoute = busRouteService.isDirectBusRoute(10, 12);

    assertThat(directBusRoute, is(false));

    verify(busRouteProvider, only()).getRoutes();
    verifyNoMoreInteractions(busRouteProvider);
  }

  @Test
  public void shouldNotHaveConnectionIfWrongDirection() {
    BusRoute busRoute = new BusRoute(1, Arrays.asList(10, 11, 12));
    when(busRouteProvider.getRoutes()).thenReturn(Collections.singletonList(busRoute));

    boolean directBusRoute = busRouteService.isDirectBusRoute(11, 10);

    assertThat(directBusRoute, is(false));

    verify(busRouteProvider, only()).getRoutes();
    verifyNoMoreInteractions(busRouteProvider);
  }

  @Test
  public void shouldHaveDirectConnection() {
    BusRoute busRoute = new BusRoute(1, Arrays.asList(10, 11, 12));
    when(busRouteProvider.getRoutes()).thenReturn(Collections.singletonList(busRoute));

    boolean directBusRoute = busRouteService.isDirectBusRoute(10, 12);

    assertThat(directBusRoute, is(true));

    verify(busRouteProvider, only()).getRoutes();
    verifyNoMoreInteractions(busRouteProvider);
  }
}