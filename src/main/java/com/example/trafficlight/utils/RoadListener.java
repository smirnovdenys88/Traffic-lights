package com.example.trafficlight.utils;

import com.example.trafficlight.component.RoadState;
import com.example.trafficlight.enums.Road;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Logger;

public class RoadListener {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static final Logger LOGGER = Logger.getLogger(RoadListener.class.getName());
    private final RoadState roadState = RoadState.getInstance();
    private Set<Channel> transportSetA = new ConcurrentSkipListSet<>();
    private Set<Channel> transportSetB = new ConcurrentSkipListSet<>();

    public void addTransportToRoadA(Channel transport) {
        this.transportSetA.add(transport);
        System.out.println(ANSI_GREEN + "Transport: " + transport + " was added to the Road_A" + ANSI_RESET);
//        LOGGER.log(Level.INFO, "Transport: " + transport + " was added to the Road_A");
    }

    public void addTransportToRoadB(Channel transport) {
        this.transportSetB.add(transport);
        System.out.println(ANSI_GREEN + "Transport: " + transport + " was added to the Road_B" + ANSI_RESET);
//        LOGGER.log(Level.INFO, "Transport: " + transport + " was added to the Road_B");
    }

    public void removeTransportA(Channel transport) {
        this.transportSetA.remove(transport);
        System.out.println(ANSI_RED + "Transport: " + transport + " was removed to the Road_A" + ANSI_RESET);
//        LOGGER.log(Level.INFO, "Transport: " + transport + " was removed to the Road_A");
    }

    public void removeTransportB(Channel transport) {
        this.transportSetB.remove(transport);
        System.out.println(ANSI_RED + "Transport: " + transport + " was removed to the Road_B" + ANSI_RESET);
//        LOGGER.log(Level.INFO, "Transport: " + transport + " was removed to the Road_B");
    }

    public synchronized void runTransportCrossStreetA() {
        Optional<Channel> transport = transportSetA.stream().findFirst();
        if (transport.isPresent()) {
            transport.get().runTransportCrossStreet();
            removeTransportA(transport.get());
        }
    }

    public synchronized void runTransportCrossStreetB() {
        Optional<Channel> transport = transportSetB.stream().findFirst();
        if (transport.isPresent()) {
            transport.get().runTransportCrossStreet();
            removeTransportA(transport.get());
        }
    }

    public void runTransportCrossStreet() {
        Road road = roadState.getRoad();
        Set<Channel> channels;

        System.out.println(ANSI_BLUE + "--------------------------------------------------------");
        System.out.println("Road: " + road.name() + "transportSetA: " + transportSetA.size() + " transportSetB: " + transportSetB.size());
        System.out.println("--------------------------------------------------------" + ANSI_RESET);

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
        for (Channel channel : channels) {
            if (roadState.getRoad().name() != road.name()){
                System.out.println(ANSI_YELLOW + "****************************************************************************");
                System.out.println("Light road was changed......");
                System.out.println("****************************************************************************" + ANSI_RESET);
                break;
            }
            channel.runTransportCrossStreet();
            if(roadState.getRoad() == Road.ROAD_A){
                removeTransportA(channel);
            }else {
                removeTransportB(channel);
            }
        }
    }
}
