package com.example.trafficlight.utils;

import com.example.trafficlight.enums.Road;
import com.example.trafficlight.enums.TransportName;
import com.example.trafficlight.model.Transport;
import com.example.trafficlight.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Component
public class ProgramRunner {
    private static final Logger LOGGER = Logger.getLogger(ProgramRunner.class.getName());
    private static final int TIMEOUT_GENERATOR = 9000;
    private static final int TIMEOUT_SLEEP = 2000;

    private static AtomicLong count = new AtomicLong(100);
    private static RoadListener ROAD_LISTENER;

    @Autowired
    private TransportService transportService;

    public ProgramRunner(TransportService transportService) {
        this.transportService = transportService;
        CompletableFuture.runAsync(this::runProgram);
        ROAD_LISTENER = new RoadListener(transportService);
    }

    private void runProgram() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> generateTransport()).start();
        new Thread(new RunLightRoad(ROAD_LISTENER)).start();
    }

    private void generateTransport() {

        Random random = new Random();
        while (true) {

            int time = random.nextInt(TIMEOUT_GENERATOR + 1) + 1000;

            Transport transport = new Transport.Builder()
                    .idTransport(count.getAndIncrement())
                    .nameTransport(time % 2 == 0 ? TransportName.BUS : TransportName.CAR)
                    .road(time % 2 == 0 ? Road.ROAD_A : Road.ROAD_B)
                    .timeCrossRoad(time)
                    .build();

            transportService.add(transport);
            if (Road.ROAD_A == transport.getRoad()) {
                ROAD_LISTENER.addTransportToRoadA(transport);
            } else {
                ROAD_LISTENER.addTransportToRoadB(transport);
            }
            try {
                Thread.sleep(TIMEOUT_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
