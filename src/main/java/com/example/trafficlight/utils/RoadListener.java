package com.example.trafficlight.utils;

import com.example.trafficlight.component.RoadState;
import com.example.trafficlight.enums.Road;
import com.example.trafficlight.model.Transport;
import com.example.trafficlight.service.TransportService;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoadListener {
    private static final Logger LOGGER = Logger.getLogger(RoadListener.class.getName());

    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private final RoadState roadState = RoadState.getInstance();
    private Set<Transport> transportSetA = new ConcurrentSkipListSet<>();
    private Set<Transport> transportSetB = new ConcurrentSkipListSet<>();

    private TransportService transportService;

    public RoadListener(TransportService transportService) {
        this.transportService = transportService;
    }

    public void addTransportToRoadA(Transport transport) {
        this.transportSetA.add(transport);
        LOGGER.log(Level.INFO, "Transport: " + transport + " was added to the Road_A");
        if (!atomicBoolean.get()) runTransportCrossStreet();
    }

    public void addTransportToRoadB(Transport transport) {
        this.transportSetB.add(transport);
        LOGGER.log(Level.INFO, "Transport: " + transport + " was added to the Road_B");
        if (!atomicBoolean.get()) runTransportCrossStreet();
    }

    public void removeTransportA(Transport transport) {
        this.transportSetA.remove(transport);
        LOGGER.log(Level.INFO, "Transport: " + transport + " was removed to the Road_A");
    }

    public void removeTransportB(Transport transport) {
        this.transportSetB.remove(transport);
        LOGGER.log(Level.INFO, "Transport: " + transport + " was removed to the Road_B");
    }

    public void runTransportCrossStreet() {
        atomicBoolean.set(true);
        Road road = roadState.getRoad();
        Set<Transport> channels;

        switch (roadState.getRoad()) {
            case ROAD_A:
                channels = transportSetA;
                break;
            case ROAD_B:
                channels = transportSetB;
                break;
            default:
                channels = new ConcurrentSkipListSet<>();
        }
        for (Transport channel : channels) {
            if (roadState.getRoad().name() != road.name()) {
                break;
            }
            channel.runTransportCrossStreet();
            if (roadState.getRoad() == Road.ROAD_A) {
                removeTransportA(channel);
            } else {
                removeTransportB(channel);
            }
            transportService.update(channel);
        }
        atomicBoolean.set(false);
    }
}
