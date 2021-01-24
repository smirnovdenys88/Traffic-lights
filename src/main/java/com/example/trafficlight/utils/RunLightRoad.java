package com.example.trafficlight.utils;

import com.example.trafficlight.component.RoadState;
import com.example.trafficlight.enums.Road;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RunLightRoad implements Runnable {
    private RoadListener roadListener;
    private final RoadState roadState = RoadState.getInstance();

    public RunLightRoad(RoadListener ROAD_LISTENER) {
        this.roadListener = ROAD_LISTENER;
    }

    @Override
    public void run() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(() -> {
            if (roadState.getRoad() != null && roadState.getRoad() == Road.ROAD_A) {
                roadListener.runTransportCrossStreet();
                roadState.setRoad(Road.ROAD_B);
            } else {
                roadListener.runTransportCrossStreet();
                roadState.setRoad(Road.ROAD_A);
            }
        }, 0, Constants.TIMEOUT_SLEEP_TRAFFIC_LIGHT, TimeUnit.SECONDS);
    }
}
