package com.example.trafficlight.utils;

import com.example.trafficlight.component.RoadState;
import com.example.trafficlight.enums.Road;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RunLightRoad implements Runnable{
    private static final int TIMEOUT_SLEEP_TRAFFIC_LIGHT = 20;
    private RoadListener roadListener;
    private final RoadState roadState = RoadState.getInstance();

    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public RunLightRoad(RoadListener ROAD_LISTENER) {
        this.roadListener = ROAD_LISTENER;
    }

    @Override
    public void run() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(() -> {
            System.out.println(ANSI_PURPLE + "****************************************************************************");
            System.out.println("Road :" + roadState.getRoad().name() + " run");
            System.out.println("****************************************************************************" + ANSI_RESET);

            if (roadState.getRoad() != null && roadState.getRoad() == Road.ROAD_A) {
                roadListener.runTransportCrossStreet();
                roadState.setRoad(Road.ROAD_B);
            } else {
                roadListener.runTransportCrossStreet();
                roadState.setRoad(Road.ROAD_A);
            }
        }, 0, TIMEOUT_SLEEP_TRAFFIC_LIGHT, TimeUnit.SECONDS);
    }
}
